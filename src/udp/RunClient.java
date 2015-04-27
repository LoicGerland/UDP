package udp;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class RunClient {

	public static void main(String[] args) {
		ArrayList<Integer> ls;
		
		ls = ObjetConnecté.scanPort(1025, 2048);
		
		if (!ls.isEmpty())
		{
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(ls.get(0));
			} catch (SocketException e) {
				System.out.println("Aucun port disponible");
			}
			
		Client c = new Client(ds);
		c.run();
		}
		else
		{
			System.out.println("Aucun port disponible");
		}

	}
}
