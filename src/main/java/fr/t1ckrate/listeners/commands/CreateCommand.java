/*
 * MIT License
 *
 * Copyright (c) 2021 Thomas PERROT / t1ckrate (https://github.com/t1ckrate).
 * Mailing at mail@t1ckrate.fr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders X be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software.
 * Except as contained in this notice, the name of copyright holder (Thomas PERROT) shall not be used in advertising or otherwise to promote the sale, use or other dealings in this Software without prior written authorization from the copyright holder (Thomas PERROT).
 *
 */

package fr.t1ckrate.listeners.commands;

import fr.t1ckrate.Main;
import fr.t1ckrate.datamanager.beans.CalendarBean;
import fr.t1ckrate.datamanager.beans.GuildBean;
import fr.t1ckrate.messages.EmbedMessages;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class CreateCommand extends ListenerAdapter {
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event){
        if(event.getAuthor().isBot())
            return;

        Message message = event.getMessage();

        String prefix = String.valueOf(message.getContentRaw().charAt(0));
        if(!prefix.equalsIgnoreCase(Main.dataGuildManager.prefixCache.get(event.getGuild().getIdLong())))
            return;


        String[] args = message.getContentRaw().split(" ");
        String command = args[0].substring(1);
        if(command.equals("create")){
            GuildBean guildBean = Main.dataGuildManager.getGuildProperties(event.getGuild().getIdLong());
            if(!guildBean.getPrefix().equals(String.valueOf(message.getContentRaw().charAt(0))))
                return;

            event.getMessage().delete().queueAfter(3, TimeUnit.SECONDS);

            if(args.length <= 2){
                event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                        " **»** Merci de préciser un URL et un titre. *(Syntaxe: " + prefix + "create <URL> <Titre>)*").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS));
                return;
            }

            String title = "";
            for(int i = 2; i < args.length; i++){
                title += args[i];
            }

            if(title.length() > 50){
                event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                        " **»** Votre titre de calendrier est **trop long**. *(Syntaxe: " + prefix + "create <URL> <Titre>)*").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS));
                return;
            }

            String url = args[1];
            CalendarBean calendarBean = Main.calendarManager.getCalendarByChannelId(message.getChannel().getIdLong());
            if (calendarBean != null){
                event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                        " **»** Le calendrier existe déjà.").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS));
                return;
            }

            final CalendarBean calendarBean1 = new CalendarBean(title, url, event.getChannel().getIdLong(), event.getGuild().getIdLong());
            Main.discordCalManager.handleCalendarEvents(calendarBean1, event.getChannel()).subscribe(messageId -> {
                    calendarBean1.setMessageId(messageId);
                    Main.calendarManager.newCalendar(calendarBean1);
                    event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                            " **»** Le calendrier a été crée.").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS));

            });
        }
    }
}
