package com.technovision.advancedgenetics.registry;

import com.technovision.advancedgenetics.AdvancedGenetics;
import com.technovision.advancedgenetics.common.recipe.cellanalyzer.CellAnalyzerRecipe;
import com.technovision.advancedgenetics.common.recipe.cellanalyzer.CellAnalyzerRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RecipeRegistry {

    public static void registerRecipes() {
        // Cell Analyzer
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(AdvancedGenetics.MOD_ID, CellAnalyzerRecipeSerializer.ID), CellAnalyzerRecipeSerializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(AdvancedGenetics.MOD_ID, CellAnalyzerRecipe.Type.ID), CellAnalyzerRecipe.Type.INSTANCE);
    }
}
