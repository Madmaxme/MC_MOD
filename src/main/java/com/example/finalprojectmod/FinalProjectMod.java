package com.example.finalprojectmod;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey; // Add TagKey import
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FinalProjectMod.MODID)
public class FinalProjectMod {
        // Define mod id in a common place for everything to reference
        public static final String MODID = "finalprojectmod";

        // Directly reference a slf4j logger
        public static final Logger LOGGER = LogUtils.getLogger();

        // Create a Deferred Register to hold Blocks which will all be registered under
        // the "finalprojectmod" namespace
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

        // Create a Deferred Register to hold Items which will all be registered under
        // the "finalprojectmod" namespace
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

        // Create a Deferred Register to hold CreativeModeTabs which will all be
        // registered under the "finalprojectmod" namespace
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, MODID);

        // ADD TOOL MATERIALS HERE
        // ADD TOOL MATERIALS HERE
        public static final ToolMaterial SAPPHIRE_TIER = new ToolMaterial(
                        BlockTags.INCORRECT_FOR_IRON_TOOL, // incorrectBlocksForDrops
                        1500, // uses
                        8.0F, // speed
                        3.0F, // attackDamageBonus
                        10, // enchantmentValue
                        ItemTags.PLANKS // repairIngredient (TagKey<Item>)
        );
        public static final ToolMaterial GOD_TIER = SAPPHIRE_TIER;

        // ... (Blocks)
        public static final DeferredBlock<Block> CORGI_DISPENSER_BLOCK = BLOCKS.registerSimpleBlock(
                        "corgi_dispenser_block",
                        BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.STONE)
                                        .strength(1.0f, 6.0f) // Hardness, Resistance
                                        .lightLevel(state -> 10) // Emits light level 10
                                        .sound(net.minecraft.world.level.block.SoundType.STONE) // Stone sound when
                                                                                                // stepped on or broken
        );

        public static final DeferredBlock<Block> SAPPHIRE_BLOCK = BLOCKS.registerSimpleBlock("sapphire_block",
                        BlockBehaviour.Properties.of()
                                .mapColor(MapColor.STONE)
                                .strength(1.0f, 6.0f) // Hardness, Resistance
                                .lightLevel(state -> 10) // Emits light level 10
                                .sound(SoundType.STONE) // Stone sound when stepped on or broken
                );


        // ADD BLOCK ITEMS HERE
        public static final DeferredItem<BlockItem> CORGI_DISPENSER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
                        "corgi_dispenser_block",
                        CORGI_DISPENSER_BLOCK);


        public static final DeferredItem<BlockItem> SAPPHIRE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
                "sapphire_block",
                SAPPHIRE_BLOCK);


        // ADD ITEMS HERE
        public static final DeferredItem<Item> TRANSMUTATION_WAND = ITEMS.register("transmutation_wand",
                        registryName -> new Item(new Item.Properties()
                                        .setId(ResourceKey.create(Registries.ITEM, registryName))));

        public static final DeferredItem<Item> CORGI_SPAWN_EGG = ITEMS.register("corgi_spawn_egg",
                        (registryName) -> new net.minecraft.world.item.SpawnEggItem(
                                        ModEntities.CORGI.get(),
                                        new Item.Properties().setId(
                                                        net.minecraft.resources.ResourceKey.create(
                                                                        net.minecraft.core.registries.Registries.ITEM,
                                                                        registryName))));

        public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire",
                        registryName -> new Item(new Item.Properties()
                                        .setId(ResourceKey.create(Registries.ITEM, registryName))));

        public static final DeferredItem<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
                        registryName -> new Item(new Item.Properties()
                                .setId(ResourceKey.create(Registries.ITEM, registryName))
                                .durability(5000)
                                .pickaxe(
                                        SAPPHIRE_TIER,
                                        50.0F, // attack damage
                                        1.6F //attack speed
                                )
                        )
        );

        public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT,
                        MODID);

        public static final DeferredItem<Item> PIZZA_ITEM =
                ITEMS.register("pizza_item", registryName ->
                        new Item(new Item.Properties()
                                .setId(ResourceKey.create(Registries.ITEM, registryName))
                                .food(new FoodProperties.Builder()
                                        .nutrition(5)
                                        .saturationModifier(0.5f)
                                        .build()
                                )
                        )
                );

        // ADD SOUNDS HERE
        public static final DeferredHolder<SoundEvent, SoundEvent> CORGI_BARK = SOUNDS.register("corgi_bark",
                        () -> SoundEvent.createVariableRangeEvent(
                                        ResourceLocation.fromNamespaceAndPath(MODID, "corgi_bark")));

        public static final DeferredHolder<SoundEvent, SoundEvent> GEM_CHIME = SOUNDS.register("gem_chime",
                        () -> SoundEvent.createVariableRangeEvent(
                                        ResourceLocation.fromNamespaceAndPath(MODID, "gem_chime")));

        public static final DeferredRegister<net.minecraft.core.particles.ParticleType<?>> PARTICLE_TYPES = DeferredRegister
                        .create(Registries.PARTICLE_TYPE, MODID);

        public static final DeferredHolder<net.minecraft.core.particles.ParticleType<?>, net.minecraft.core.particles.SimpleParticleType> SPARKLE_PARTICLE = PARTICLE_TYPES
                        .register("sparkle", () -> new net.minecraft.core.particles.SimpleParticleType(false));

        // Creates a creative tab with the id "finalprojectmod:example_tab" for the
        // example item, that is placed after the combat tab
        // ADD TO CREATIVE TAB HERE
        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
                        .register("example_tab", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.finalprojectmod")) // The language key
                                                                                                    // for the title of
                                                                                                    // your
                                                                                                    // CreativeModeTab
                                        .withTabsBefore(CreativeModeTabs.COMBAT)
                                        .icon(() -> CORGI_DISPENSER_BLOCK_ITEM.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {
                                                output.accept(CORGI_DISPENSER_BLOCK_ITEM.get());
                                                output.accept(TRANSMUTATION_WAND.get());
                                                output.accept(CORGI_SPAWN_EGG.get());
                                                output.accept(SAPPHIRE.get());
                                                output.accept(SAPPHIRE_PICKAXE.get());
                                                output.accept(SAPPHIRE_BLOCK.get());
                                                output.accept(PIZZA_ITEM.get());
                                        }).build());

        // The constructor for the mod class is the first code that is run when your mod
        // is loaded.
        // FML will recognize some parameter types like IEventBus or ModContainer and
        // pass them in automatically.
        public FinalProjectMod(IEventBus modEventBus, ModContainer modContainer) {
                // Register the commonSetup method for modloading
                modEventBus.addListener(this::commonSetup);

                // Register our interactions handler
                NeoForge.EVENT_BUS.register(PlayerInteractions.class);

                // Register the Deferred Register to the mod event bus so blocks get registered
                BLOCKS.register(modEventBus);

                // Register the Deferred Register to the mod event bus so items get registered
                ITEMS.register(modEventBus);

                // Register the Deferred Register to the mod event bus so tabs get registered
                CREATIVE_MODE_TABS.register(modEventBus);

                // Register sounds
                SOUNDS.register(modEventBus);

                // Register particles
                PARTICLE_TYPES.register(modEventBus);

                // Register our custom entities
                ModEntities.register(modEventBus);

                // Register ourselves for server and other game events we are interested in.
                // Note that this is necessary if and only if we want *this* class
                // (FinalProjectMod) to respond directly to events.
                // Do not add this line if there are no @SubscribeEvent-annotated functions in
                // this class, like onServerStarting() below.
                // NeoForge.EVENT_BUS.register(this);

                // Register the item to a creative tab
                modEventBus.addListener(this::addCreative);

                // Client-only: register renderers
                if (FMLEnvironment.dist == Dist.CLIENT) {
                        modEventBus.addListener((EntityRenderersEvent.RegisterRenderers e) -> FinalProjectModClient
                                        .onRegisterRenderers(e));
                }

                // Register our mod's ModConfigSpec so that FML can create and load the config
                // file for us
                modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        }

        private void commonSetup(FMLCommonSetupEvent event) {
                // Some common setup code
                LOGGER.info("HELLO FROM COMMON SETUP");

                if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
                        LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
                }

                LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

                Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));

                // Debugging: Print all registered items for our mod
                LOGGER.info("DEBUG: Printing registered items for finalprojectmod:");
                ITEMS.getEntries().forEach(item -> LOGGER.info("REGISTERED ITEM: {}", item.getId()));

                LOGGER.info("FinalProjectMod: Common setup complete.");
        }

        // Add the example block item to the building blocks tab
        private void addCreative(BuildCreativeModeTabContentsEvent event) {
                if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
                        event.accept(CORGI_DISPENSER_BLOCK_ITEM);
                }
        }

}
