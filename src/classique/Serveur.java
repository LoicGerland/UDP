package classique;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import udp.ObjetConnecté;

public class Serveur extends ObjetConnecté {

	public Serveur(DatagramSocket ds) {
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
		while(true){
			try {
				receptionMessage();
				repondre("Message bien recu");
			} catch (UnsupportedEncodingException e) {
				System.out.println("Erreur de format");
			}
		}
	}

}
