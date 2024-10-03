package com.technovision.advancedgenetics.registry;

import com.technovision.advancedgenetics.AdvancedGenetics;
import com.technovision.advancedgenetics.common.block.bloodpurifier.BloodPurifierBlockEntity;
import com.technovision.advancedgenetics.common.block.cellanalyzer.CellAnalyzerBlockEntity;
import com.technovision.advancedgenetics.common.block.dnadecrypter.DnaDecrypterBlockEntity;
import com.technovision.advancedgenetics.common.block.dnaextractor.DnaExtractorBlockEntity;
import com.technovision.advancedgenetics.common.block.plasmidinfuser.PlasmidInfuserBlockEntity;
import com.technovision.advancedgenetics.common.block.plasmidinjector.PlasmidInjectorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import team.reborn.energy.api.EnergyStorage;

public class BlockEntityRegistry {

    public static final BlockEntityType<CellAnalyzerBlockEntity> CELL_ANALYZER_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(CellAnalyzerBlockEntity::new, BlockRegistry.CELL_ANALYZER).build(null);
    public static final BlockEntityType<DnaExtractorBlockEntity> DNA_EXTRACTOR_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(DnaExtractorBlockEntity::new, BlockRegistry.DNA_EXTRACTOR).build(null);
    public static final BlockEntityType<DnaDecrypterBlockEntity> DNA_DECRYPTER_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(DnaDecrypterBlockEntity::new, BlockRegistry.DNA_DECRYPTER).build(null);
    public static final BlockEntityType<PlasmidInfuserBlockEntity> PLASMID_INFUSER_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(PlasmidInfuserBlockEntity::new, BlockRegistry.PLASMID_INFUSER).build(null);
    public static final BlockEntityType<BloodPurifierBlockEntity> BLOOD_PURIFIER_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(BloodPurifierBlockEntity::new, BlockRegistry.BLOOD_PURIFIER).build(null);
    public static final BlockEntityType<PlasmidInjectorBlockEntity> PLASMID_INJECTOR_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(PlasmidInjectorBlockEntity::new, BlockRegistry.PLASMID_INJECTOR).build(null);

    public static void registerBlockEntities() {
        // Register block entity
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AdvancedGenetics.MOD_ID, "cell_analyzer_block_entity"), CELL_ANALYZER_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AdvancedGenetics.MOD_ID, "dna_extractor_block_entity"), DNA_EXTRACTOR_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AdvancedGenetics.MOD_ID, "dna_decrypter_block_entity"), DNA_DECRYPTER_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AdvancedGenetics.MOD_ID, "plasmid_infuser_block_entity"), PLASMID_INFUSER_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AdvancedGenetics.MOD_ID, "blood_purifier_block_entity"), BLOOD_PURIFIER_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AdvancedGenetics.MOD_ID, "plasmid_injector_block_entity"), PLASMID_INJECTOR_BLOCK_ENTITY);

        // Register energy storage for block entity
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.getEnergyStorage(), CELL_ANALYZER_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.getEnergyStorage(), DNA_EXTRACTOR_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.getEnergyStorage(), DNA_DECRYPTER_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.getEnergyStorage(), PLASMID_INFUSER_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.getEnergyStorage(), BLOOD_PURIFIER_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.getEnergyStorage(), PLASMID_INJECTOR_BLOCK_ENTITY);
    }
}
