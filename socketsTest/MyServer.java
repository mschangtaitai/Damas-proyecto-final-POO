import java.io.*;
import java.net.*;

public class MyServer{
	public static void main(String[] args){
		try{
			ServerSocket ss = new ServerSocket(5000);
			Socket s= ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String str = (String)dis.readUTF();
			System.out.println("Client says = " + str);
			DataOutputStream dout = new DataOutputStream(ss.getOutputStream());
			dout.writeUTF("Hello Client");
			dout.flush();
			dout.close();
			dis.close();
			ss.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}