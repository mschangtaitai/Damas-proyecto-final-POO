import java.io.*;
import java.net.*;

public class MyClient{
	public static void main(String[] args){
		try{
			Socket s = new Socket("127.0.0.1",5000);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF("Hello Server");
			DataInputStream dis = new DataInputStream(ss.getInputStream());
			String str = (String)dis.readUTF();
			System.out.println("Server says = " + str);
			dout.flush();
			dout.close();
			s.close();
			dis.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}