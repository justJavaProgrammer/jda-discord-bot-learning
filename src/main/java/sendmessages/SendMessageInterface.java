package sendmessages;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface SendMessageInterface {

    void sendMessage(GuildMessageReceivedEvent event, String answer);
}
