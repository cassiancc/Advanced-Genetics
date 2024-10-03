package com.technovision.advancedgenetics.api.screen;

import com.mojang.datafixers.util.Function4;
import com.technovision.advancedgenetics.api.blockentity.AbstractProcessingBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public abstract class AbstractGeneticsScreenHandler extends ScreenHandler {

    private final PropertyDelegate delegate;
    private final Inventory inventory;
    private final int inputSlots;
    private final int outputSlots;
    private final World world;
    private final AbstractProcessingBlockEntity blockEntity;

    public AbstractGeneticsScreenHandler(ScreenHandlerType<?> screenHandlerType, int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, Inventory inventory, PropertyDelegate delegate, int inputSlots, int outputSlots) {
        super(screenHandlerType, syncId);
        this.delegate = delegate;
        this.inventory = inventory;
        this.inputSlots = inputSlots;
        this.outputSlots = outputSlots;
        this.world = playerInventory.player.getWorld();
        this.blockEntity = (AbstractProcessingBlockEntity) blockEntity;
        addPlayerInventorySlots(playerInventory);
        inventory.onOpen(playerInventory.player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        int blockEntitySlots = inputSlots + outputSlots;
        Slot sourceSlot = slots.get(invSlot);
        if (!sourceSlot.hasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyStack = sourceStack.copy();

        if (invSlot < 36) {
            if (!insertItem(sourceStack, 36, 36 + inputSlots, false)) {
                return ItemStack.EMPTY;
            }
        } else if (invSlot < 36 + inputSlots) {
            if (!insertItem(sourceStack, 0, 36, false))  {
                return ItemStack.EMPTY;
            }
        } else if (invSlot >= 36 + inputSlots && invSlot < 36 + blockEntitySlots) {
            if (!insertItem(sourceStack, 0, 36, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.setStack(ItemStack.EMPTY);
        } else {
            sourceSlot.markDirty();
        }
        sourceSlot.onTakeItem(player, sourceStack);
        return copyStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    protected <T> void addSlots(Function4<Inventory, Integer, Integer, Integer, Slot> slotType, Inventory container, int xOrigin, int yOrigin) {
        addSlots(slotType, container, 1, 1, 0, 1, xOrigin, yOrigin);
    }

    protected <T> void addSlots(Function4<Inventory, Integer, Integer, Integer, Slot> slotType, Inventory container, int startIndex, int totalSlots, int xOrigin, int yOrigin) {
        addSlots(slotType, container, 1, 1, startIndex, totalSlots, xOrigin, yOrigin);
    }

    protected <T> void addSlots(Function4<Inventory, Integer, Integer, Integer, Slot> slotType, Inventory container, int rows, int columns, int startIndex, int totalSlots, int xOrigin, int yOrigin) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int slotIndex = column + row * columns + startIndex;
                int x = xOrigin + column * 18;
                int y = yOrigin + row * 18;
                if (slotIndex < startIndex + totalSlots) {
                    this.addSlot(slotType.apply(container, slotIndex, x, y));
                }
            }
        }
    }

    public void addPlayerInventorySlots(Inventory inventory) {
        addSlots(Slot::new, inventory, 3, 9, 9, 27, 8, 84);
        addSlots(Slot::new, inventory, 1, 9, 0, 9, 8, 142);
    }

    public PropertyDelegate getPropertyDelegate() {
        return delegate;
    }

    public AbstractProcessingBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public World getWorld() {
        return world;
    }

    public Inventory getClientInventory() {
        return inventory;
    }
}
