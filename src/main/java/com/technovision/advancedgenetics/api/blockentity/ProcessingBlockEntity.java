package com.technovision.advancedgenetics.api.blockentity;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.DynamicRegistryManager;
import org.jetbrains.annotations.Nullable;


public interface ProcessingBlockEntity {

    void tick();

    void updateRecipe();

    boolean canProcessRecipe(DynamicRegistryManager manager);

    void processRecipe(DynamicRegistryManager manager);

    <T extends Recipe<SimpleInventory>> void setRecipe(@Nullable T pRecipe);

    Recipe<SimpleInventory> getRecipe();

    int getProgress();

    void setProgress(int pProgress);

    void incrementProgress();

    void dropContents();
}
