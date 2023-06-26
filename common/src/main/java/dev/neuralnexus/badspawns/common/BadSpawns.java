package dev.neuralnexus.badspawns.common;

import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BadSpawns {
    /**
     * Properties of the BadSpawns class.
     * instance: The singleton instance of the BadSpawns class
     * config: The config file
     * logger: The logger
     * STARTED: Whether the BadSpawns has been started
     */
    private static final BadSpawns instance = new BadSpawns();
    private static YamlDocument config;
    private static Object logger;
    private static String configPath;
    private static boolean STARTED = false;
    public static boolean isEnabled = false;
    public static ArrayList<String> bannedMobs = new ArrayList<>();

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
     * Use whatever logger is being used.
     * @param message The message to log
     */
    public static void useLogger(String message) {
        if (logger instanceof java.util.logging.Logger) {
            ((java.util.logging.Logger) logger).info(message);
        } else if (logger instanceof org.slf4j.Logger) {
            ((org.slf4j.Logger) logger).info(message);
        } else {
            System.out.println(message);
        }
    }

    /**
     * Start BadSpawns
     * @param configPath The path to the config file
     * @param logger The logger
     */
    public static void start(String configPath, Object logger) {
        BadSpawns.configPath = configPath;
        BadSpawns.logger = logger;

        // Config
        try {
            config = YamlDocument.create(new File("." + File.separator + configPath + File.separator + "BadSpawns", "config.yml"),
                    Objects.requireNonNull(BadSpawns.class.getClassLoader().getResourceAsStream("config.yml"))
            );
            config.reload();
        } catch (IOException | NullPointerException e) {
            useLogger("Failed to load config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }

        if (STARTED) {
            useLogger("[BadSpawns] BadSpawns has already started!");
            return;
        }
        STARTED = true;

        isEnabled = config.getBoolean("enabled");

        for (String mob : config.getStringList("bannedMobs")) {
            bannedMobs.add("entity." + mob.replace(":", "."));
        }

        useLogger("[BadSpawns] BadSpawns has been started!");
    }

    /**
     * Start BadSpawns
     */
    public static void start() {
        start(configPath, logger);
    }

    /**
     * Stop BadSpawns
     */
    public static void stop() {
        if (!STARTED) {
            useLogger("[BadSpawns] BadSpawns has already stopped!");
            return;
        }
        STARTED = false;

        useLogger("[BadSpawns] BadSpawns has been stopped!");
    }
}
