package com.jonmagon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Looper extends org.bukkit.scheduler.BukkitRunnable {

    public void run() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            synchronized (p) {
                if (Util.config.getStringList("ignored-worlds").contains(p.getWorld().getName().toLowerCase()))
                    continue;

                if (p.getLocation().getBlock().getType() == Material.NETHER_PORTAL) {
                    if (!Util.playersInPortal.containsKey(p)) {
                        Util.playersInPortal.put(p, Util.config.getInt("stay-counter"));
                        continue;
                    }

                    int playerCounter = Util.playersInPortal.get(p);

                    if (playerCounter < 1) {
                        Location l = p.getLocation();
                        if (l.getBlock().getType() == Material.NETHER_PORTAL) {
                            l.getBlock().setType(Material.AIR);

                            p.sendMessage(Util.getMessage(Util.config.getString("portal-disabled"),
                                    Util.config.getInt("auto-enable")));

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), () -> {

                                if (l.getBlock().getType() == Material.AIR) {
                                    l.getBlock().setType(Material.FIRE);
                                    p.sendMessage(Util.getMessage(Util.config.getString("portal-enabled")));
                                }

                                }, (Util.config.getInt("auto-enable") * 20));
                        }
                    } else {
                        Util.playersInPortal.put(p, playerCounter - 1);

                        if (playerCounter == Util.config.getInt("stay-warn")) {

                            p.sendMessage(Util.getMessage(Util.config.getString("will-disabled-first"),
                                    Util.config.getInt("stay-warn")));
                        }
                        else if (playerCounter < Util.config.getInt("stay-warn")) {
                            p.sendMessage(Util.getMessage(Util.config.getString("will-disabled-warn")
                                    .replaceAll("<f>",
                                            (playerCounter == 1 ?
                                                    Util.config.getString("singular-word") :
                                                    Util.config.getString("plural-word"))),
                                    playerCounter));
                        }

                    }
                } else Util.playersInPortal.remove(p);
            }
        }
    }

}