package com.technovision.advancedgenetics.api.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;


public abstract class AbstractInventoryBlockEntity extends AbstractProcessingBlockEntity implements ImplementedInventory {

    private final DefaultedList<ItemStack> inventory;

    public AbstractInventoryBlockEntity(DefaultedList<ItemStack> inventory, BlockEntityType<?> type, BlockPos pos, BlockState state, long energyCapacity, int maxProgress, int maxOverclock) {
        super(type, pos, state, energyCapacity, maxProgress, maxOverclock);
        this.inventory = inventory;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public void dropContents() {
        ItemScatterer.spawn(world, pos, this);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return slot == 1;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return slot == 0;
    }

    public ItemStack getStackInSlot(int slot) {
        return inventory.get(slot);
    }

    public ItemStack setStackInSlot(int slot, ItemStack stack) {
        return inventory.set(slot, stack);
    }

    public void incrementSlot(int pSlot, int pAmount) {
        ItemStack temp = this.getStackInSlot(pSlot);
        if (temp.getCount() + pAmount <= temp.getMaxCount()) {
            temp.setCount(temp.getCount() + pAmount);
        }
        this.setStackInSlot(pSlot, temp);
    }

    public void setOrIncrement(int slot, ItemStack stackToSet) {
        if (!stackToSet.isEmpty()) {
            if (getStackInSlot(slot).isEmpty()) {
                setStackInSlot(slot, stackToSet);
            } else {
                incrementSlot(slot, stackToSet.getCount());
            }
        }
    }

    public void decrementSlot(int slot, int pAmount) {
        ItemStack temp = this.getStackInSlot(slot);
        if (temp.isEmpty()) return;
        if (temp.getCount() - pAmount < 0) return;

        temp.decrement(pAmount);
        if (temp.getCount() <= 0) {
            this.setStackInSlot(slot, ItemStack.EMPTY);
        } else {
            this.setStackInSlot(slot, temp);
        }
    }
}
