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

        String entityName = entity.getType();

        // Check if the entity should be removed
        if (BadSpawns.bannedMobs.contains(entityName)) {
            entity.remove();
        }

        // Height dependent removal
        int maxHeight = 63; // BadSpawns.config.getInt("maxHeight");
        String[] heightDependentMobs = {"entity.varietyaquatic.lionfish", "entity.varietyaquatic.opah", "entity.varietyaquatic.yellowfin_tuna"};

//        if (entity.getY() > maxHeight) {
//            for (String mob : heightDependentMobs) {
//                if (entityName.equals(mob)) {
//                    entity.remove();
//                }
//            }
//        }
    }
}
