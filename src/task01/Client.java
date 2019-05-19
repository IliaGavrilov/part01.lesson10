package task01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {

    public static void main(String[] args){

        /* UDP socket на клиентской стороне */
        try {
            DatagramSocket ds = new DatagramSocket(6226);
            byte[] buf = new byte[1024];
            DatagramPacket pack = new DatagramPacket(buf, 1024);

            while(true){
                ds.receive(pack);
                pack.setData(pack.getData());

                String str = new String(pack.getData());
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
