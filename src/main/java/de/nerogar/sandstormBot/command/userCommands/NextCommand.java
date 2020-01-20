package de.nerogar.sandstormBot.command.userCommands;

import de.nerogar.sandstormBot.UserGroup;
import de.nerogar.sandstormBotApi.IGuildMain;
import de.nerogar.sandstormBotApi.command.CommandResults;
import de.nerogar.sandstormBotApi.command.ICommandResult;
import de.nerogar.sandstormBotApi.command.IUserCommand;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class NextCommand implements IUserCommand {

	@Override
	public boolean accepts(String command, String[] commandSplit) {
		if (command.isBlank()) return false;
		return commandSplit[0].equals("next");
	}

	@Override
	public UserGroup getMinUserGroup() {
		return UserGroup.GUEST;
	}

	@Override
	public void setCommandString(VoiceChannel voiceChannel, Member member, String command, String[] commandSplit) {
	}

	@Override
	public IUserCommand newInstance() {
		return new NextCommand();
	}

	@Override
	public ICommandResult execute(IGuildMain guildMain) {
		guildMain.getPlaylists().getCurrent().next();
		return CommandResults.success();
	}
}
