package codechicken.chunkloader;

import static codechicken.chunkloader.ChickenChunks.config;

import codechicken.core.CCUpdateChecker;
import codechicken.core.ClientUtils;
import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ChunkLoaderClientProxy  extends ChunkLoaderProxy
{
    @Override
    public void init()
    {
        if(config.getTag("checkUpdates").getBooleanValue(true))
            CCUpdateChecker.updateCheck("ChickenChunks");
        ClientUtils.enhanceSupportersList("ChickenChunks");
        
        super.init();

        PacketCustom.assignHandler(ChunkLoaderCPH.channel, new ChunkLoaderCPH());
        
        //ClientRegistry.bindTileEntitySpecialRenderer(TileChunkLoader.class, new TileChunkLoaderRenderer());
        //ClientRegistry.bindTileEntitySpecialRenderer(TileSpotLoader.class, new TileChunkLoaderRenderer());
        RenderingRegistry.registerBlockHandler(new ChunkLoaderSBRH());
    }
    
    @Override
    public void openGui(TileChunkLoader tile)
    {
        //Minecraft.getMinecraft().displayGuiScreen(new GuiChunkLoader(tile));
    }
}
