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

public class UDPClient {
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;
    private DatagramChannel datagramChannel;
    private SocketAddress addr;

    public UDPClient(InetAddress address, int port) throws IOException {
        addr = new InetSocketAddress(address, port);
        datagramChannel = DatagramChannel.open().bind(null).connect(addr);
        datagramChannel.configureBlocking(false);
    }

    public byte[] sendAndReceiveData(byte[] data) throws IOException {
        System.out.println(data.length);
        datagramChannel.send(ByteBuffer.wrap(data), addr);
        byte[] receivedData = new byte[PACKET_SIZE];
        datagramChannel.receive(ByteBuffer.wrap(receivedData));
        return receivedData;
    }

    public Response sendRequestAndGetResponse(Request request) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(request);
        out.close();
        byte[] receivedData = sendAndReceiveData(bos.toByteArray());
        ByteArrayInputStream bis = new ByteArrayInputStream(receivedData);
        ObjectInputStream ois;
        ois = new ObjectInputStream(bis);
        Response response = (Response) ois.readObject();
        ois.close();
        return response;
    }
}
