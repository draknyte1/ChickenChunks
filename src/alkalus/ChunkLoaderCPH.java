package alkalus;

import codechicken.core.CommonUtils;
import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IClientPacketHandler;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;

public class ChunkLoaderCPH implements IClientPacketHandler {
	@Override
	public void handlePacket(PacketCustom packet, Minecraft mc, INetHandlerPlayClient handler) {
		switch (packet.getType()) {
			case 1:
				PlayerChunkViewer.openViewer((int) mc.thePlayer.posX, (int) mc.thePlayer.posZ,
						CommonUtils.getDimension(mc.theWorld));
				break;
			case 2:
				PlayerChunkViewer.instance().loadDimension(packet, mc.theWorld);
				break;
			case 3:
				PlayerChunkViewer.instance().unloadDimension(packet.readInt());
				break;
			case 4:
				PlayerChunkViewer.instance().handleChunkChange(packet.readInt(),
						new ChunkCoordIntPair(packet.readInt(), packet.readInt()), packet.readBoolean());
				break;
			case 5:
				PlayerChunkViewer.instance().handleTicketChange(packet.readInt(), packet.readInt(),
						new ChunkCoordIntPair(packet.readInt(), packet.readInt()), packet.readBoolean());
				break;
			case 6:
				PlayerChunkViewer.instance().handlePlayerUpdate(packet.readString(), packet.readInt(),
						new Vector3(packet.readFloat(), packet.readFloat(), packet.readFloat()));
				break;
			case 7:
				PlayerChunkViewer.instance().removePlayer(packet.readString());
				break;
			case 8:
				PlayerChunkViewer.instance().handleNewTicket(packet, mc.theWorld);
				break;
			case 10:
				// TileChunkLoader.handleDescriptionPacket(packet, mc.theWorld);
				break;
			case 11:
				// TileSpotLoader.handleDescriptionPacket(packet, mc.theWorld);
				break;
			case 12:
				BlockCoord pos = packet.readCoord();
				TileEntity tile = mc.theWorld.getTileEntity(pos.x, pos.y, pos.z);
				break;

		}
	}

	public static void sendGuiClosing() {
		try{
			System.out.println("Creating a PacketCustom in "+"ChunkLoaderCPH"+"."+"sendGuiClosing");
		PacketCustom packet = new PacketCustom(ChunkViewer.channel, 1);
		packet.sendToServer();
		} catch (NullPointerException n){
			
		}
	}

}
