# BadSpawns

A cross-API server plugin to cancel the spawns of mobs using defined regions and conditions.

Link to our support: [Discord](https://discord.neuralnexus.dev)

## Download

- [GitHub](https://github.com/p0t4t0sandwich/BadSpawns/releases)
- [Maven Repo](https://maven.neuralnexus.dev/#/releases/dev/neuralnexus/BadSpawns)

[//]: # (- [Spigot]&#40;https://www.spigotmc.org/resources/badspawns.xxxxxx/&#41;)

[//]: # (- [Hangar]&#40;https://hangar.papermc.io/p0t4t0sandwich/BadSpawns&#41;)

[//]: # (- [Modrinth]&#40;https://modrinth.com/plugin/badspawns&#41;)

[//]: # (- [CurseForge]&#40;https://www.curseforge.com/minecraft/mc-mods/badspawns&#41;)

[//]: # (- [Sponge]&#40;https://ore.spongepowered.org/p0t4t0sandwich/BadSpawns&#41;)

### Compatibility Cheatsheet

[//]: # (BadSpawns supports: Bukkit, Fabric, Forge, and Sponge &#40;some versions&#41;)
BadSpawns supports: Bukkit, Fabric, and Forge

| Server type        | Versions    | Jar Name                       |
|--------------------|-------------|--------------------------------|
| All 1.20           | 1.20-1.20.1 | `BadSpawns-<version>-1.20.jar` |
| All 1.19           | 1.19-1.19.4 | `BadSpawns-<version>-1.19.jar` |

[//]: # (| All 1.18           | 1.18-1.18.2 | `BadSpawns-<version>-1.18.jar` |)

[//]: # (| All 1.17           | 1.17-1.17.1 | `BadSpawns-<version>-1.17.jar` |)

[//]: # (| All 1.16 &#40;Sponge8&#41; | 1.16-1.16.5 | `BadSpawns-<version>-1.16.jar` |)

[//]: # (| All 1.15           | 1.15-1.15.2 | `BadSpawns-<version>-1.15.jar` |)

## Dependencies

- [TaterLib](https://github.com/p0t4t0sandwich/TaterLib) - Required on all platforms
- [FabricAPI](https://modrinth.com/mod/fabric-api) - Required on Fabric

### Optional Dependencies

- [LuckPerms](https://luckperms.net/) - For permissions/prefix/suffix support

## Usage

- Commands either require a permissions manager or OP level 4 in order to use them.

## Commands and Permissions

| Command                                  | Permission               | Description                                               |
|------------------------------------------|--------------------------|-----------------------------------------------------------|
| `/badspawns help`                        | `badspawns.command.help` | Show help for commands                                    |

## Configuration

`<plugins/config>/BadSpawns/badspawns.config.yml`

```yaml
---
version: 1
enabled: true
bannedMobs:
  - "modid:entityid"

regions:
  - name: ExampleRegion
    type: blacklist
    minX: -100
    maxX: *
    minY: 0
    maxY: *
    minZ: -100
    maxZ: *
    worlds:
      - world
    biomes:
      - "minecraft:plains"
    mobs:
      - "modid:entityid"

  - name: ExampleRegion2
    type: whitelist
    minX: -100
    maxX: 100
    minY: 0
    maxY: 256
    minZ: -100
    maxZ: 100
    worlds:
      - world
    biomes:
      - "minecraft:plains"
    mobs:
      - "modid:entityid"
```

## TODO

- Command to manage blacklist
  - Add/remove mobs from blacklist
  - Change location parameters via command
- Command to set up regions
  - Add/remove regions
  - Change location parameters via command
- include a `*` to blacklist all mobs from a given mod
- Ability to enable/disable regions

## Release Notes
