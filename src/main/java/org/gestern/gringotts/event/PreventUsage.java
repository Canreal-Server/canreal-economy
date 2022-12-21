package org.gestern.gringotts.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PreventUsage implements Listener {
    @EventHandler
    public void onItemThrow(PlayerInteractEvent e) {
        Player player = (Player) e;
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§a1x Flüssige Emeralds §7= §a100 Emeralds")) {
                e.setCancelled(true);
                player.sendMessage("Du solltest nicht dein Geld wegwerfen...");
            }
        }
    }
}
