package com.jonmagon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(Util.getMessage("&aStopNetherTrap v0.1 [Author: &Dmitry Sidorov (JonMagon)&a]"));

        return true;
    }
}