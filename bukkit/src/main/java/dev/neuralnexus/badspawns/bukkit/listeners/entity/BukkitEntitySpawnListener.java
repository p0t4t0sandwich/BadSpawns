package dev.neuralnexus.badspawns.bukkit.listeners.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * Listens for entity spawn events.
 */
public class BukkitEntitySpawnListener implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        // Do stuff
    }
}
