package codechicken.chunkloader;

import java.util.List;

import codechicken.core.commands.PlayerCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;

public class CommandChunkLoaders extends PlayerCommand
{
    @Override
    public String getCommandName()
    {
        return "chunkloaders";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "chunkloaders";
    }

    @Override
    public void handleCommand(WorldServer world, EntityPlayerMP player, String[] args)
    {
        WCommandSender wrapped = new WCommandSender(player);
        if(PlayerChunkViewerManager.instance().isViewerOpen(player.getCommandSenderName()))
        {
            wrapped.chatT("command.chunkloaders.alreadyopen");
            return;
        }
        if(!ChunkLoaderManager.allowChunkViewer(player.getCommandSenderName()))
        {
            wrapped.chatT("command.chunkloaders.denied");
            return;
        }
        PlayerChunkViewerManager.instance().addViewers.add(player.getCommandSenderName());
    }
    
    @Override
    public void printHelp(WCommandSender listener)
    {
        listener.chatT("command.chunkloaders");
    }

    @Override
    public boolean OPOnly()
    {
        return false;
    }
    
    @Override
    public int minimumParameters()
    {
        return 0;
    }

	public List getCommandAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		// TODO Auto-generated method stub
		
	}

	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}
}
