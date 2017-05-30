package codechicken.chunkloader;

import codechicken.lib.packet.PacketCustom;

public class ChunkLoaderClientProxy extends ChunkLoaderProxy {
	@Override
	public void init() {
		super.init();
		PacketCustom.assignHandler(ChunkViewer.channel, new ChunkLoaderCPH());
	}

}
