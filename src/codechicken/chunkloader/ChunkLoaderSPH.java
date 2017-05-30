package codechicken.chunkloader;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IServerPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ChunkLoaderSPH implements IServerPacketHandler {

	@Override
	public void handlePacket(PacketCustom packet, EntityPlayerMP sender, INetHandlerPlayServer handler) {
		switch (packet.getType()) {
			case 1:
				PlayerChunkViewerManager.instance().closeViewer(sender.getCommandSenderName());
				break;
			case 2:
				handleChunkLoaderChangePacket(sender.worldObj, packet);
				break;

		}
	}

	private void handleChunkLoaderChangePacket(World world, PacketCustom packet) {
		TileEntity tile = world.getTileEntity(packet.readInt(), packet.readInt(), packet.readInt());
	}
}
