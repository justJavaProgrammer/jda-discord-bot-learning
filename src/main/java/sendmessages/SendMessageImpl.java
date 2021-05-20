package sendmessages;

import com.sun.xml.internal.ws.api.message.Message;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class SendMessageImpl implements SendMessageInterface {

    @Override
    public void sendMessage(GuildMessageReceivedEvent event, String answer) {
        event.getChannel().sendMessage(answer).queue();
    }
}
