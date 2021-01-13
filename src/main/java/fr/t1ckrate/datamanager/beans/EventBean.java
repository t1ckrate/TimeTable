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

import java.util.Date;

public class EventBean {
    long UUID;
    String eventName;
    String eventType;
    String eventDesc;
    Date deadLine;
    long calendarId;

    public EventBean(long UUID, String eventName, String eventType, String eventDesc, Date deadLine, Long calendarId) {
        this.UUID = UUID;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDesc = eventDesc;
        this.deadLine = deadLine;
        this.calendarId = calendarId;
    }

    public EventBean(String eventName, String eventType, String eventDesc, Date deadLine, Long calendarId) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDesc = eventDesc;
        this.deadLine = deadLine;
        this.calendarId = calendarId;
    }



    public long getUUID() {
        return UUID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public void setUUID(long UUID) {
        this.UUID = UUID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }
}
