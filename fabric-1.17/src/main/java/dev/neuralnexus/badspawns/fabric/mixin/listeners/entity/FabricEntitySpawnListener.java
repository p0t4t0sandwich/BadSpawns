package dev.neuralnexus.badspawns.fabric.mixin.listeners.entity;

import dev.neuralnexus.badspawns.common.BadSpawns;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ServerWorld.class)
class FabricEntitySpawnListener {
    @Inject(method = "spawnEntity", at = @At("HEAD"))
    private void onEntitySpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!BadSpawns.isEnabled || entity == null) return;

        // Get the entity name in the form of "entity.modid.entityname"
        String entityName = entity.getType().toString();

        // Check if the entity should be removed
        if (BadSpawns.bannedMobs.contains(entityName)) {
            entity.remove(Entity.RemovalReason.KILLED);
        }
    }
}
