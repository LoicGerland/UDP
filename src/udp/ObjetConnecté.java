package udp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;


public class ObjetConnecté {

	protected DatagramSocket socket = null;
	
	protected DatagramPacket packet = null;
	
	public ObjetConnecté(DatagramSocket ds)
	{
		this.socket = ds;
	}
	

	/**
	 * Rend la liste des ports disponibles
	 *
	 * @param portDebut début de la plage de ports
	 * @param portFin fin de la plage
	 * @return ArrayList des ports disponibles
	 */
	public static ArrayList<Integer> scanPort(int portDebut, int portFin)
	{
		boolean portOK = true;
		ArrayList<Integer> ls = new ArrayList<Integer>();
		DatagramSocket ds = null;
		
		for (int i = portDebut;i <= portFin; i++)
		{
			try
			{
				ds = new DatagramSocket(i);	
			}
			catch(SocketException e)
			{
				portOK = false;
			}
			
			
			if(portOK) 
			{
				ds.close();
				ls.add(i);
			}				
			
			portOK = true;
		}
		return ls;
	}
	
	public void receptionMessage() throws UnsupportedEncodingException
	{
		byte[] data = new byte[25];
		this.packet = new DatagramPacket(data,25);
		
		try {
			this.socket.receive(this.packet);
		} catch (IOException e) {
			System.out.println("Erreur dans la reception du message : Datagramme trop grand");
			return;
		}
		
		System.out.println("Message de "+ this.packet.getAddress() + " , " + this.packet.getPort() + " message : "
		+ new String(this.packet.getData(), "ascii"));
	}
}
