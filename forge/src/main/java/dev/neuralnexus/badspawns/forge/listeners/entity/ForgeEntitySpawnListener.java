package dev.neuralnexus.badspawns.forge.listeners.entity;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeEntitySpawnListener {
    @SubscribeEvent
    public void onEntitySpawn(PlayerEvent.PlayerLoggedInEvent event) {
        // Do stuff
    }
}
