package codechicken.chunkloader;

import java.util.List;

import codechicken.core.commands.CoreCommand;
import net.minecraft.command.ICommandSender;

public class CommandDebugInfo extends CoreCommand
{
    @Override
    public String getCommandName() {
        return "ccdebug";
    }

    @Override
    public boolean OPOnly() {
        return false;
    }

    @Override
    public void handleCommand(String command, String playername, String[] args, WCommandSender listener) {

    }

    @Override
    public void printHelp(WCommandSender listener) {
        listener.chatT("command.ccdebug");
    }

    @Override
    public int minimumParameters() {
        return 0;
    }

	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return null;
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
