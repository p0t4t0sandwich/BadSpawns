package dev.neuralnexus.badspawns.forge;

import com.mojang.logging.LogUtils;
import dev.neuralnexus.badspawns.common.BadSpawnsPlugin;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.forge.TemplateForgePlugin;
import dev.neuralnexus.taterlib.forge.abstrations.logger.ForgeLogger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * The BadSpawns Forge plugin.
 */
@Mod(ForgeBadSpawnsPlugin.MOD_ID)
public class ForgeBadSpawnsPlugin extends TemplateForgePlugin implements BadSpawnsPlugin {
    public static final String MOD_ID = "badspawns";

    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new ForgeLogger(LogUtils.getLogger());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {}

    /**
     * Called when the Forge mod is initializing.
     */
    public ForgeBadSpawnsPlugin() {
        // Register server starting/stopping events
        MinecraftForge.EVENT_BUS.register(this);
        pluginStart();
    }

    /**
     * Called when the server is stopping.
     * @param event The event.
     */
    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        pluginStop();
    }
}
