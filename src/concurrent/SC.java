package concurrent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SC extends ServeurConcu implements Runnable {
	
	private InetAddress adrClient;
	private int portClient;
	
	public SC(DatagramSocket ds, InetAddress adrClient, int portClient) {
		super(ds);
		this.adrClient = adrClient;
		this.portClient = portClient;
	}
	
	 public void run()
	 {
		 String lastmsg ="";
		 try {
			repondre("Message Bien recu", this.adrClient, this.portClient);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		 
		 	while(lastmsg != "STOP")
		 	{
		 		try {
		 			receptionMessage();
		 			lastmsg = new String(this.packet.getData(), "ascii");
					repondre("Message bien recu");
		 		} catch (UnsupportedEncodingException e) {
		 			e.printStackTrace();
		 		}
		 	} 
	  }
	
		public void repondre(String msg, InetAddress adr, int port) throws UnsupportedEncodingException
		{
			
			this.packet = new DatagramPacket(msg.getBytes("ascii"),msg.length(),adr,port);
			try {
				this.socket.send(this.packet);
			} catch (IOException e) {
				System.out.println("Echec de l'envoi du Datagramme : Taille supérieur");
			}
		}

}
