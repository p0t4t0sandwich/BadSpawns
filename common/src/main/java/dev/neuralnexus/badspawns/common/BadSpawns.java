package dev.neuralnexus.badspawns.common;

import dev.neuralnexus.badspawns.common.api.BadSpawnsAPIProvider;
import dev.neuralnexus.badspawns.common.listeners.entity.CommonEntityListener;
import dev.neuralnexus.badspawns.common.region.Region;
import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.event.entity.EntityEvents;
import dev.neuralnexus.taterlib.lib.dejvokep.boostedyaml.YamlDocument;
import dev.neuralnexus.taterlib.lib.dejvokep.boostedyaml.block.implementation.Section;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * The BadSpawns class.
 */
public class BadSpawns {
    private static final BadSpawns instance = new BadSpawns();
    private static YamlDocument config;
    private static String configPath;
    public static AbstractLogger logger;
    private static boolean STARTED = false;
    private static final ArrayList<Object> hooks = new ArrayList<>();
    public static boolean isEnabled = false;
    public static ArrayList<String> bannedMobs = new ArrayList<>();
    public static HashMap<String, Region> regions = new HashMap<>();

    /**
     * Constructor for the BadSpawns class.
     */
    public BadSpawns() {}

    /**
     * Getter for the singleton instance of the BadSpawns class.
     * @return The singleton instance
     */
    public static BadSpawns getInstance() {
        return instance;
    }

    /**
     * Add a hook to the hooks list
     * @param hook The hook to add
     */
    public static void addHook(Object hook) {
        hooks.add(hook);
    }

    /**
     * Use the Logger
     * @param message The message to log
     */
    public static void useLogger(String message) {
        logger.info(message);
    }

    /**
     * Start
     * @param configPath The path to the config file
     * @param logger The logger
     */
    public static void start(String configPath, AbstractLogger logger) {
        BadSpawns.configPath = configPath;
        BadSpawns.logger = logger;

        // Config
        try {
            config = YamlDocument.create(new File("." + File.separator + configPath + File.separator + "BadSpawns", "badspawns.config.yml"),
                    Objects.requireNonNull(BadSpawns.class.getClassLoader().getResourceAsStream("badspawns.config.yml"))
            );
            config.reload();
        } catch (IOException | NullPointerException e) {
            useLogger("Failed to load badspawns.config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }

        if (STARTED) {
            useLogger("BadSpawns has already started!");
            return;
        }
        STARTED = true;

        isEnabled = config.getBoolean("enabled");

        // Build banned mobs list
        useLogger("Building banned mobs list...");

        for (String mob : config.getStringList("bannedMobs")) {
            bannedMobs.add("entity." + mob.replace(":", "."));
        }

        // Build regions list
        useLogger("Building regions list...");
        for (Object region : config.getList("regions")) {
            Section section = (Section) region;
            String name = section.getString("name");
            try {
                // Build region
                regions.put(name, Region.build(
                        name,
                        section.getString("type"),
                        section.getString("minX"),
                        section.getString("maxX"),
                        section.getString("minY"),
                        section.getString("maxY"),
                        section.getString("minZ"),
                        section.getString("maxZ"),
                        section.getStringList("worlds"),
                        section.getStringList("biomes"),
                        section.getStringList("mobs")
                ));
            } catch (Exception e) {
                useLogger("Failed to build region " + name + "!\n" + e.getMessage());
                e.printStackTrace();
            }
        }

        // Register Entity Listeners
        EntityEvents.SPAWN.register(CommonEntityListener::onEntitySpawn);

        useLogger("BadSpawns has been started!");
        BadSpawnsAPIProvider.register(instance);
    }

    /**
     * Start
     */
    public static void start() {
        start(configPath, logger);
    }

    /**
     * Stop
     */
    public static void stop() {
        if (!STARTED) {
            useLogger("BadSpawns has already stopped!");
            return;
        }
        STARTED = false;

        // Remove references to objects
        bannedMobs.clear();
        regions.clear();

        useLogger("BadSpawns has been stopped!");
        BadSpawnsAPIProvider.unregister();
    }

    /**
     * Reload
     */
    public static void reload() {
        if (!STARTED) {
            useLogger("BadSpawns has not been started!");
            return;
        }

        // Stop
        stop();

        // Start
        start(configPath, logger);

        useLogger("BadSpawns has been reloaded!");
    }

    /**
     * Get whether an entity is banned
     * @param entity The entity to check
     * @return Whether the entity is banned
     */
    public static boolean isMobBanned(AbstractEntity entity) {
        return bannedMobs.contains(entity.getType());
    }

    /**
     * Get whether an entity is banned within a region
     * @param entity The entity to check
     * @return Whether the entity is banned
     */
    public static boolean checkRegions(AbstractEntity entity) {
        for (Region region : regions.values()) {
            if (region.calculate(entity)) {
                return true;
            }
        }
        return false;
    }
}
