package fr.capymod.item;

import fr.capymod.commons.Commons;
import fr.capymod.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Commons.MOD_ID);



    public static final RegistryObject<Item> CAPYBARA_SPAWN_EGG = ITEMS.register("capybara_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.CAPYBARA, 0x5a3106, 0x9a540a, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
