package codechicken.chunkloader;

import static codechicken.chunkloader.ChickenChunks.blockChunkLoader;
import static codechicken.chunkloader.ChickenChunks.config;
import static codechicken.chunkloader.ChickenChunks.instance;

import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ChunkLoaderProxy
{
    public void init()
    {
        blockChunkLoader = new BlockChunkLoader();
        blockChunkLoader.setBlockName("chickenChunkLoader").setCreativeTab(CreativeTabs.tabMisc);
        GameRegistry.registerBlock(blockChunkLoader, ItemChunkLoader.class, "chickenChunkLoader");
        
       // GameRegistry.registerTileEntity(TileChunkLoader.class, "ChickenChunkLoader");
       // GameRegistry.registerTileEntity(TileSpotLoader.class, "ChickenSpotLoader");
        
        PacketCustom.assignHandler(ChunkLoaderSPH.channel, new ChunkLoaderSPH());
        ChunkLoaderManager.initConfig(config);
        
        MinecraftForge.EVENT_BUS.register(new ChunkLoaderEventHandler());
        FMLCommonHandler.instance().bus().register(new ChunkLoaderEventHandler());
        ChunkLoaderManager.registerMod(instance);
        
        GameRegistry.addRecipe(new ItemStack(blockChunkLoader, 1, 0), 
            " p ",
            "ggg",
            "gEg",
            'p', Items.ender_pearl,
            'g', Items.gold_ingot,
            'd', Items.diamond,
            'E', Blocks.enchanting_table
        );
        
        GameRegistry.addRecipe(new ItemStack(blockChunkLoader, 10, 1), 
                "ppp",
                "pcp",
                "ppp",
                'p', Items.ender_pearl,
                'c', new ItemStack(blockChunkLoader, 1, 0)
        );
    }
    
    public void registerCommands(FMLServerStartingEvent event)
    {
        CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
        commandManager.registerCommand(new CommandChunkLoaders());
        commandManager.registerCommand(new CommandDebugInfo());
    }

    public void openGui(TileChunkLoader tile)
    {
    }
}
