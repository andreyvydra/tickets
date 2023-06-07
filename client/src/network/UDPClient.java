package network;


import requests.Request;
import responses.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

import static core.Globals.DATA_SIZE;
import static core.Globals.Network.*;
import static core.Globals.PACKET_SIZE;

public class UDPClient {
    private final DatagramChannel datagramChannel;
    private final SocketAddress addr;

    public UDPClient(InetAddress address, int port) throws IOException {
        addr = new InetSocketAddress(address, port);
        datagramChannel = DatagramChannel.open().connect(addr);
        datagramChannel.configureBlocking(false);
    }

    /**
     * Класс делит данные на пакеты, а также добавляет специальный
     * байт в конец и отправляет их на сервер
     *
     * @param data - данные для отправки
     * @throws IOException - ошибка при передачи данных
     */
    public void sendData(byte[] data) throws IOException {
        byte[][] packetData = new byte[(int)Math.ceil(data.length / (double)DATA_SIZE)][DATA_SIZE];
        for (int i = 0; i < packetData.length; i++) {
            packetData[i] = Arrays.copyOfRange(data, i * DATA_SIZE, (i + 1) * DATA_SIZE);
        }

        for (int i = 0; i < packetData.length; i++) {
            byte[] packet = packetData[i];
            byte[] packetToSend = Arrays.copyOf(packet, PACKET_SIZE);
            if (i + 1 == packetData.length) {
                System.arraycopy(new byte[]{PACKET_ENDS}, 0, packetToSend, DATA_SIZE, 1);
            } else {
                System.arraycopy(new byte[]{PACKET_CONTINUES}, 0, packetToSend, DATA_SIZE, 1);
            }
            datagramChannel.send(ByteBuffer.wrap(packetToSend), addr);
        }

    }

    public byte[] receiveData() throws IOException {
        boolean isReceived = false;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (!isReceived) {
            byte[] packet = getPacket();
            if (packet[packet.length - 1] == PACKET_ENDS) {
                isReceived = true;
            }
            outputStream.write(Arrays.copyOf(packet, DATA_SIZE));
        }
        return outputStream.toByteArray();
    }

    public byte[] getPacket() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
        SocketAddress address = null;
        while(address == null) {
            address = datagramChannel.receive(buffer);
        }
        return buffer.array();
    }

    public Response sendRequestAndGetResponse(Request request) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(request);
        out.close();
        bos.close();
        sendData(bos.toByteArray());
        byte[] receivedData = receiveData();
        ByteArrayInputStream bis = new ByteArrayInputStream(receivedData);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Response response = (Response) ois.readObject();
        ois.close();
        bis.close();
        return response;
    }
}
