package task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static int CLIENT_PORT = 6225;

    public static void main(String[] args) throws IOException {

        /* Отправка собственного имени клиента серверу */
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя клиента: ");
        String sentence = inFromUser.readLine();
        byte[] sendData = new byte[1024];
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Datagramm.SERVER_PORT);
        clientSocket.send(sendPacket);

        /* Отправка и получение сообщений от сервера через Listener в отдельном треде */
        Listener listenerThread = new Listener(CLIENT_PORT);
        listenerThread.start();
    }
}
