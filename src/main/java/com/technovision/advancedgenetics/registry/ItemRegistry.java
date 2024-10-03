package com.technovision.advancedgenetics.registry;

import com.technovision.advancedgenetics.AdvancedGenetics;
import com.technovision.advancedgenetics.api.genetics.Entities;
import com.technovision.advancedgenetics.common.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

import static com.technovision.advancedgenetics.AdvancedGenetics.TAB_KEY;

public class ItemRegistry {

    private static final Item.Settings ITEM_SETTINGS = new Item.Settings();

    // Organic Matter and Cells
    public static final Map<EntityType, OrganicMatterItem> MATTER = new HashMap<>();
    public static final Map<EntityType, CellItem> CELLS = new HashMap<>();

    // Tools
    public static final ScalpelItem METAL_SCALPEL = new ScalpelItem(25);
    public static final ScalpelItem DIAMOND_SCALPEL = new ScalpelItem(150);
    public static final ScalpelItem NETHERITE_SCALPEL = new ScalpelItem(300);
    public static final SyringeItem SYRINGE = new SyringeItem();
    public static final Item OVERCLOCKER = new Item(ITEM_SETTINGS);
    public static final Item CROWBAR = new Item(new Item.Settings().maxCount(1).maxDamage(100));

    // Block Items
    public static final BlockItem CELL_ANALYZER = new BlockItem(BlockRegistry.CELL_ANALYZER, ITEM_SETTINGS);
    public static final BlockItem DNA_EXTRACTOR = new BlockItem(BlockRegistry.DNA_EXTRACTOR, ITEM_SETTINGS);
    public static final BlockItem DNA_DECRYPTER = new BlockItem(BlockRegistry.DNA_DECRYPTER, ITEM_SETTINGS);
    public static final BlockItem PLASMID_INFUSER = new BlockItem(BlockRegistry.PLASMID_INFUSER, ITEM_SETTINGS);
    public static final BlockItem BLOOD_PURIFIER = new BlockItem(BlockRegistry.BLOOD_PURIFIER, ITEM_SETTINGS);
    public static final BlockItem PLASMID_INJECTOR = new BlockItem(BlockRegistry.PLASMID_INJECTOR, ITEM_SETTINGS);

    // Other Items
    public static final DnaItem DNA_HELIX = new DnaItem();
    public static final PlasmidItem PLASMID = new PlasmidItem();
    public static final AntiPlasmidItem ANTIPLASMID = new AntiPlasmidItem();
    public static final DragonHealthCrystalItem DRAGON_HEALTH_CRYSTAL = new DragonHealthCrystalItem();

    public static void registerItems() {
        // Block Items
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "cell_analyzer"), CELL_ANALYZER);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "dna_extractor"), DNA_EXTRACTOR);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "dna_decrypter"), DNA_DECRYPTER);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "plasmid_infuser"), PLASMID_INFUSER);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "blood_purifier"), BLOOD_PURIFIER);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "plasmid_injector"), PLASMID_INJECTOR);

        // Tools
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "metal_scalpel"), METAL_SCALPEL);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "diamond_scalpel"), DIAMOND_SCALPEL);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "netherite_scalpel"), NETHERITE_SCALPEL);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "syringe"), SYRINGE);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "overclocker"), OVERCLOCKER);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "crowbar"), CROWBAR);

        // Items
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "dna_helix"), DNA_HELIX);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "plasmid"), PLASMID);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "antiplasmid"), ANTIPLASMID);
        Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, "dragon_health_crystal"), DRAGON_HEALTH_CRYSTAL);

        ItemGroupEvents.modifyEntriesEvent(TAB_KEY).register(itemGroup -> {
            itemGroup.add(METAL_SCALPEL);
            itemGroup.add(DIAMOND_SCALPEL);
            itemGroup.add(NETHERITE_SCALPEL);
            itemGroup.add(SYRINGE);
            itemGroup.add(OVERCLOCKER);
            itemGroup.add(CROWBAR);

            itemGroup.add(CELL_ANALYZER);
            itemGroup.add(DNA_EXTRACTOR);
            itemGroup.add(DNA_DECRYPTER);
            itemGroup.add(PLASMID_INFUSER);
            itemGroup.add(BLOOD_PURIFIER);
            itemGroup.add(PLASMID_INJECTOR);

            itemGroup.add(DNA_HELIX);
            itemGroup.add(PLASMID);
            itemGroup.add(ANTIPLASMID);
            itemGroup.add(DRAGON_HEALTH_CRYSTAL);
        });

        // Organic Matter
        for (Entities entity : Entities.values()) {
            String key = entity.getName() + "_matter";
            OrganicMatterItem matterItem = new OrganicMatterItem(entity.getType());
            Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, key), matterItem);
            MATTER.put(entity.getType(), matterItem);
            ItemGroupEvents.modifyEntriesEvent(TAB_KEY).register(itemGroup -> {
                itemGroup.add(matterItem);
            });
        }

        // Cells
        for (Entities entity : Entities.values()) {
            String key = entity.getName() + "_cell";
            CellItem cellItem = new CellItem(entity.getType(), entity.getColor());
            Registry.register(Registries.ITEM, new Identifier(AdvancedGenetics.MOD_ID, key), cellItem);
            CELLS.put(entity.getType(), cellItem);
            ItemGroupEvents.modifyEntriesEvent(TAB_KEY).register(itemGroup -> {
                itemGroup.add(cellItem);
            });
        }

    }
}
