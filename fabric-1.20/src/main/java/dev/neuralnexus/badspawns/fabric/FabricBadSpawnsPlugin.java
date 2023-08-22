package dev.neuralnexus.badspawns.fabric;

import dev.neuralnexus.badspawns.common.BadSpawnsPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.fabric.TemplateFabricPlugin;
import dev.neuralnexus.taterlib.fabric.abstractions.logger.FabricLogger;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.LoggerFactory;

/**
 * The BadSpawns Fabric plugin.
 */
public class FabricBadSpawnsPlugin extends TemplateFabricPlugin implements BadSpawnsPlugin {
    public static final String MOD_ID = "badspawns";

    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new FabricLogger( "[BadSpawns] ", LoggerFactory.getLogger(MOD_ID));
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
