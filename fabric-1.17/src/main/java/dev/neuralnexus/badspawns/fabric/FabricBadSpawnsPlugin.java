package dev.neuralnexus.badspawns.fabric;

import dev.neuralnexus.badspawns.common.BadSpawnsPlugin;
import dev.neuralnexus.badspawns.fabric.events.entity.FabricEntityEvents;
import dev.neuralnexus.badspawns.fabric.listeners.entity.FabricEntityListener;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.LoggerFactory;

/**
 * The LeafDecayNt Fabric plugin.
 */
public class FabricBadSpawnsPlugin implements DedicatedServerModInitializer, BadSpawnsPlugin {
    /**
     * @inheritDoc
     */
    @Override
    public Object pluginLogger() {
        return LoggerFactory.getLogger("badspawns");
    }

    /**
     * @inheritDoc
     */
    @Override
    public String pluginConfigPath() {
        return "config";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerType() {
        return "Fabric";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {});
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
        FabricEntityEvents.SPAWN.register(FabricEntityListener::onEntitySpawn);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {}

    /**
     * @inheritDoc
     */
    @Override
    public void onInitializeServer() {
        pluginStart();
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> pluginStop());
    }
}
