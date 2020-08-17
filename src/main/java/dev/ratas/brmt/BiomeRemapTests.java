package dev.ratas.brmt;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * BiomeRemapTests
 */
public class BiomeRemapTests extends JavaPlugin {
    private static final String CUSTOM_WORLD_NAME = "more_custom_world";

    @Override
    public void onEnable() {
        addPopulator(getServer().createWorld(new WorldCreator(CUSTOM_WORLD_NAME)));
        for (World world : getServer().getWorlds()) {
            addPopulator(world);
        }
    }

    private void addPopulator(World world) {
        world.getPopulators().add(new CustomPopulator(this, world));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Need a player");
            return true;
        }
        Player player = (Player) sender;
        Location loc = player.getLocation();
        Biome biome = player.getWorld().getBiome(loc.getBlockX(), 0, loc.getBlockZ());
        player.sendMessage("Biome here: " + biome);
        return true;
    }
}