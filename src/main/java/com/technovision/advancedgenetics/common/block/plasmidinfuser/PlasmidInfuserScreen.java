package com.technovision.advancedgenetics.common.block.plasmidinfuser;

import com.mojang.blaze3d.systems.RenderSystem;
import com.technovision.advancedgenetics.AdvancedGenetics;
import com.technovision.advancedgenetics.api.screen.*;
import com.technovision.advancedgenetics.common.item.PlasmidItem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class PlasmidInfuserScreen extends AbstractGeneticsScreen<PlasmidInfuserScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(AdvancedGenetics.MOD_ID, "textures/gui/plasmid_infuser_gui.png");

    protected final List<DisplayData> displayData = new ArrayList<>();

    public PlasmidInfuserScreen(PlasmidInfuserScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
        displayData.add(new ProgressDisplayData(handler.getPropertyDelegate(), 0, 1, 83, 37, 60, 9, Direction2D.RIGHT));
        displayData.add(new EnergyDisplayData(handler.getPropertyDelegate(), 2, 3, 10, 23, 12, 40));
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        renderDisplayData(displayData, context.getMatrices(), this.x, this.y);
        renderDisplayTooltip(displayData, context, textRenderer, this.x, this.y, mouseX, mouseY);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
        int overclock = handler.getPropertyDelegate().get(4);
        if (overclock > 0) {
            String text = "x"+overclock;
            context.drawText(textRenderer, text, x, y, 6, true);
        }
        ItemStack plasmid = handler.getClientInventory().getStack(PlasmidInfuserBlockEntity.OUTPUT_SLOT_INDEX);
        if (!plasmid.isEmpty() && plasmid.hasNbt()) {
            String text = String.format("%d/%d", plasmid.getNbt().getInt("count"), PlasmidItem.MAX_GENES);
            context.drawText(textRenderer, text, x, y, 6, true);
        }
    }

}
