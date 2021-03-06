package alkalus;

import java.util.ArrayList;
import java.util.List;

import codechicken.core.commands.PlayerCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;

public class CommandChunkLoaders extends PlayerCommand {
	@Override
	public String getCommandName() {
		return "chunkloaders";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "chunkloaders";
	}

	@Override
	public void handleCommand(WorldServer world, EntityPlayerMP player, String[] args) {
		System.out.println("Called ChunkLoaders Command.");
		WCommandSender wrapped = new WCommandSender(player);
		if (PlayerChunkViewerManager.instance().isViewerOpen(player.getCommandSenderName())) {
			wrapped.chatT("command.chunkloaders.alreadyopen");
			System.out.println("Called ChunkLoaders Command. Failed, Already open.");
			return;
		}
		if (!ChunkLoaderManager.allowChunkViewer(player.getCommandSenderName())) {
			System.out.println("Called ChunkLoaders Command. Failed, Denied.");
			wrapped.chatT("command.chunkloaders.denied");
			return;
		}
		System.out.println("ChunkLoaders Command, Processing.");
		PlayerChunkViewerManager.instance().addViewers.add(player.getCommandSenderName());
	}

	@Override
	public void printHelp(WCommandSender listener) {
		listener.chatT("command.chunkloaders");
	}

	@Override
	public boolean OPOnly() {
		return false;
	}

	@Override
	public int minimumParameters() {
		return 0;
	}

	public List getCommandAliases() {
		List<String> alias = new ArrayList<String>();
		alias.add("CV");
		alias.add("CL");
		alias.add("cv");
		alias.add("cl");
		return alias;
	}

	public void processCommand(ICommandSender sender, String[] randomArray) {
	System.out.println("Called ChunkLoaders Command. [1]");
	handleCommand(null, (EntityPlayerMP) sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()), randomArray);
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	public List<?> addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}
}
