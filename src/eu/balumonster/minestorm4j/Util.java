package eu.balumonster.minestorm4j;

public class Util {

	static boolean isInstance(Packet packet, PacketType type){
		return PacketType.valueOf(packet.getStatus().toUpperCase()).equals(type);
	}
	
	static PacketType parseType(String jsonString){
		return null;
	}
	
}
