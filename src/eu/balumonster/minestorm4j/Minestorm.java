package eu.balumonster.minestorm4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import com.google.gson.Gson;

import eu.balumonster.minestorm4j.packets.PacketInNewSession;
import eu.balumonster.minestorm4j.packets.PacketInStartServer;
import eu.balumonster.minestorm4j.packets.PacketInStopServer;

public class Minestorm {

	private String session;
	private String host;
	private int port;
	public static final Logger LOGGER=Logger.getLogger("Minestorm");
	
	public Minestorm(String host, int port){
		this.host=host;
		this.port=port;
	}
	
	public boolean connect(){
		PacketInNewSession ns=new PacketInNewSession();
		ns.setStatus(PacketType.NEW_SESSION.getStatus());
		Packet ok=sendPacket(ns);
		if(Util.isInstance(ok, PacketType.OK)){
			LOGGER.info("Connected");
			//session=boh;
			return true;
		}else{
			LOGGER.warning("Connection Failed!");
			return false;
		}
	}
	
	public void startServer(String serverName, String flags){
		PacketInStartServer in=new PacketInStartServer();
		in.setStatus(PacketType.START_SERVER.getStatus());
		in.setSid(session);
		in.setServer(serverName);
		in.setFlags(flags);
		Packet result=sendPacket(in);
		if(!Util.isInstance(result, PacketType.OK)){
			LOGGER.warning("Operation failed: START SERVER");
		}
	}
	
	public void stopServer(String serverName){
		PacketInStopServer in=new PacketInStopServer();
		in.setStatus(PacketType.STOP_SERVER.getStatus());
		in.setSid(session);
		in.setServer(serverName);
		Packet result=sendPacket(in);
		if(!Util.isInstance(result, PacketType.OK)){
			LOGGER.warning("Operation failed: START SERVER");
		}
	}
	
	private Packet sendPacket(Packet packet){
		try {
			Socket socket=new Socket(host, port);
			PrintWriter writer=new PrintWriter(socket.getOutputStream(), true);
			BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Gson gson=new Gson();
			String jsonRequest= gson.toJson(packet);
			writer.print(jsonRequest);
			String jsonReponse=reader.readLine();
			PacketType reponseType= Util.parseType(jsonReponse);
			Packet result=gson.fromJson(jsonReponse, reponseType.getPacketClass());
			socket.close();
			return result;
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
