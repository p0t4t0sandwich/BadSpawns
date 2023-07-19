package dev.neuralnexus.badspawns.fabric.events.entity;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;

/**
 * Contains additional entity events.
 */
public final class FabricEntityEvents {
    /**
     * Called when an entity spawns.
     */
    public static final Event<EntitySpawn> SPAWN = EventFactory.createArrayBacked(EntitySpawn.class, (listeners) -> (entity) -> {
        for (EntitySpawn listener : listeners) {
            listener.onEntitySpawn(entity);
        }
    });

    @FunctionalInterface
    public interface EntitySpawn {
        void onEntitySpawn(Entity entity);
    }
}
