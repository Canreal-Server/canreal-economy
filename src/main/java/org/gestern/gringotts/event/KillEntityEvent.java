package org.gestern.gringotts.event;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class KillEntityEvent implements Listener {
    // Give player money if they kill a hostile mob, boss or player (randomly)
    @EventHandler
    public void onEntityKill(EntityDeathEvent e) {
        LivingEntity dead = e.getEntity();

        if (dead.getKiller() == null)
            return;

        Player player = dead.getKiller();
        if (dead.getType() == EntityType.ZOMBIE ||
            dead.getType() == EntityType.BLAZE ||
            dead.getType() == EntityType.ZOMBIE_VILLAGER ||
            dead.getType() == EntityType.ZOMBIFIED_PIGLIN ||
            dead.getType() == EntityType.SKELETON ||
            dead.getType() == EntityType.CAVE_SPIDER ||
            dead.getType() == EntityType.SPIDER ||
            dead.getType() == EntityType.CREEPER ||
            dead.getType() == EntityType.DROWNED ||
            dead.getType() == EntityType.ELDER_GUARDIAN ||
            dead.getType() == EntityType.ENDERMITE ||
            dead.getType() == EntityType.EVOKER ||
            dead.getType() == EntityType.GHAST ||
            dead.getType() == EntityType.GIANT ||
            dead.getType() == EntityType.GUARDIAN ||
            dead.getType() == EntityType.HOGLIN ||
            dead.getType() == EntityType.HUSK ||
            dead.getType() == EntityType.ILLUSIONER ||
            dead.getType() == EntityType.MAGMA_CUBE ||
            dead.getType() == EntityType.PHANTOM ||
            dead.getType() == EntityType.PIGLIN_BRUTE ||
            dead.getType() == EntityType.PILLAGER ||
            dead.getType() == EntityType.RAVAGER ||
            dead.getType() == EntityType.SHULKER ||
            dead.getType() == EntityType.SILVERFISH ||
            dead.getType() == EntityType.SLIME ||
            dead.getType() == EntityType.STRAY ||
            dead.getType() == EntityType.VEX ||
            dead.getType() == EntityType.VINDICATOR ||
            dead.getType() == EntityType.WITCH ||
            dead.getType() == EntityType.WITHER_SKELETON ||
            dead.getType() == EntityType.ZOGLIN) {
            int min = 1;
            int max = 10;
            int rand = new Random().nextInt(max - min) + min;
            if (rand == 5) {
                int minEmeralds = 1;
                int maxEmeralds = 3;
                int randEmeralds = new Random().nextInt(maxEmeralds - minEmeralds) + minEmeralds;

                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                String command = "moneyadmin add " + player.getName() + " " + randEmeralds;
                Bukkit.dispatchCommand(console, command);
            }
        }
        if (dead.getType() == EntityType.ENDER_DRAGON ||
            dead.getType() == EntityType.WITHER ||
            dead.getType() == EntityType.WARDEN) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "moneyadmin add " + player.getName() + " 50";
            Bukkit.dispatchCommand(console, command);
        }
        if (dead.getType() == EntityType.PLAYER) {
            int min = 1;
            int max = 3;
            int rand = new Random().nextInt(max - min) + min;
            if (rand == 2) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                String command = "moneyadmin add " + player.getName() + " 20";
                Bukkit.dispatchCommand(console, command);
            }
        }
    }
}
