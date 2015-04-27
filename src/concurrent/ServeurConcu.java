package concurrent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import udp.ObjetConnecté;

public class ServeurConcu extends ObjetConnecté {

	public ServeurConcu(DatagramSocket ds) {
		super(ds);
	}
	
	public void repondre(String msg) throws UnsupportedEncodingException
	{
		
		this.packet = new DatagramPacket(msg.getBytes("ascii"),msg.length(),this.packet.getAddress(),this.packet.getPort());
		try {
			this.socket.send(this.packet);
		} catch (IOException e) {
			System.out.println("Echec de l'envoi du Datagramme : Taille supérieur");
		}
	}
	
	public void run()
	{
		ArrayList<Integer> ls;
		
		while(true){
			try {
				receptionMessage();
			} catch (UnsupportedEncodingException e) {
				System.out.println("Erreur de format");
			}
			
			ls = ObjetConnecté.scanPort(1025, 2025);
			if (!ls.isEmpty())
			{
				DatagramSocket ds = null;
				try {
					ds = new DatagramSocket(ls.get(0));
				} catch (SocketException e) {
					System.out.println("Aucun port disponible pour le thread");
				}
				
			new Thread(new SC(ds,this.packet.getAddress(), this.packet.getPort())).start();
			}
			else
			{
				System.out.println("Aucun port disponible pour le thread");
			}
		}
	}

}
