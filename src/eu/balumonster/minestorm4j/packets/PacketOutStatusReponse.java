package eu.balumonster.minestorm4j.packets;

import eu.balumonster.minestorm4j.Packet;
import lombok.Getter;
import lombok.Setter;

@Deprecated
public class PacketOutStatusReponse extends Packet{

	@Getter @Setter
	public Server[] servers;
	
	public static class Server{
		
		@Getter @Setter
		public String status;
		
		@Getter @Setter
		public long started_at;
		
		@Getter @Setter
		public int uptime;
		
		@Getter @Setter
		public double ram;
	}
	
}
