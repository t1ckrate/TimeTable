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

package fr.t1ckrate.datamanager.mysql;

import fr.t1ckrate.database.DatabaseManager;
import fr.t1ckrate.datamanager.beans.EventBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventCalManager {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void newEvent(EventBean eventBean) {
        try {
            connection = DatabaseManager.SERVER.getDatabaseAccess().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO events(eventName, eventType, eventDesc, deadlineDate, channelId) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, eventBean.getEventName());
            preparedStatement.setString(2, eventBean.getEventType());
            preparedStatement.setString(3, eventBean.getEventDesc());
            preparedStatement.setDate(4, new Date(eventBean.getDeadLine().getTime()));
            preparedStatement.setLong(5, eventBean.getCalendarId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<EventBean> getEvents(long calendarId) {
        try {
            connection = DatabaseManager.SERVER.getDatabaseAccess().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM events WHERE channelId = ?");
            preparedStatement.setLong(1, calendarId);

            resultSet = preparedStatement.executeQuery();
            List<EventBean> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new EventBean(resultSet.getLong("eventId"),
                        resultSet.getString("eventName"),
                        resultSet.getString("eventType"),
                        resultSet.getString("eventDesc"),
                        resultSet.getDate("deadlineDate"),
                        resultSet.getLong("channelId")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
