package fr.capymod.events;

import fr.capymod.commons.Commons;
import fr.capymod.entity.ModEntities;
import fr.capymod.entity.custom.CapybaraEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Commons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.CAPYBARA.get(), CapybaraEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void entitySpawnRestrictionEvent(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.CAPYBARA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TamableAnimal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
