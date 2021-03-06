package eu.balumonster.minestorm4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import com.google.gson.Gson;

import eu.balumonster.minestorm4j.packets.PacketInNewSession;
import eu.balumonster.minestorm4j.packets.PacketInPing;
import eu.balumonster.minestorm4j.packets.PacketInStartServer;
import eu.balumonster.minestorm4j.packets.PacketInStopServer;
import eu.balumonster.minestorm4j.packets.PacketOutSessionCreated;

public class Minestorm {

	private String session;
	private String host;
	private int port;
	private int timeout;
	public static final Logger LOGGER=Logger.getLogger("Minestorm");
	
	
	/**
	 * Minestorm constructor
	 * 
	 * @param host Minestorm server host
	 * @param port Minestorm server port
	 * @param timeout Connections timeout in milliseconds
	 */
	public Minestorm(String host, int port, int timeout){
		this.host=host;
		this.port=port;
		this.timeout=timeout;
	}
	
	/**
	 * 
	 * @return <b>true</b> if connection is successful
	 */
	public boolean connect(){
		PacketInNewSession ns=new PacketInNewSession();
		ns.setStatus(PacketType.NEW_SESSION.getStatus());
		Packet ok=sendPacket(ns);
		if(Util.isInstance(ok, PacketType.SESSION_CREATED)){
			PacketOutSessionCreated out=(PacketOutSessionCreated) ok;
			LOGGER.info("Connected");
			session=out.getSid(); 
			return true;
		}else{
			LOGGER.warning("Connection Failed!");
			return false;
		}
	}
	
	public void ping(){
		PacketInPing ping=new PacketInPing();
		ping.setStatus(PacketType.PING.getStatus());
		ping.setSid(session);
		sendPacket(ping);
	}
	
	/**
	 * Starts the selected minecraft server
	 * 
	 * @param serverName minecraft server name
	 * @param flags Java flags
	 */ 
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
	
	/**
	 * Stops the selected minecraft server
	 * 
	 * @param serverName minecraft server name
	 */
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
			socket.setSoTimeout(timeout);
			BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Gson gson=new Gson();
			String jsonRequest= gson.toJson(packet);
			System.out.println(jsonRequest);
			socket.getOutputStream().write(jsonRequest.getBytes());
			String jsonReponse=reader.readLine();
			System.out.println(jsonReponse);
			PacketType reponseType= Util.parseType(jsonReponse);
			if(reponseType==null)System.out.println("null");
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
