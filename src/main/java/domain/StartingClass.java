package domain;

import command.commands.SearchLyricsCommand;
import listeners.MessageListener;
import music.SearchVideoOnYoutubeImpl;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sendmessages.SendMessageImpl;

import javax.security.auth.login.LoginException;
@SpringBootApplication
public class StartingClass {
    public static void main(String[] args) throws LoginException {
        SpringApplication.run(StartingClass.class);
        JDABuilder builder = JDABuilder.createDefault(new DiscordBotData().getDISCORD_TOKEN());
        SearchVideoOnYoutubeImpl search = new SearchVideoOnYoutubeImpl();
        search.initialize();
        SearchLyricsCommand.initialize();
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.listening("/play"));
        builder.addEventListeners(new MessageListener(new SendMessageImpl()));
        builder.build();


    }
}