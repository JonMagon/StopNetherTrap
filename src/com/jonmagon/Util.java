package com.jonmagon;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Util {

    public static HashMap<Player, Integer> playersInPortal = new HashMap();

    public static String getMessage(String msg) {

        return getMessage(msg, 0);
    }

    public static String getMessage(String msg, Integer time) {

        return msg.replaceAll("%s", time.toString())
                .replaceAll("&", "§");
    }
}
