package dev.neuralnexus.badspawns.common;

import dev.neuralnexus.taterlib.common.TemplatePlugin;

/**
 * The BadSpawns plugin interface.
 */
public interface BadSpawnsPlugin extends TemplatePlugin {
    /**
     * Starts the plugin.
     */
    default void pluginStart() {
        try {
            useLogger("BadSpawns is running on " + getServerType() + " " + getServerVersion() + "!");

            // Start
            BadSpawns.start(pluginConfigPath(), pluginLogger());

            // Register hooks
            registerHooks();

            // Register event listeners
            registerEventListeners();

            // Register commands
            registerCommands();

            useLogger("BadSpawns has been enabled!");

        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Stops the plugin.
     */
    default void pluginStop() {
        try {
            BadSpawns.stop();
            useLogger("BadSpawns has been disabled!");
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
