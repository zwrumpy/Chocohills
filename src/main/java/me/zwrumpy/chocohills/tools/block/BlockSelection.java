package me.zwrumpy.chocohills.tools.block;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BlockSelection {

    private List<Block> sphere(@Nonnull Block b) {
        List<Block> blocks = new ArrayList<>(26);
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x != 0 || y != 0 || z != 0)
                        blocks.add(b.getRelative(x, y, z));
                }
            }
        }
        return blocks;
    }
    @ParametersAreNonnullByDefault
    public static List<Block> sphere(Location center, int radius) {
        List<Block> sphere = new ArrayList<>();
        World world = center.getWorld();
        int r2 = radius * radius;
        for (int x = -radius; x <= radius; x++) {
            int x2 = x * x;
            for (int y = -radius; y <= radius; y++) {
                int y2 = y * y;
                for (int z = -radius; z <= radius; z++) {
                    if (x2 + y2 + z * z < r2) {
                        Block block = world.getBlockAt(x + center.getBlockX(), y + center.getBlockY(), z + center.getBlockZ());
                        sphere.add(block);
                    }
                }
            }
        }
        return sphere;
    }
    @ParametersAreNonnullByDefault
    public List<Block> cuboid(Location loc, BlockFace blockFace, Integer depth) {
        Location loc2 = loc.clone();
        depth = Integer.valueOf(depth.intValue() - 1);
        switch (blockFace) {
            case SOUTH:
                loc.add(-1.0D, 1.0D, -depth.intValue());
                loc2.add(1.0D, -1.0D, 0.0D);
                break;
            case WEST:
                loc.add(depth.intValue(), 1.0D, -1.0D);
                loc2.add(0.0D, -1.0D, 1.0D);
                break;
            case EAST:
                loc.add(-depth.intValue(), 1.0D, 1.0D);
                loc2.add(0.0D, -1.0D, -1.0D);
                break;
            case NORTH:
                loc.add(1.0D, 1.0D, depth.intValue());
                loc2.add(-1.0D, -1.0D, 0.0D);
                break;
            case UP:
                loc.add(-1.0D, -depth.intValue(), -1.0D);
                loc2.add(1.0D, 0.0D, 1.0D);
                break;
            case DOWN:
                loc.add(1.0D, depth.intValue(), 1.0D);
                loc2.add(-1.0D, 0.0D, -1.0D);
                break;
        }
        return selectionCuboid(loc, loc2);
    }
    @ParametersAreNonnullByDefault
    public List<Block> line(Location loc, BlockFace blockFace, Integer depth) {
        Location loc2 = loc.clone();
        depth = Integer.valueOf(depth.intValue() - 1);
        switch (blockFace) {
            case SOUTH:
                loc.add(0.0D, 0.0D, -depth.intValue());
                break;
            case WEST:
                loc.add(depth.intValue(), 0.0D, 0.0D);
                break;
            case EAST:
                loc.add(-depth.intValue(), 0.0D, 0.0D);
                break;
            case NORTH:
                loc.add(0.0D, 0.0D, depth.intValue());
                break;
            case UP:
                loc.add(0.0D, -depth.intValue(), 0.0D);
                break;
            case DOWN:
                loc.add(0.0D, depth.intValue(), 0.0D);
                break;
        }
        return selectionCuboid(loc, loc2);
    }
    @ParametersAreNonnullByDefault
    private List<Block> selectionCuboid(Location pos1, Location pos2) {
        List<Block> blockList = new ArrayList<>();
        int topBlockX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int bottomBlockX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int topBlockY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int bottomBlockY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int topBlockZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());
        int bottomBlockZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        World world = pos1.getWorld();

        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++)
                    blockList.add(world.getBlockAt(x, y, z));
            }
        }
        return blockList;
    }
}
