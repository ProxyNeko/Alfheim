package alfheimrsmoons.init;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.entity.EntityArrowAM;
import alfheimrsmoons.entity.EntitySeedPouch;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class AMEntities {
    private static int nextEntityId = 0;

    public static void registerEntities() {
        registerEntity(EntityArrowAM.class, "Arrow", 64, 20, false);
        registerEntity(EntitySeedPouch.class, "SeedPouch", 64, 10, true);
    }

    /**
     * @param entityClass          The entity class
     * @param entityName           A unique name for the entity
     * @param trackingRange        The range at which MC will send tracking updates
     * @param updateFrequency      The frequency of tracking updates
     * @param sendsVelocityUpdates Whether to send velocity information packets as well
     * @see net.minecraft.entity.EntityList
     * @see net.minecraft.entity.EntityTracker
     */
    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(new ResourceLocation(AlfheimrsMoons.MOD_ID, entityName), entityClass, entityName, nextEntityId++, AlfheimrsMoons.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    private static ResourceLocation registerLootTable(String id) {
        return LootTableList.register(new ResourceLocation(AlfheimrsMoons.MOD_ID, id));
    }
}
