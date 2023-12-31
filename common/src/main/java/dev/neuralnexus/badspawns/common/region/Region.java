package dev.neuralnexus.badspawns.common.region;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;

import java.util.List;

/**
 * Defines a region that will check dimensions, coordinates, and biomes to determine if a mob should be banned.
 */
public class Region {
    private final String name;
    private final boolean isWhitelist;
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final int minZ;
    private final int maxZ;
    private final List<String> worlds;
    private final List<String> biomes;
    private final List<String> mobs;

    /**
     * Constructor for the Region class.
     * @param name The name of the region
     * @param isWhitelist Whether the region is a whitelist
     * @param minX The minimum X coordinate
     * @param maxX The maximum X coordinate
     * @param minY The minimum Y coordinate
     * @param maxY The maximum Y coordinate
     * @param minZ The minimum Z coordinate
     * @param maxZ The maximum Z coordinate
     * @param worlds The dimension
     * @param biomes The biomes
     * @param mobs The mobs
     */
    public Region(final String name, final boolean isWhitelist, final int minX, final int maxX, final int minY, final int maxY, final int minZ, final int maxZ, final List<String> worlds, final List<String> biomes, final List<String> mobs) {
        this.name = name;
        this.isWhitelist = isWhitelist;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.worlds = worlds;
        this.biomes = biomes;
        this.mobs = mobs;
    }

    /**
     * Build a region from the given parameters.
     * @param name The name of the region
     * @param type The type of region
     * @param minXs The minimum X coordinate
     * @param maxXs The maximum X coordinate
     * @param minYs The minimum Y coordinate
     * @param maxYs The maximum Y coordinate
     * @param minZs The minimum Z coordinate
     * @param maxZs The maximum Z coordinate
     * @param worlds The dimension
     * @param biomes The biomes
     * @param mobs The mobs
     * @return The built region
     */
    public static Region build(final String name, final String type, final String minXs, final String maxXs, final String minYs, final String maxYs, final String minZs, final String maxZs, final List<String> worlds, final List<String> biomes, final List<String> mobs) {
        boolean isWhitelist = type.equals("whitelist");
        int minX;
        int maxX;
        int minY;
        int maxY;
        int minZ;
        int maxZ;

        // Get the minimum X coordinate
        if (minXs.equals("*")) {
            minX = -30000000;
        } else {
            minX = Integer.parseInt(minXs);
        }

        // Get the maximum X coordinate
        if (maxXs.equals("*")) {
            maxX = 30000000;
        } else {
            maxX = Integer.parseInt(maxXs);
        }

        // Get the minimum Y coordinate
        if (minYs.equals("*")) {
            minY = -1000;
        } else {
            minY = Integer.parseInt(minYs);
        }

        // Get the maximum Y coordinate
        if (maxYs.equals("*")) {
            maxY = 1000;
        } else {
            maxY = Integer.parseInt(maxYs);
        }

        // Get the minimum Z coordinate
        if (minZs.equals("*")) {
            minZ = -30000000;
        } else {
            minZ = Integer.parseInt(minZs);
        }

        // Get the maximum Z coordinate
        if (maxZs.equals("*")) {
            maxZ = 30000000;
        } else {
            maxZ = Integer.parseInt(maxZs);
        }

        return new Region(name, isWhitelist, minX, maxX, minY, maxY, minZ, maxZ, worlds, biomes, mobs);
    }

    /**
     * Check if an entity is within the region.
     * @return Whether the entity is within the region
     */
    public boolean isWithinRegion(AbstractEntity entity) {
        //
//        System.out.println("---------------------------------------------");
//        System.out.println("Region: " + name);
//        System.out.println("Entity: " + entity.getType());
//        System.out.println("Mobs: " + mobs);
//        System.out.println("Contains: " + mobs.contains(entity.getType()));
//        System.out.println("All: " + mobs.contains("all"));
        //

        // Check mobs
        if (!mobs.contains(entity.getType()) && !mobs.contains("all")) {
            //
//            System.out.println("Not in mob list: " + entity.getType());
            //
            return false;
        }

        // Check dimensions
        if (!worlds.contains(entity.getDimension()) && !worlds.contains("all")) {
            //
//            System.out.println("Not in dimension list: " + entity.getDimension());
            //
            return false;
        }

        // Check biomes
        if (!biomes.contains(entity.getBiome()) && !biomes.contains("all")) {
            //
//            System.out.println("Not in biome list: " + entity.getBiome());
            //
            return false;
        }

        // Check coordinates
        if (entity.getX() < minX || entity.getX() > maxX) {
            return false;
        }
        if (entity.getY() < minY || entity.getY() > maxY) {
            return false;
        }
        if (entity.getZ() < minZ || entity.getZ() > maxZ) {
            return false;
        }

        //
//        System.out.println("In region, all parameters true");
        //

        return true;
    }

    /**
     * Calculate the final result of the region.
     * @param entity The entity
     * @return Whether the entity should be banned
     */
    public boolean calculate(AbstractEntity entity) {
        // Nifty simplification to invert the result if the region is a whitelist
//        return isWithinRegion(entity) != isWhitelist;

        boolean result = isWithinRegion(entity);

        //
//        System.out.println("Result: " + result);
//        System.out.println("Is Whitelist: " + isWhitelist);
//        System.out.println("Final: " + (result != isWhitelist));
//        System.out.println("---------------------------------------------");
        //

        return result != isWhitelist;
    }
}
