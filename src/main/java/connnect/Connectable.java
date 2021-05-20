package connnect;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface Connectable {

    void connectToTheVoiceChannel(GuildMessageReceivedEvent event);
}
