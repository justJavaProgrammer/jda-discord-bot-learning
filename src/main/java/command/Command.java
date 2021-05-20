package command;

import exceptions.SoundNotFoundException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface Command {

    void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException;
}
