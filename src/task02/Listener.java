package task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Listener extends Thread {
    private int port;

    /* Конструктор класса Listener для получения порта клиента */
    public Listener(int port) {
        this.port = port;
    }

    /* Переписанный метод run() класса Thread для отправки и получения сообщений от сервера */
    @Override
    public void run(){
        try(
            DatagramSocket clientSocket = new DatagramSocket(port);
            )
        {
            String sentence = " ";
            while(!sentence.isEmpty()){
                System.out.println("Введите сообщение: ");
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                sentence = inFromUser.readLine();

                byte[] sendData = new byte[1024];
                sendData = sentence.getBytes();
                InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 6226);
                clientSocket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());
                System.out.println("Получено от сервера: " + modifiedSentence);
            } clientSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}