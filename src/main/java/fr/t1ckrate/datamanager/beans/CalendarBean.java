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

package fr.t1ckrate.datamanager.beans;

public class CalendarBean {
    public String calendarTitle;
    public String calendarUrl;
    public long messageId;
    public long channelId;
    public long guildId;

    public CalendarBean(String calendarTitle, String calendarUrl, long messageId, long channelId, long guildId) {
        this.calendarTitle = calendarTitle;
        this.calendarUrl = calendarUrl;
        this.messageId = messageId;
        this.channelId = channelId;
        this.guildId = guildId;
    }

    public CalendarBean(String calendarTitle, String calendarUrl, long channelId, long guildId) {
        this.calendarTitle = calendarTitle;
        this.calendarUrl = calendarUrl;
        this.channelId = channelId;
        this.guildId = guildId;
    }

    public String getCalendarTitle() {
        return calendarTitle;
    }

    public String getCalendarUrl() {
        return calendarUrl;
    }

    public long getChannelId() {
        return channelId;
    }

    public long getGuildId() {
        return guildId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setCalendarTitle(String calendarTitle) {
        this.calendarTitle = calendarTitle;
    }

    public void setCalendarUrl(String calendarUrl) {
        this.calendarUrl = calendarUrl;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
    }
}
