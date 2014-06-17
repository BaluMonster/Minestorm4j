package eu.balumonster.minestorm4j;

import java.util.HashMap;

import eu.balumonster.minestorm4j.packets.PacketInChangeFocus;
import eu.balumonster.minestorm4j.packets.PacketInCommand;
import eu.balumonster.minestorm4j.packets.PacketInNewSession;
import eu.balumonster.minestorm4j.packets.PacketInPing;
import eu.balumonster.minestorm4j.packets.PacketInRemoveSession;
import eu.balumonster.minestorm4j.packets.PacketInStartServer;
import eu.balumonster.minestorm4j.packets.PacketInStatus;
import eu.balumonster.minestorm4j.packets.PacketInStopServer;
import eu.balumonster.minestorm4j.packets.PacketInUpdate;
import eu.balumonster.minestorm4j.packets.PacketOutFailed;
import eu.balumonster.minestorm4j.packets.PacketOutInvalidRequest;
import eu.balumonster.minestorm4j.packets.PacketOutOk;
import eu.balumonster.minestorm4j.packets.PacketOutPong;
import eu.balumonster.minestorm4j.packets.PacketOutSessionCreated;
import eu.balumonster.minestorm4j.packets.PacketOutStatusReponse;
import eu.balumonster.minestorm4j.packets.PacketOutUpdates;

@SuppressWarnings("deprecation")
public enum PacketType {
	
	PING(PacketInPing.class, "ping"),
	NEW_SESSION(PacketInNewSession.class, "new_session"),
	REMOVE_SESSION(PacketInRemoveSession.class, "remove_session"),
	CHANGE_FOCUS(PacketInChangeFocus.class, "change_focus"),
	START_SERVER(PacketInStartServer.class, "start_server"),
	STOP_SERVER(PacketInStopServer.class, "stop_server"),
	COMMAND(PacketInCommand.class, "command"),
	STATUS(PacketInStatus.class, "status"),
	UPDATE(PacketInUpdate.class, "update"),
	INVALID_REQUEST(PacketOutInvalidRequest.class, "invalid_request"),
	PONG(PacketOutPong.class, "pong"),
	OK(PacketOutOk.class, "ok"),
	FAILED(PacketOutFailed.class, "failed"),
	SESSION_CREATED(PacketOutSessionCreated.class, "session_created"),
	STATUS_REPONSE(PacketOutStatusReponse.class, "status_reponse"),
	UPDATES(PacketOutUpdates.class, "updates");

	private static HashMap<String, PacketType> statusToPacketType=new HashMap<>();
	
	public static PacketType getPacketType(String status){
		return statusToPacketType.get(status);
	}
	
	static {
		for (PacketType p:values()){
			statusToPacketType.put(p.getStatus(), p);
		}
	}
	
	private Class<? extends Packet> clazz;
	private String status;
	
	private PacketType(Class<? extends Packet> clazz, String statusString){
		this.clazz=clazz;
		this.status=statusString;
	}
	
	public Class<? extends Packet> getPacketClass(){
		return clazz;
	}
	
	public String getStatus(){
		return status;
	}
	
}
