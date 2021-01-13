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
import fr.t1ckrate.datamanager.beans.GuildBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataGuildManager {

    public Map<Long, String> prefixCache = new HashMap<>();

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public void newGuild(long guildId) {
        try {
            connection = DatabaseManager.SERVER.getDatabaseAccess().getConnection();
            statement = connection.prepareStatement("INSERT INTO guilds(guildId, prefix) VALUES (?, ?)");
            statement.setLong(1, guildId);
            statement.setString(2, "-");

            statement.executeUpdate();
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

    public GuildBean getGuildProperties(long guildId) {
        try {
            connection = DatabaseManager.SERVER.getDatabaseAccess().getConnection();
            statement = connection.prepareStatement("SELECT * FROM guilds WHERE guildId = ?");
            statement.setLong(1, guildId);

            resultSet = statement.executeQuery();
            GuildBean guildBean;
            if(!resultSet.next()){
                newGuild(guildId);
                guildBean = new GuildBean(guildId, "-");
            }
            else{
                guildBean = new GuildBean(guildId, resultSet.getString("prefix"));
            }
            return guildBean;
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

    public void editGuildProperties(GuildBean guildBean) {
        try {
            connection = DatabaseManager.SERVER.getDatabaseAccess().getConnection();
            statement = connection.prepareStatement("UPDATE guilds SET prefix = ? WHERE guildId = ?");
            statement.setString(1, guildBean.getPrefix());
            statement.setLong(2, guildBean.getGuildId());

            statement.executeUpdate();
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

    public void cacheGuildPrefix() {
        try {
            connection = DatabaseManager.SERVER.getDatabaseAccess().getConnection();
            statement = connection.prepareStatement("SELECT * FROM guilds");

            resultSet = statement.executeQuery();
            while(resultSet.next()){
                prefixCache.put(resultSet.getLong("guildId"), resultSet.getString("prefix"));
            }
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

}
