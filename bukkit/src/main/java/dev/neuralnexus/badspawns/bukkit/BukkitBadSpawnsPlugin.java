package dev.neuralnexus.badspawns.bukkit;

import dev.neuralnexus.badspawns.common.BadSpawnsPlugin;
import dev.neuralnexus.taterlib.bukkit.TemplateBukkitPlugin;

/**
 * The BadSpawns Bukkit plugin.
 */
public class BukkitBadSpawnsPlugin extends TemplateBukkitPlugin implements BadSpawnsPlugin {
    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {}

    /**
     * @inheritDoc
     */
    @Override
    public void onEnable() {
        pluginStart();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onDisable() {
        pluginStop();
    }
}
