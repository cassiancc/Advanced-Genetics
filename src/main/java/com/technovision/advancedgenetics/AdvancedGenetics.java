package com.technovision.advancedgenetics;

import com.technovision.advancedgenetics.commands.AddGeneCommand;
import com.technovision.advancedgenetics.commands.ClearGeneCommand;
import com.technovision.advancedgenetics.commands.GeneArgumentType;
import com.technovision.advancedgenetics.commands.RemoveGeneCommand;
import com.technovision.advancedgenetics.events.GeneticsEvents;
import com.technovision.advancedgenetics.events.KeyInputEvents;
import com.technovision.advancedgenetics.registry.*;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.config.ModConfig;

public class AdvancedGenetics implements ModInitializer {

    public static final String MOD_ID = "advancedgenetics";
    public static final RegistryKey<ItemGroup> TAB_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MOD_ID, "tab"));

    public static final ItemGroup TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ItemRegistry.CELL_ANALYZER))
            .displayName(Text.translatable("itemGroup.advancedgenetics.tab"))
            .build();

    @Override
    public void onInitialize() {
        // Register and load config
        ForgeConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.COMMON, Config.COMMON_SPEC, "AdvancedGenetics.toml");

        // Register in-game items, blocks, entities, and GUIs
        BlockRegistry.registerBlocks();
        ItemRegistry.registerItems();
        BlockEntityRegistry.registerBlockEntities();
        ScreenRegistry.registerScreens();
        RecipeRegistry.registerRecipes();
        Registry.register(Registries.ITEM_GROUP, TAB_KEY, TAB);

        // Register events
        GeneticsEvents.registerEvents();
        KeyInputEvents.registerServerSide();

        // Register commands
        ArgumentTypeRegistry.registerArgumentType(Identifier.of(MOD_ID, "gene"), GeneArgumentType.class, ConstantArgumentSerializer.of(GeneArgumentType::gene));
        CommandRegistrationCallback.EVENT.register(AddGeneCommand::register);
        CommandRegistrationCallback.EVENT.register(RemoveGeneCommand::register);
        CommandRegistrationCallback.EVENT.register(ClearGeneCommand::register);
    }
}
