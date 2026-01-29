# Final Project Mod (CPSC 298)

## Overview
This is the final project mod for CPSC 298. It is a collaborative effort.

**Current Mod ID:** `finalprojectmod`
**Minecraft Version:** 1.21.7
**NeoForge Version:** 21.7.25-beta

## Implemented Features (User Side)
The following features have been implemented and are ready in the codebase:

1.  **Custom Item: Sapphire**
    *   Class: `ModItems.SAPPHIRE`
    *   Texture: `assets/finalprojectmod/textures/item/sapphire.png`
    *   Functionality: Right-click to play a sound and spawn particles.

2.  **Custom Tool: Sapphire Pickaxe**
    *   Class: `ModItems.SAPPHIRE_PICKAXE`
    *   Texture: `assets/finalprojectmod/textures/item/sapphire_pickaxe.png`
    *   Tier: Custom `SAPPHIRE` tier (better than Iron).

3.  **Particles**
    *   Type: `SparkleParticle`
    *   Texture: `assets/finalprojectmod/textures/particle/sparkle.png`

4.  **Sounds**
    *   Sound Event: `GEM_CHIME`
    *   File location expected: `assets/finalprojectmod/sounds/gem_chime.ogg`

## Handoff Status & Notes for Partner

### 1. Build & Run
The project compiles and the client launches successfully.
*   Run command: `./gradlew runClient`

### 2. Known Issues (Needs Investigation)
*   **Recipes Not Loading:**
    *   Recipe JSON files exist in `src/main/resources/data/finalprojectmod/recipes/`.
    *   `pack.mcmeta` is set to `pack_format: 64`.
    *   However, the recipes (`sapphire_pickaxe.json`, `transmutation_wand.json`) are not showing up in-game.
    *   **Action Item:** Please allow extra time to investigate why the data pack portion is not loading these recipes. It might be a version mismatch in `pack.mcmeta` or a strictly NeoForge specific issue.

### 3. Missing Assets
*   **Sound:** You need to add a `gem_chime.ogg` file to `src/main/resources/assets/finalprojectmod/sounds/`.
*   **Textures:** Placeholder textures have been generated, but you may want to replace them with final art.

## Your TODO List
1.  Implement the Custom Block.
2.  Add Loot Table for the block.
3.  Implement Right-click mechanics for the block.
4.  Add the Custom Food Item.
5.  Debug and fix the Recipe loading issue.
# MC_MOD
