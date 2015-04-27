package classique;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import udp.ObjetConnecté;

public class RunServeur {
	
	public static void main(String[] args) {
		ArrayList<Integer> ls;
		
		ls = ObjetConnecté.scanPort(1025, 2048);
		
		if (!ls.isEmpty())
		{
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(ls.get(0));
				System.out.println("Serveur démarrer sur le port : "+ls.get(0));
			} catch (SocketException e) {
				System.out.println("Aucun port disponible");
			}
			
		Serveur s = new Serveur(ds);
		
		s.run();
		}
		else
		{
			System.out.println("Aucun port disponible");
		}
	}

}
