package org.gestern.gringotts.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.gestern.gringotts.api.Account;
import org.gestern.gringotts.api.TransactionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class getRankExecutor extends GringottsAbstractExecutor {
    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd,
                             String commandLabel,
                             String[] args) {
        String name = sender.getName();

        Account target = eco.getAccount(name);

        if(!target.exists()) {
            sendInvalidAccountMessage(sender, name);
            return false;
        }

        if (testPermission(sender, "canrealeconomy.isvip")) {
            sender.sendMessage("Du bist bereits VIP oder höher. Du würdest hierdurch ein downgrade deines Rangs vornehmen!");
            return true;
        }

        if (testPermission(sender, "canrealeconomy.isunternehmer")) {
            sender.sendMessage("Du bist bereits Unternehmer. Du musst warten, bis dein Rang ausläuft um ihn dir neu zu holen.");
            return true;
        }

        double amount = 10000.0;
        double balance = target.balance();

        if (balance >= amount) {
            String formattedAmount = eco.currency().format(amount);
            TransactionResult removed = target.remove(amount);

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "lp user " + sender.getName() + " parent addtemp unternehmer 1mo";
            Bukkit.dispatchCommand(console, command);

            sender.sendMessage("Du hast dir erfolgreich den Unternehmer-Rang geholt.");
            return true;
        } else {
            sender.sendMessage("Du hast dazu nicht genug Emeralds.");
            return true;
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
