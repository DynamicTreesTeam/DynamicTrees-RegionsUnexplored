package dtteam.dtru.init;

import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import dtteam.dtru.DynamicTreesRU;
import dtteam.dtru.cell.DTRUCellKits;
import dtteam.dtru.genfeature.DTRUGenFeatures;
import dtteam.dtru.growthlogic.DTRUGrowthLogicKits;
import dtteam.dtru.tree.GenUnderwaterSpecies;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class DTRURegistries {

    @SubscribeEvent
    public static void onGenFeatureRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GenFeature> event) {
        DTRUGenFeatures.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GrowthLogicKit> event) {
        DTRUGrowthLogicKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onCellKitRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<CellKit> event) {
        DTRUCellKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(TypeRegistryEvent<LeavesProperties> event) {
        //event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "cobweb"), CobwebLeavesProperties.TYPE);
    }

    @SubscribeEvent
    public static void registerSpeciesTypes(final TypeRegistryEvent<Species> event) {
        event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "cypress"), GenUnderwaterSpecies.TYPE);
        //event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "maple"), MapleSpecies.TYPE);
        //event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "generates_on_stone"), GenOnStoneSpecies.TYPE);
    }

    @SubscribeEvent
    public static void registerSpecies(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<Species> event) {
        // Registers fake species for generating bushes.
//        event.getRegistry().registerAll(new Bush("flowering_oak_bush", new ResourceLocation("oak_log"), new ResourceLocation("oak_leaves"), new ResourceLocation("biomesoplenty", "flowering_oak_leaves")));
//        event.getRegistry().registerAll(new Bush("oak_bush", new ResourceLocation("oak_log"), new ResourceLocation("oak_leaves")));
//        event.getRegistry().registerAll(new Bush("infested_oak_bush", new ResourceLocation("oak_log"), new ResourceLocation("oak_leaves"), new ResourceLocation("cobweb")));
//        event.getRegistry().registerAll(new Bush("silk_bush", new ResourceLocation("oak_log"), new ResourceLocation("cobweb")));
//        event.getRegistry().registerAll(new Bush("acacia_bush", new ResourceLocation("acacia_log"), new ResourceLocation("acacia_leaves")).addAcceptableSoils(SoilHelper.SAND_LIKE));
//        event.getRegistry().registerAll(new Bush("spruce_bush", new ResourceLocation("spruce_log"), new ResourceLocation("spruce_leaves")));

    }
}
