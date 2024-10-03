package com.technovision.advancedgenetics.common.block.cellanalyzer;

import com.technovision.advancedgenetics.Config;
import com.technovision.advancedgenetics.api.blockentity.AbstractInventoryBlockEntity;
import com.technovision.advancedgenetics.common.recipe.cellanalyzer.CellAnalyzerRecipe;
import com.technovision.advancedgenetics.registry.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public class CellAnalyzerBlockEntity extends AbstractInventoryBlockEntity {

    public static final int SLOT_COUNT = 2;
    public static final int INPUT_SLOT_INDEX = 0;
    public static final int OUTPUT_SLOT_INDEX = 1;

    private CellAnalyzerRecipe recipe;

    public CellAnalyzerBlockEntity(BlockPos pos, BlockState state) {
        super(DefaultedList.ofSize(SLOT_COUNT, ItemStack.EMPTY),
                BlockEntityRegistry.CELL_ANALYZER_BLOCK_ENTITY,
                pos, state,
                Config.Common.cellAnalyzerEnergyCapacity.get(),
                Config.Common.cellAnalyzerTicksPerOperation.get(),
                Config.Common.cellAnalyzerMaxOverclock.get()
        );
    }

    @Override
    public void updateRecipe() {
        if (world == null || world.isClient()) return;
        if (!getStackInSlot(INPUT_SLOT_INDEX).isEmpty()) {
             world.getRecipeManager().getAllMatches(CellAnalyzerRecipe.Type.INSTANCE, new SimpleInventory(1), world)
                    .stream()
                    .filter(recipe -> ItemStack.canCombine(getStackInSlot(0), recipe.getInput()))
                    .findFirst()
                    .ifPresent(recipe -> {
                        this.recipe = recipe;
                    });
        }
    }

    @Override
    public boolean canProcessRecipe(DynamicRegistryManager manager) {
        if (recipe != null) {
            ItemStack input = getStackInSlot(INPUT_SLOT_INDEX);
            ItemStack output = getStackInSlot(OUTPUT_SLOT_INDEX);
            return getEnergyStorage().getAmount() >= getEnergyRequirement()
                    && (ItemStack.canCombine(input, recipe.getInput()) && input.getCount() >= recipe.getInput().getCount())
                    && (recipe.getOutput(manager).getCount() + output.getCount()) <= recipe.getOutput(manager).getMaxCount()
                    && (ItemStack.canCombine(output, recipe.getOutput(manager)) || output.isEmpty());
        }
        return false;
    }

    @Override
    public void processRecipe(DynamicRegistryManager manager) {
        if (getProgress() < getMaxProgress()) {
            incrementProgress();
        } else {
            setProgress(0);
            decrementSlot(INPUT_SLOT_INDEX, recipe.getInput().getCount());
            if (ThreadLocalRandom.current().nextDouble() <= Config.Common.cellAnalyzerSuccessRate.get()) {
                setOrIncrement(OUTPUT_SLOT_INDEX, recipe.getOutput(manager).copy());
            }
        }
        extractEnergy(getEnergyRequirement());
        markDirty();
    }

    @Override
    public <T extends Recipe<SimpleInventory>> void setRecipe(@Nullable T recipe) {
        this.recipe = (CellAnalyzerRecipe) recipe;
    }

    @Override
    public Recipe<SimpleInventory> getRecipe() {
        return recipe;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CellAnalyzerScreenHandler(syncId, inv, this, this, getPropertyDelegate());
    }

    private int getEnergyRequirement() {
        return Config.Common.cellAnalyzerEnergyPerTick.get() + (Config.Common.overclockEnergy.get() * getOverclock());
    }
}
