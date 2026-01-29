package com.example.finalprojectmod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class PlayerInteractions {

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity(); // The player
        ItemStack item = event.getItemStack(); // The item used
        Level world = event.getLevel(); // The world (level)
        InteractionHand hand = event.getHand(); // Main or off-hand

        if (!world.isClientSide) {
            // DO STUFF HERE
            if (item.getItem() == FinalProjectMod.SAPPHIRE.get()) {
                ((ServerLevel) world).sendParticles(FinalProjectMod.SPARKLE_PARTICLE.get(),
                        player.getX(), player.getEyeY(), player.getZ(),
                        10, 0.5, 0.5, 0.5, 0.1);

                float pitch = 0.8f + world.random.nextFloat() * 0.4f;
                world.playSound(null, player.getX(), player.getY(), player.getZ(),
                        FinalProjectMod.GEM_CHIME.get(), SoundSource.PLAYERS, 1.0f, pitch);

                // Optional: Consume item? No, user didn't specify. Just an effect.
                // player.getCooldowns().addCooldown(item.getItem(), 20); // Add cooldown if we
                // want
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity(); // The player
        ItemStack item = event.getItemStack(); // The item used
        Level world = event.getLevel(); // The world
        InteractionHand hand = event.getHand(); // Hand used
        Entity target = event.getTarget(); // Entity being interacted with

        if (!world.isClientSide) {
            // DO STUFF HERE
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level world = event.getLevel();
        InteractionHand hand = event.getHand();
        ItemStack item = player.getItemInHand(hand);
        BlockPos pos = event.getPos();

        if (!world.isClientSide) {
            // DO STUFF HERE
        }
    }
}