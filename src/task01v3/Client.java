package task01v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws IOException {

        /* Отправка собственного имени клиента серверу */
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя клиента: ");
        String sentence = inFromUser.readLine();
        byte[] sendData = new byte[1024];
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 6226);
        clientSocket.send(sendPacket);

        /* Отправка и получение сообщений от сервера */
        sentence = " ";
        while(!sentence.isEmpty()){
            System.out.println("Введите сообщение: ");
            sentence = inFromUser.readLine();
            sendData = new byte[1024];
            sendData = sentence.getBytes();

            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 6226);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("Получено от сервера: " + modifiedSentence);
        }
        clientSocket.close();
    }
}
