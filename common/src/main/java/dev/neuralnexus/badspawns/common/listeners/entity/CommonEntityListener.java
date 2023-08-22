package dev.neuralnexus.badspawns.common.listeners.entity;

import dev.neuralnexus.badspawns.common.BadSpawns;
import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;

/**
 * Entity event listeners.
 */
public interface CommonEntityListener {
    /**
     * Called when an entity spawns.
     * @param args The entity.
     */
    static void onEntitySpawn(Object[] args) {
        AbstractEntity entity = (AbstractEntity) args[0];

        // Check banned mobs
        if (BadSpawns.isMobBanned(entity)) {
            entity.remove();
        }
        // Check regions
        else if (BadSpawns.checkRegions(entity)) {
            entity.remove();
        }
    }
}
