package com.snowy.snowydatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {

    private SnowyDatabase main;

    private UUID uuid;
    private String rank;
    private int coins;

    public CustomPlayer(SnowyDatabase main, UUID uuid) throws SQLException {
        this.main = main;

        this.uuid = uuid;

        PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("SELECT RANK, COINS FROM players WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            rank = rs.getString("RANK");
            coins = rs.getInt("COINS");
        } else {
            rank = "GUEST";
            coins = 0;
            PreparedStatement statement1 = main.getDatabase().getConnection().prepareStatement("INSERT INTO (ID, UUID, RANK, COINS) VALUES (" +
                    "default," +
                    "'" + uuid + "," +
                    "'" + rank + "," +
                    coins + ");");
            statement1.executeUpdate();
        }
    }

    public void setRank(String rank) {
        this.rank = rank;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("UPDATE players SET RANK = '" + rank + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCoins(int coins) {
        this.coins = coins;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("UPDATE players SET COINS = " + coins + " WHERE COINS = " + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRank() { return rank; }
    public int getCoins() { return coins; }
}
