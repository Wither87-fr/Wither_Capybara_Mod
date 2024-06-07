package fr.capymod.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // dropSelf()
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        // HERE :
        // ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        return List.of();
    }
}
