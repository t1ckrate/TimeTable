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
import fr.t1ckrate.datamanager.beans.EventBean;
import fr.t1ckrate.datamanager.beans.GuildBean;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EventAddCommand extends ListenerAdapter {
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event){
        if(event.getAuthor().isBot())
            return;

        Message message = event.getMessage();

        String prefix = String.valueOf(message.getContentRaw().charAt(0));
        if(!prefix.equalsIgnoreCase(Main.dataGuildManager.prefixCache.get(event.getGuild().getIdLong())))
            return;

        String[] args = message.getContentRaw().split(" ");
        String command = args[0].substring(1);
        if(command.startsWith("globaladd")){
            GuildBean guildBean = Main.dataGuildManager.getGuildProperties(event.getGuild().getIdLong());
            if(!guildBean.getPrefix().equals(String.valueOf(message.getContentRaw().charAt(0))))
                return;

            event.getMessage().delete().queueAfter(3, TimeUnit.SECONDS);

            if(args.length <= 2){
                event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                        " **»** Merci de préciser les paramètres, séparé par des ;. *(Syntaxe: " + prefix + "globalAdd; <Nom>; <Description>; <Date (dd-MM-yyyy HH:mm)>; <Type>)*").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS));
                return;
            }

            String[] eventParam = message.getContentRaw().split(";");

            if(eventParam[1].length() > 50 && eventParam[2].length() > 250){
                event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                        " **»** Des éléments de votre évènement sont trop longs. *(Syntaxe: " + prefix + "globalAdd; <Nom>; <Description>; <Date (dd-MM-yyyy HH:mm>); <Type>)*").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS));
                return;
            }

            try{
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date date = sdf.parse(eventParam[3]);
                CalendarBean calendarBean = Main.calendarManager.getCalendarByChannelId(event.getChannel().getIdLong());
                if (calendarBean == null) {
                    event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                            " **»** Il n'y a pas de calendrier dans ce canal.").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS));
                    return;
                }

                Main.eventCalManager.newEvent(new EventBean(eventParam[1], eventParam[4], eventParam[2], date, event.getChannel().getIdLong()));

                Main.discordCalManager.handleCalendarEvents(calendarBean, message.getTextChannel()).subscribe(aLong -> event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                        " **»** L'évènement a bien été crée.").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS)));

            } catch (ParseException e) {
                e.printStackTrace();
                event.getChannel().sendMessage(message.getAuthor().getAsMention() +
                        " **»** La date ou l'identifiant de votre évènement n'est pas correcte. *(Syntaxe: " + prefix + "globalAdd; <Nom>; <Description>; Date (<dd-MM-yyyy HH:mm>); <Type>)*").submit().whenComplete((message1, throwable) -> message1.delete().queueAfter(3, TimeUnit.SECONDS));

            }


        }
    }
}
