package me.zwrumpy.chocohills.util.entities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class EntityScan {
    public static List<Entity> getEntitiesAroundPoint(Location location, double radius) {
        List<Entity> entities = new ArrayList<Entity>();
        World world = location.getWorld();

        // To find chunks we use chunk coordinates (not block coordinates!)
        int smallX = (int) Math.floor((location.getX() - radius) / 16.0D);
        int bigX = (int) Math.floor((location.getX() + radius) / 16.0D);
        int smallZ = (int) Math.floor((location.getZ() - radius) / 16.0D);
        int bigZ = (int) Math.floor((location.getZ() + radius) / 16.0D);

        for (int x = smallX; x <= bigX; x++) {
            for (int z = smallZ; z <= bigZ; z++) {
                if (world.isChunkLoaded(x, z)) {
                    // Add all entities from this chunk to the list
                    entities.addAll(Arrays.asList(world.getChunkAt(x, z).getEntities()));
                }
            }
        }
        // Create an iterator so we can loop through the list while removing entries
        Iterator<Entity> entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
            // If the entity is outside of the sphere...
            if (entityIterator.next().getLocation().distanceSquared(location) > radius * radius) {
                entityIterator.remove();
            }
        }
        return entities;
    }
}
