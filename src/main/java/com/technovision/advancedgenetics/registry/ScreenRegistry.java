package com.technovision.advancedgenetics.registry;

import com.technovision.advancedgenetics.AdvancedGenetics;
import com.technovision.advancedgenetics.common.block.bloodpurifier.BloodPurifierScreenHandler;
import com.technovision.advancedgenetics.common.block.cellanalyzer.CellAnalyzerScreenHandler;
import com.technovision.advancedgenetics.common.block.dnadecrypter.DnaDecrypterScreenHandler;
import com.technovision.advancedgenetics.common.block.dnaextractor.DnaExtractorScreenHandler;
import com.technovision.advancedgenetics.common.block.plasmidinfuser.PlasmidInfuserScreenHandler;
import com.technovision.advancedgenetics.common.block.plasmidinjector.PlasmidInjectorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ScreenRegistry {

    public static ExtendedScreenHandlerType<CellAnalyzerScreenHandler> CELL_ANALYZER_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(CellAnalyzerScreenHandler::new);
    public static ExtendedScreenHandlerType<DnaExtractorScreenHandler> DNA_EXTRACTOR_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(DnaExtractorScreenHandler::new);
    public static ExtendedScreenHandlerType<DnaDecrypterScreenHandler> DNA_DECRYPTER_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(DnaDecrypterScreenHandler::new);
    public static ExtendedScreenHandlerType<PlasmidInfuserScreenHandler> PLASMID_INFUSER_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(PlasmidInfuserScreenHandler::new);
    public static ExtendedScreenHandlerType<BloodPurifierScreenHandler> BLOOD_PURIFIER_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(BloodPurifierScreenHandler::new);
    public static ExtendedScreenHandlerType<PlasmidInjectorScreenHandler> PLASMID_INJECTOR_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(PlasmidInjectorScreenHandler::new);

    public static void registerScreens() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(AdvancedGenetics.MOD_ID, "cell_analyzer_menu"), CELL_ANALYZER_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(AdvancedGenetics.MOD_ID, "dna_extractor_menu"), DNA_EXTRACTOR_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(AdvancedGenetics.MOD_ID, "dna_decrypter_menu"), DNA_DECRYPTER_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(AdvancedGenetics.MOD_ID, "plasmid_infuser_menu"), PLASMID_INFUSER_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(AdvancedGenetics.MOD_ID, "blood_purifier_menu"), BLOOD_PURIFIER_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(AdvancedGenetics.MOD_ID, "plasmid_injector_menu"), PLASMID_INJECTOR_SCREEN_HANDLER);
    }
}
