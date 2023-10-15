# Luma Mode

Changes to survival Minecraft to turn it into a more authentic and fun experience.  
This is achieved through a combination of feature forward-ports and small server-side changes.

## Changes

For Minecraft 1.3:

- Enderman griefing is disabled
- Fire from lightning no longer destroys blocks
- Set difficulty to hard by default
- Disable spawn protection
  - There's no vanilla option for this yet
- Beds have functionality removed, are decoration instead:
    - No longer allow skipping the night
    - No longer change the player's spawn point
- Players can not accidentally destroy blocks with weapons during combat

## Future Ideas

For Minecraft 1.4:

- Disable natural regeneration (Enable UHC mode from 1.6)
    - Players will have to set up beacons for permanent health regeneration
- Maybe make beacons use up potions (at a slower rate than usual)
    - Length scales based on pyramid size and material
    - Requires making Haste and Resistance potions craftable

For Minecraft 1.5:

- Prevent sprinting up stairs

For Minecraft 1.6:

- Set `naturalRegeneration` rule to `false` by default
    - Supersedes manual implementation of UHC mode

For Minecraft 1.8:

- Re-add void fog for nicer cave atmosphere
    - Maybe make it less potent if the area is lit up
- Endermite stop attracting Endermen after some time
    - Endermen farms being no effort is pretty lame ...
- Set `reducedDebugInfo` rule to `true` by default
- Maybe implement minecart changes from snapshot 14w11a

For Minecraft 1.9:

- Disallow shield usage in offhand
    - Maybe make shield usage percentage based (?)
- Mending allows no repair cost increasing anvil repairs
    - Replacing mining entirely with an enchant is overpowered
- Set `disableElytraMovementCheck` rule to `true` by default
    - This has a pretty big false positive rate when gliding

For Minecraft 1.11:

- Remove firework rocket boosting for elytra

For Minecraft 1.12:

- Set `doLimitedCrafting` rule to `true` by default
- Re-add achievements that were not added as advancements

For Minecraft 1.13:

- Add built-in anti ender grief datapack
    - Supersedes manual implementation of anti ender grief
- Remove fast boat sliding on ice variants
    - This makes other methods of transportation redundant
- Re-add all-sided pistons, and similar weird blocks
    - These can exist in legitimate worlds, useful decoration

For Minecraft 1.16:

- Throw invisibility potion to turn item frame invisible

For Minecraft 1.17:

- Set `playersSleepingPercentage` rule to `101` by default
    - Supersedes manual implementation of sleep blocking

For Minecraft 1.18:

- Revert to previous cave generation
    - Huge open caverns are not actually fun to explore :(
