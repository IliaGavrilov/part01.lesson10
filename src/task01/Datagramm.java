package task01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Datagramm {

    public static void main(String[] args) {

        /* UDP socket на серверной стороне */
        try {
            DatagramSocket ds = new DatagramSocket();

            byte[] data = "Пинг".getBytes();
            InetAddress addr = InetAddress.getByName("127.0.0.1");

            DatagramPacket pack = new DatagramPacket(data, data.length, addr, 6226);

            Scanner scanner = new Scanner(System.in);
            String message;

            while (!(message = scanner.nextLine()).isEmpty()){
                pack.setData(message.getBytes());
                ds.send(pack);
            }
            ds.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}