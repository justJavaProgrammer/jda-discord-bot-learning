package disconnect;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface Disconnectable {

    void disconnect(GuildMessageReceivedEvent event);
}
