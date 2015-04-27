package udp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends ObjetConnecté {
	
	

	public Client(DatagramSocket ds) {
		super(ds);
	}
	
	public boolean connexionServeur()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Saisir l'adresse du serveur : ");
	    String adrServeur = sc.nextLine();
	    System.out.println("Saisir le port du serveur : ");
	    int portServeur = sc.nextInt();
	    
		try {
			envoiMessage("Demande connexion", adrServeur, portServeur);
		} catch (UnsupportedEncodingException | UnknownHostException e) {
			System.out.println("Erreur de format");
		}
		
		try {
			receptionMessage();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Erreur de format");
		}
		return true;
	}
	
	public void envoiMessage(String msg, String adr, int port) throws UnknownHostException, UnsupportedEncodingException
	{
		InetAddress ia = InetAddress.getByName(adr);
		this.packet = new DatagramPacket(msg.getBytes("ascii"),msg.length(),ia,port);
		
		try {
			this.socket.send(this.packet);
		} catch (IOException e) {
			System.out.println("Echec de l'envoi du Datagramme : Taille supérieur");
		}
	}
	
	public void envoiMessage(String msg, InetAddress adr, int port) throws UnknownHostException, UnsupportedEncodingException
	{
		this.packet = new DatagramPacket(msg.getBytes("ascii"),msg.length(),adr,port);
		
		try {
			this.socket.send(this.packet);
		} catch (IOException e) {
			System.out.println("Echec de l'envoi du Datagramme : Taille supérieur");
		}
	}
	
	public void run()
	{
		if(connexionServeur())
		{			
			while (true)
			{
				Scanner sc = new Scanner(System.in);
			
				System.out.println("Saisir le message à envoyer : ");
				String msg = sc.nextLine();
			
				try {
					envoiMessage(msg, this.packet.getAddress(), this.packet.getPort());
				} catch (UnsupportedEncodingException | UnknownHostException e) {
					System.out.println("Erreur de format");
				}
				
				try {
					receptionMessage();
				} catch (UnsupportedEncodingException e) {
					System.out.println("Erreur de format");
				}
			}
		}
		else
		{
			System.out.println("Serveur non accessible");
		}
	}

}
