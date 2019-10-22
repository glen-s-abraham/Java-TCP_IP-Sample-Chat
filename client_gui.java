package tcpchat;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class client_gui implements ActionListener{
	JFrame f;
	JScrollPane sp;
	JLabel l;
	JTextField host_box,port_box,text_box;
	JTextArea chat_area;
	JButton port,send;
	ServerSocket serverSocket;
	BufferedReader in;
	PrintWriter out;
	String msg;
	client_gui()
	{
		f=new JFrame("Client");
		f.setSize(340, 500);
		f.setLayout(null);
		
		
		host_box=new JTextField("127.0.0.1");
		host_box.setBounds(10,20,100,50);
		port_box=new JTextField("666");
		port_box.setBounds(120,20,50,50);
		port=new JButton("Connect");
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
		
		f.add(host_box);
		f.add(port_box);
		f.add(port);
		f.add(sp);
		//f.add(chat_area);
		f.add(text_box);
		f.add(send);
		f.setVisible(true);
		
		
		
		
	}
	
	
	public void socketConnect(String address,int port)throws Exception
	{
		Socket socket=new Socket(address,port);
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
	}
	public void read()throws Exception
	{
		String str=in.readLine();
		String msg=chat_area.getText()+"\nServer: "+str;
		chat_area.setText(msg);
	}
	public void write()throws Exception
	{
		String str=text_box.getText();
		String msg="\nClient: "+str;
		out.println(msg);
		chat_area.setText(chat_area.getText()+msg);
		text_box.setText("");
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		String cmd=ae.getActionCommand();
		
		if(cmd=="Connect")
		{	
			
			try {
				
				socketConnect(host_box.getText(),Integer.parseInt(port_box.getText()));
				out.println("Client connected");
				read();
				}catch (Exception e) {}
					
		}
		else if(cmd=="send")
		{
			try{write();read();}catch(Exception e) {}
			
			
		}
			
		}
	
	public static void main(String args[])
	{
		new client_gui();
		
	}
	

	
	
		
	}

