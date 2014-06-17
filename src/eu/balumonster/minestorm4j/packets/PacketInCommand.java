package eu.balumonster.minestorm4j.packets;

import eu.balumonster.minestorm4j.Packet;
import lombok.Getter;
import lombok.Setter;

public class PacketInCommand extends Packet{

	@Getter @Setter
	public String sid;
	
	@Getter @Setter
	public String command;
	
}
