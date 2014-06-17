package eu.balumonster.minestorm4j.packets;

import lombok.Getter;
import lombok.Setter;
import eu.balumonster.minestorm4j.Packet;

public class PacketOutSessionCreated extends Packet{

	@Getter @Setter
	public String sid;
	
}
