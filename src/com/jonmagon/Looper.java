package com.jonmagon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Looper extends org.bukkit.scheduler.BukkitRunnable {

    FileConfiguration config;

    public Looper(FileConfiguration config) {

        this.config = config;
    }

    public void run() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            synchronized (p) {
                if (p.getLocation().getBlock().getType() == Material.NETHER_PORTAL) {
                    if (!Util.playersInPortal.containsKey(p)) {
                        Util.playersInPortal.put(p, config.getInt("stay-counter"));
                        continue;
                    }

                    int playerCounter = Util.playersInPortal.get(p);

                    if (playerCounter < 1) {
                        Location l = p.getLocation();
                        if (l.getBlock().getType() == Material.NETHER_PORTAL) {
                            l.getBlock().setType(Material.AIR);

                            p.sendMessage(Util.getMessage(config.getString("portal-disabled"),
                                    config.getInt("auto-enable")));

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), () -> {

                                if (l.getBlock().getType() == Material.AIR) {
                                    l.getBlock().setType(Material.FIRE);
                                    p.sendMessage(Util.getMessage(config.getString("portal-enabled")));
                                }

                                }, (config.getInt("auto-enable") * 20));
                        }
                    } else {
                        Util.playersInPortal.put(p, playerCounter - 1);

                        if (playerCounter == config.getInt("stay-warn")) {

                            p.sendMessage(Util.getMessage(config.getString("will-disabled-first"),
                                    config.getInt("stay-warn")));
                        }
                        else if (playerCounter < config.getInt("stay-warn")) {
                            p.sendMessage(Util.getMessage(config.getString("will-disabled-warn")
                                    .replaceAll("<f>",
                                            (playerCounter == 1 ?
                                                    config.getString("singular-word") :
                                                    config.getString("plural-word"))),
                                    playerCounter));
                        }

                    }
                } else Util.playersInPortal.remove(p);
            }
        }
    }

}