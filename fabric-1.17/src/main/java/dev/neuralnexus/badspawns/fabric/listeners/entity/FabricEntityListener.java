package dev.neuralnexus.badspawns.fabric.listeners.entity;

import dev.neuralnexus.badspawns.common.BadSpawns;
import net.minecraft.entity.Entity;

public class FabricEntityListener {
    /**
     * Called when an entity is spawned.
     * @param entity The entity that was spawned.
     */
    public static void onEntitySpawn(Entity entity) {
        if (!BadSpawns.isEnabled || entity == null) return;

        // Get the entity name in the form of "entity.modid.entityname"
        String entityName = entity.getType().toString();

        // Check if the entity should be removed
        if (BadSpawns.bannedMobs.contains(entityName)) {
            entity.remove(Entity.RemovalReason.KILLED);
        }

        // Height dependent removal
        int maxHeight = 63; // BadSpawns.config.getInt("maxHeight");
        String[] heightDependentMobs = {"entity.varietyaquatic.lionfish", "entity.varietyaquatic.opah", "entity.varietyaquatic.yellowfin_tuna"};

        if (entity.getY() > maxHeight) {
            for (String mob : heightDependentMobs) {
                if (entityName.equals(mob)) {
                    entity.remove(Entity.RemovalReason.KILLED);
                }
            }
        }
    }
}
