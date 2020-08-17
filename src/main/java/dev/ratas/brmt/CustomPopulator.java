package dev.ratas.brmt;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomPopulator extends BlockPopulator {
    private final JavaPlugin plugin;
    private final World world;

    public CustomPopulator(JavaPlugin plugin, World world) {
        this.plugin = plugin;
        this.world = world;
    }

    @Override
    public void populate(World world, Random random, Chunk source) {
        checkBiome(source, 0);
        plugin.getServer().getScheduler().runTask(plugin, (task) -> {
            checkBiome(source, 1); // a tick later just in case
        });
    }

    private void checkBiome(Chunk source, long later) {
        int x = source.getX() << 4;
        int z = source.getZ() << 4;
        ChunkSnapshot snapshot = source.getChunkSnapshot(false, true, false);
        System.out.println("Biome in " + String.format("%s (%s)", this.world.getName(), world.getName()) + " at " + x
                + ", " + z + String.format(" %d later:", later));
        try {
            System.out.println(snapshot.getBiome(0, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(world.getBiome(x, 0, z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}