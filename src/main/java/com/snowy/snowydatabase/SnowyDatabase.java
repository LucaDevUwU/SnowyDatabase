package com.snowy.snowydatabase;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class SnowyDatabase extends JavaPlugin {

    private Database database;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        database = new Database();
        try {
            database.connect();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        playerManager = new PlayerManager();

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
    }

    @Override
    public void onDisable() {
        database.disconnect();
    }

    public Database getDatabase() { return database; }
    public PlayerManager getPlayerManager() { return playerManager; }

}
