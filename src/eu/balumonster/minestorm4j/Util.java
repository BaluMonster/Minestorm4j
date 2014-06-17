package eu.balumonster.minestorm4j;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Util {

	static boolean isInstance(Packet packet, PacketType type){
		return PacketType.valueOf(packet.getStatus().toUpperCase()).equals(type);
	}
	
	static PacketType parseType(String jsonString){
		JsonObject jobj=new JsonParser().parse(jsonString).getAsJsonObject();
		String status=jobj.get("status").getAsString();
		return PacketType.getPacketType(status);
	}
	
}
