package eu.balumonster.minestorm4j.packets;

import eu.balumonster.minestorm4j.Packet;
import lombok.Getter;
import lombok.Setter;

@Deprecated
public class PacketOutStatusReponse extends Packet{

	public static class Servers{
		@Getter @Setter
		public Server server;
	}
	
	public static class Server{
		
	}
	
}
