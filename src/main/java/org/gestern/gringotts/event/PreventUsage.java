package org.gestern.gringotts.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.gestern.gringotts.api.Account;
import org.gestern.gringotts.api.Eco;

public class PreventUsage implements Listener {
    @EventHandler
    public void craftItem(PrepareItemCraftEvent e) {
        if (e.getRecipe() != null) {
            Material itemType = e.getRecipe().getResult().getType();
            if(itemType == Material.EMERALD || itemType == Material.EMERALD_BLOCK) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
                for (HumanEntity he:e.getViewers()) {
                    if (he instanceof Player) {
                        ((Player)he).sendMessage(ChatColor.RED + "Geldfälschung ist nicht erlaubt...:)");
                    }
                }
            }
        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        Material itemData = e.getItemInHand().getType();
        ItemMeta itemMeta = e.getItemInHand().getItemMeta();
        if (itemData == Material.EMERALD_BLOCK) {
            e.getPlayer().sendMessage("Du solltest dein Geld nicht platzieren...");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.VILLAGER)
            return;
        Villager villager = (Villager) e.getRightClicked();
        if (villager.getProfession() == Villager.Profession.NONE)
            return;
        if (villager.hasAI() && villager.hasGravity()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Villager Trades sind aktuell zum Schutz des Economysystems" +
                    "deaktiviert und werden in Zukunft wieder hinzugefügt.");
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        if (!action.equals(Action.RIGHT_CLICK_AIR) && !action.equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (!e.hasItem()) {
            return;
        }
        ItemStack item = e.getItem();
        if (item.getType().equals(Material.EXPERIENCE_BOTTLE) && item.getItemMeta().getDisplayName().contains("Emerald")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Du solltest nicht deine wertvollen Emeralds wegwerfen...");
        }
    }
}
