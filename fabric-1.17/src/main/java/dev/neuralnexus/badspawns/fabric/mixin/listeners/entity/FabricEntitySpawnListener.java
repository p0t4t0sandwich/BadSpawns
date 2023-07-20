package dev.neuralnexus.badspawns.fabric.mixin.listeners.entity;

import dev.neuralnexus.badspawns.fabric.events.entity.FabricEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for the entity spawn listener
 */
@Mixin(ServerWorld.class)
class FabricEntitySpawnListener {
    /**
     * Called when an entity is spawned.
     * @param entity The entity that was spawned.
     * @param cir The callback info returnable.
     */
    @Inject(method = "spawnEntity", at = @At("HEAD"))
    private void onEntitySpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        // Fire the entity spawn event
        FabricEntityEvents.SPAWN.invoker().onEntitySpawn(entity);
    }
}
