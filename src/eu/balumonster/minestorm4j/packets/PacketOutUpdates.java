package eu.balumonster.minestorm4j.packets;

import lombok.Getter;
import lombok.Setter;
import eu.balumonster.minestorm4j.Packet;

public class PacketOutUpdates extends Packet{

	@Getter @Setter
	public String[] lines;
	
	@Getter @Setter
	public UpdateBox[] boxes;
	
	@Getter @Setter
	public UpdateServer[] servers;
	
	@Getter @Setter
	public String focus;
	
	@Getter @Setter
	public double ram_used;
	
	public static class UpdateBox{
		
		@Getter @Setter
		public String name;
		
		@Getter @Setter
		public Object value;
		
		@Getter @Setter
		public String type;
		
	}
	
	public static class UpdateServer{
		
		@Getter @Setter
		public String name;
		
		@Getter @Setter
		public boolean online;
		
	}
	
}
