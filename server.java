package tcpchat;
import java.net.*;
import java.io.*;
public class server {
	public static void main(String args[])throws Exception{
		ServerSocket server_socket=new ServerSocket(666);
		System.out.println("Waiting for client");
		Socket socket=server_socket.accept();
		System.out.println("Client connected"+socket.getInetAddress());
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		BufferedReader kin=new BufferedReader(new InputStreamReader(System.in));
		String msg;
		//out.println("welcome");
		while(true)
		{
			msg=kin.readLine();
			System.out.println("Server: "+msg);
			out.println(msg);
			msg=in.readLine();
			System.out.println("Client: "+msg);
		}
		
		
	}

}
