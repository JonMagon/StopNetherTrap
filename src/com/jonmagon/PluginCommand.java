package com.jonmagon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

            if (sender.isOp()) {

                MainClass.getInstance().reloadConfig();
                Util.config = MainClass.getInstance().getConfig();
                Util.playersInPortal.clear();

                sender.sendMessage(Util.getMessage("&b[StopNetherTrap] &aReloaded"));
            } else
                sender.sendMessage(Util.getMessage("&b[StopNetherTrap] &cYou don't have permission to reload."));
        }
        else
            sender.sendMessage(Util.getMessage("&bStopNetherTrap v1.0 &e[Dmitry Sidorov (JonMagon)]"));

        return true;
    }
}
