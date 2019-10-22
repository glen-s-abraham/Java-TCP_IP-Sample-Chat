package tcpchat;
import java.io.*;
import java.net.*;
public class client {
	public static void main(String srgs[])throws Exception
	{
		Socket socket=new Socket("localhost",666);
		System.out.println("connected to server");
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		BufferedReader kin=new BufferedReader(new InputStreamReader(System.in));
		String msg="client connected";
		out.println(msg);
		msg=in.readLine();
		System.out.println("Server: "+msg);
		//msg=in.readLine();
		//System.out.println("Server: "+msg);
		while(true)
		{
			
			msg=in.readLine();
			System.out.println("Server: "+msg);
			msg=kin.readLine();
			System.out.println("Client: "+msg);
			out.println(msg);
		}
	}
 
}
