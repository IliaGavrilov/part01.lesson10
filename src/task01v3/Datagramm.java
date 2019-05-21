package task01v3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Datagramm {

    public static void main(String[] args) throws IOException {

        /* Получение собственного имени клиента сервером */
        DatagramSocket serverSocket = new DatagramSocket(6226);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        int port = receivePacket.getPort();
        InetAddress IPAddress = receivePacket.getAddress();
        String sentence = new String(receivePacket.getData());
        System.out.println("Получено от клиента: " + sentence);

        /* Получение сообщений от клиента и их рассылка (broadcast) */
        while(true) {
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);

            serverSocket.receive(receivePacket);
            port = receivePacket.getPort();
            IPAddress = receivePacket.getAddress();
            sentence = new String(receivePacket.getData());
            System.out.println("Получено от клиента: " + sentence);

            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
