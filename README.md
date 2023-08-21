# BadSpawns

A cross-API server plugin to cancel the spawns of mobs using defined regions and conditions.

## TODO

- [ ] Bukkit implementation
- [ ] Forge implementation
- [ ] Command to set up blacklists
  - [ ] Add/remove mobs from blacklist
  - [ ] Change location parameters via command
- [ ] New config specification
  - [ ] Two sets of xyz coords to draw a box, with world name
    - [ ] add a `*` to the world name to blacklist all worlds
    - [ ] add a `*` to a coord to blacklist to infinity in that direction
  - [ ] List of mobs to blacklist
    - [ ] include a `*` to blacklist all mobs from a given mod
