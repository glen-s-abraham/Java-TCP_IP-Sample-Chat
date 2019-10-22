package tcpchat;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class server_gui implements ActionListener{
	JFrame f;
	JScrollPane sp;
	JLabel l;
	JTextField port_box,text_box;
	JTextArea chat_area;
	JButton port,send;
	ServerSocket serverSocket;
	BufferedReader in;
	PrintWriter out;
	String msg;
	server_gui()
	{
		f=new JFrame("Server ");
		f.setSize(340, 500);
		f.setLayout(null);
		l=new JLabel("port");
		l.setBounds(10,20,100,50);
		port_box=new JTextField("666");
		port_box.setBounds(80,20,100,50);
		port=new JButton("Host");
		port.addActionListener(this);
		port.setBounds(200,20,100,50);
		chat_area=new JTextArea();
		chat_area.setBounds(10,80,300,300);
		sp=new JScrollPane(chat_area);
		sp.setBounds(10,80,300,300);
		text_box=new JTextField();
		text_box.setBounds(10,400,180,50);
		send=new JButton("send");
		send.addActionListener(this);
		send.setBounds(200,400,100,50);
		f.add(l);
		f.add(port_box);
		f.add(port);
		f.add(sp);
		f.add(text_box);
		f.add(send);
		f.setVisible(true);
		
		
		
		
	}
	public void socketServerUp(int port)throws Exception
	{
		serverSocket=new ServerSocket(port);	
			
	}
	
	public void socketConnect()throws Exception
	{
		Socket socket=serverSocket.accept();
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
	}
	public void read()throws Exception
	{
		String str=in.readLine();
		String msg=chat_area.getText()+"\nClient: "+str;
		chat_area.setText(msg);
	}
	public void write()throws Exception
	{
		String str=text_box.getText();
		String msg="\nServer: "+str;
		out.println(msg);
		chat_area.setText(chat_area.getText()+msg);
		text_box.setText("");
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		String cmd=ae.getActionCommand();
		
		if(cmd=="Host")
		{	
			l.setText(cmd);
			try {
				chat_area.setText("Setting Server up...");
				socketServerUp(Integer.parseInt(port_box.getText()));
				
				socketConnect();
				read();
				out.println("Connected to server");
				}catch (Exception e) {}
					
		}
		else if(cmd=="send")
		{
			try{write();read();}catch(Exception e) {}
			
			
		}
			
		}
	
	public static void main(String args[])
	{
		new server_gui();
		
	}
	

	
	
		
	}

