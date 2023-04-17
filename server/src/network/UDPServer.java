package network;

import application.DataApp;
import commands.Add;
import commands.Command;
import core.OutputHandler;
import managers.CommandManager;
import requests.Request;
import javafx.util.Pair;
import responses.Response;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class UDPServer {
    private final Logger logger = Logger.getLogger(UDPServer.class.getName());
    private final int PACKET_SIZE = 1024;
    private final DatagramSocket datagramSocket;
    private final DataApp dataApp;
    private final CommandManager commandManager;

    public UDPServer(InetSocketAddress address, DataApp dataApp) throws SocketException {
        datagramSocket = new DatagramSocket(address);
        this.dataApp = dataApp;
        this.commandManager = new CommandManager(logger, dataApp);
    }

    public Pair<byte[], SocketAddress> receiveData() throws IOException {
        byte[] arr = new byte[PACKET_SIZE];
        int len = arr.length;
        DatagramPacket datagramPacket = new DatagramPacket(arr, len);
        datagramSocket.receive(datagramPacket);
        SocketAddress socketAddress = new InetSocketAddress(datagramPacket.getAddress(), datagramPacket.getPort());
        return new Pair<>(arr, socketAddress);
    }

    public void sendResponse(Response response, SocketAddress socketAddress) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(response);
        os.flush();
        DatagramPacket datagramPacket = new DatagramPacket(bos.toByteArray(), bos.toByteArray().length, socketAddress);
        datagramSocket.send(datagramPacket);
        bos.close();
        os.close();
    }

    public void run() {
        logger.info("Старт сервера");
        while (true) {
            Pair<byte[], SocketAddress> pair;
            try {
                pair = receiveData();
            } catch (IOException e) {
                logger.throwing(UDPServer.class.getName(), "receiveData", e);
                continue;
            }

            var data = pair.getKey();
            var socketAddress = pair.getValue();

            Request request;
            try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
                 ObjectInput ois = new ObjectInputStream(bis)) {
                request = (Request) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                logger.info(e.toString());
                continue;
            }
            logger.info("Пришёл " + request.toString());

            Response response;
            try {
                Command command = commandManager.getCommand(request.getCommandName());
                response = command.execute(request);
            } catch (Exception e) {
                logger.info(e.toString());
                continue;
            }
            logger.info("Ответ " + response.toString());


            try {
                sendResponse(response, socketAddress);
            } catch (IOException e) {
                logger.throwing(UDPServer.class.getName(), "sendData", e);
            }

        }
    }
}
