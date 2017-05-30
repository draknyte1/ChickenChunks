package alkalus;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;

public class ChunkLoaderProxy {
	public void init() {

		// GameRegistry.registerTileEntity(TileChunkLoader.class,
		// "ChickenChunkLoader");
		// GameRegistry.registerTileEntity(TileSpotLoader.class,
		// "ChickenSpotLoader");

		// PacketCustom.assignHandler(ChunkLoaderSPH.channel, new
		// ChunkLoaderSPH());
		ChunkLoaderManager.initConfig(ChunkViewer.config);

		MinecraftForge.EVENT_BUS.register(new ChunkLoaderEventHandler());
		FMLCommonHandler.instance().bus().register(new ChunkLoaderEventHandler());
		ChunkLoaderManager.registerMod(ChunkViewer.instance);

	}

	public void registerCommands(FMLServerStartingEvent event) {
		CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
		commandManager.registerCommand(new CommandChunkLoaders());
		// commandManager.registerCommand(new CommandDebugInfo());
	}

}
