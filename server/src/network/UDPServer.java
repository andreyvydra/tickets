package network;

import application.DataApp;
import commands.Command;
import managers.CommandManager;
import requests.Request;
import javafx.util.Pair;
import responses.Response;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import static core.Globals.DATA_SIZE;
import static core.Globals.Network.*;
import static core.Globals.PACKET_SIZE;

public class UDPServer {
    private final Logger logger = Logger.getLogger(UDPServer.class.getName());
    private final DatagramSocket datagramSocket;
    private boolean isRunning = true;
    private final CommandManager commandManager;

    private final ExecutorService serviceRequest;
    private final ExecutorService serviceReceiveData;
    private final ExecutorService serviceSendResponse;


    public UDPServer(InetSocketAddress address, DataApp dataApp) throws SocketException {
        datagramSocket = new DatagramSocket(address);
        this.commandManager = new CommandManager(logger, dataApp);
        serviceReceiveData = Executors.newFixedThreadPool(10);
        serviceRequest = Executors.newFixedThreadPool(10);
        serviceSendResponse = Executors.newCachedThreadPool();
    }

    public Pair<byte[], SocketAddress> receiveData() throws IOException {
        boolean isReceived = false;
        SocketAddress address = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (!isReceived) {
            byte[] data = new byte[PACKET_SIZE];
            DatagramPacket packet = new DatagramPacket(data, PACKET_SIZE);
            datagramSocket.receive(packet);
            address = packet.getSocketAddress();
            if (data[data.length - 1] == PACKET_ENDS) {
                isReceived = true;
            }
            outputStream.write(Arrays.copyOf(data, DATA_SIZE));
        }
        logger.info("Получено " + outputStream.toByteArray().length + " байт с " + address);
        return new Pair<>(outputStream.toByteArray(), address);
    }


    public void sendResponse(Response response, SocketAddress socketAddress) {
        serviceSendResponse.execute(() -> {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(bos);
                os.writeObject(response);
                os.flush();
                byte[] data = bos.toByteArray();
                byte[][] packetData = new byte[(int) Math.ceil(data.length / (double) DATA_SIZE)][DATA_SIZE];
                for (int i = 0; i < packetData.length; i++) {
                    packetData[i] = Arrays.copyOfRange(data, i * DATA_SIZE, (i + 1) * DATA_SIZE);
                }

                logger.info("Отправка " + data.length + " байт на " + socketAddress);
                for (int i = 0; i < packetData.length; i++) {
                    byte[] packet = packetData[i];
                    byte[] packetToSend = Arrays.copyOf(packet, PACKET_SIZE);
                    if (i + 1 == packetData.length) {
                        System.arraycopy(new byte[]{PACKET_ENDS}, 0, packetToSend, DATA_SIZE, 1);
                    } else {
                        System.arraycopy(new byte[]{PACKET_CONTINUES}, 0, packetToSend, DATA_SIZE, 1);
                    }
                    DatagramPacket datagramPacket = new DatagramPacket(packetToSend, packetToSend.length, socketAddress);
                    datagramSocket.send(datagramPacket);
                }
                logger.info("Отправка на " + socketAddress + " завершена.");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "ошибка в sendData", e);
            }
        });
    }

    public void run() {
        logger.info("Старт сервера");
        serviceReceiveData.execute(() -> {
            while (isRunning) {
                Pair<byte[], SocketAddress> pair;
                try {
                    pair = receiveData();
                } catch (IOException e) {
                    if (datagramSocket.isClosed()) {
                        return;
                    }
                    logger.log(Level.SEVERE, "ошибка при получении данных", e);
                    continue;
                }

                serviceRequest.execute(() -> {
                    var data = pair.getKey();
                    var socketAddress = pair.getValue();

                    Request request;
                    try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
                         ObjectInput ois = new ObjectInputStream(bis)) {
                        request = (Request) ois.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        logger.log(Level.SEVERE, "ошибка при обработке реквеста", e);
                        return;
                    }
                    logger.info("Пришёл " + request.toString());

                    Response response;
                    Command command = commandManager.getCommand(request.getCommandName());
                    response = command.execute(request);

                    logger.info("Ответ: " + response.toString());

                    sendResponse(response, socketAddress);
                });
            }
        });
        serviceReceiveData.shutdown();
    }

    public void stop() {
        isRunning = false;
        datagramSocket.close();
    }
}
