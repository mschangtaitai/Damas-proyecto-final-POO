package Server;
import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Waiting for client 1");
        Socket client1 = serverSocket.accept();

        BufferedReader client1In = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        System.out.println("Setup finished");

        String msg;
        while(true) {
            System.out.println("Esperando siguiente movimiento");
            msg = client1In.readLine();
            System.out.println("Se ha movido una pieza " + msg.substring(9,13) + "\nPosicion inicial: [" + msg.substring(26,27) + "," + msg.substring(19,20) + "]" + "\nPosicion final: [" + msg.substring(44,45) + "," + msg.substring(35,36) + "]\n-----------------------------------");
        }
    }
}
