package com.snowy.snowydatabase;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class ConnectionListener implements Listener {

    private SnowyDatabase main;

    public ConnectionListener(SnowyDatabase main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        try {
            CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());
            main.getPlayerManager().addCustomPlayer(player.getUniqueId(), playerData);
        } catch (SQLException ex) {
            player.kickPlayer("Your data could not get loaded!");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        main.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
    }
}
