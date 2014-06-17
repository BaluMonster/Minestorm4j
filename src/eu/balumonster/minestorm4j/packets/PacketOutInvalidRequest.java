package eu.balumonster.minestorm4j.packets;

import eu.balumonster.minestorm4j.Packet;
import lombok.Getter;
import lombok.Setter;

public class PacketOutInvalidRequest extends Packet{

	@Getter @Setter
	public String reason;
	
}
