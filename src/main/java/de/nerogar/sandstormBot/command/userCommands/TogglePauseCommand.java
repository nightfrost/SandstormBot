package de.nerogar.sandstormBot.command.userCommands;

import de.nerogar.sandstormBot.UserGroup;
import de.nerogar.sandstormBot.opusPlayer.PlayerState;
import de.nerogar.sandstormBotApi.IGuildMain;
import de.nerogar.sandstormBotApi.command.CommandResults;
import de.nerogar.sandstormBotApi.command.ICommandResult;
import de.nerogar.sandstormBotApi.command.IUserCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class TogglePauseCommand implements IUserCommand {

	@Override
	public boolean accepts(String command, String[] commandSplit) {
		if (command.isBlank()) return false;
		return commandSplit[0].equals("togglepause");
	}

	@Override
	public UserGroup getMinUserGroup() {
		return UserGroup.OWNER;
	}

	@Override
	public void setCommandString(VoiceChannel voiceChannel, Member member, String command, String[] commandSplit) {
	}

	@Override
	public IUserCommand newInstance() {
		return new TogglePauseCommand();
	}

	@Override
	public ICommandResult execute(IGuildMain guildMain) {
		if (guildMain.getPlayer().getState() == PlayerState.PLAYING) {
			guildMain.getPlayer().pause();
		} else {
			guildMain.getPlayer().play(false);
		}
		return CommandResults.success();
	}
}