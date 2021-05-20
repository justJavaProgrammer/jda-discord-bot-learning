package domain;

import com.sun.corba.se.spi.activation.Server;
import listeners.MessageListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import sendmessages.SendMessageImpl;

import javax.print.attribute.standard.Compression;
import javax.security.auth.login.LoginException;

public class StartingClass {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(new DiscordBotData().getDISCORD_TOKEN());

        //builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.playing("with your shit"));

        builder.addEventListeners(new MessageListener(new SendMessageImpl()));
        builder.build();

    }
}