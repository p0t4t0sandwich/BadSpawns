package dev.neuralnexus.badspawns.sponge;

import dev.neuralnexus.badspawns.common.BadSpawnsPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.sponge.TemplateSpongePlugin;
import dev.neuralnexus.taterlib.sponge.abstractions.logger.SpongeLogger;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;

@Plugin("badspawns")
public class SpongeBadSpawnsPlugin extends TemplateSpongePlugin implements BadSpawnsPlugin {
    @Inject
    private Logger logger;

    @Inject
    private PluginContainer container;

    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new SpongeLogger(logger);
    }

    /**
     * Register commands.
     * @param event The event
     */
    @Listener
    public void onRegisterCommands(final RegisterCommandEvent<Command.Parameterized> event) {}

    /**
     * Fired when the server starts.
     * @param event The event
     */
    @Listener
    public void onServerStarting(StartingEngineEvent<Server> event) {
        pluginStart();
    }

    /**
     * Fired when the server stops.
     * @param event The event
     */
    @Listener
    public void onServerStop(StoppingEngineEvent<Server> event) {
        pluginStop();
    }
}
