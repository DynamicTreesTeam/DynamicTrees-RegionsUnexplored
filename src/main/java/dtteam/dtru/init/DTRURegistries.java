package dtteam.dtru.init;

import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.worldgen.featurecancellation.TreeFeatureCanceller;
import dtteam.dtru.DynamicTreesRU;
import dtteam.dtru.cell.DTRUCellKits;
import dtteam.dtru.genfeature.DTRUGenFeatures;
import dtteam.dtru.growthlogic.DTRUGrowthLogicKits;
import dtteam.dtru.tree.BambooFamily;
import dtteam.dtru.tree.EucalyptusFamily;
import dtteam.dtru.tree.GenUnderwaterSpecies;
import dtteam.dtru.tree.TransitionLogFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.regions_unexplored.world.features.treedecorators.BlackwoodBioshroom;
import net.regions_unexplored.world.level.feature.configuration.GiantBioshroomConfiguration;
import net.regions_unexplored.world.level.feature.configuration.RuTreeConfiguration;
import net.regions_unexplored.world.level.feature.tree.*;

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
    }

    @SubscribeEvent
    public static void registerFamilyTypes(final TypeRegistryEvent<Family> event) {
        event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "bamboo"), BambooFamily.TYPE);
        event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "eucalyptus"), EucalyptusFamily.TYPE);
        event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "stripped_transition_log"), TransitionLogFamily.TYPE_STRIPPED);
        event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "base_transition_log"), TransitionLogFamily.TYPE_BASE);
    }

    public static final FeatureCanceller RU_TREE_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesRU.location("tree"), RuTreeConfiguration.class);
    public static final FeatureCanceller RU_TREE2_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesRU.location("tree_2"), NoneFeatureConfiguration.class){
        @Override
        public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
            final Feature<?> featureConfig = configuredFeature.feature();
            return featureConfig instanceof LargeJoshuaTreeFeature ||
                    featureConfig instanceof MediumJoshuaTreeFeature ||
                    featureConfig instanceof SmallSocotraTreeFeature;
        }
    };
    public static final FeatureCanceller RU_MUSHROOM_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesRU.location("mushroom"), GiantBioshroomConfiguration.class);
    public static final FeatureCanceller RU_MUSHROOM2_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesRU.location("mushroom_2"), NoneFeatureConfiguration.class){
        @Override
        public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
            final Feature<?> featureConfig = configuredFeature.feature();
            return featureConfig instanceof YellowBioshroomShrubFeature ||
                    featureConfig instanceof CobaltShrubFeature ||
                    configuredFeature.config() instanceof HugeMushroomFeatureConfiguration;
        }
    };
    public static final FeatureCanceller TREE_NO_SHROOMS_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesRU.location("tree_no_shrooms"), NoneFeatureConfiguration.class){
        private boolean isConfigClass (FeatureConfiguration config){
            return config instanceof TreeConfiguration || config instanceof RuTreeConfiguration;
        }
        @Override
        public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
            final FeatureConfiguration featureConfig = configuredFeature.config();

            if (isConfigClass(featureConfig)) {
                if (featureConfig instanceof TreeConfiguration treeConfiguration && treeConfiguration.decorators.size() > 0 && treeConfiguration.decorators.get(0) instanceof BlackwoodBioshroom){
                    return false;
                }
                String nameSpace = "";
                final ConfiguredFeature<?, ?> nextConfiguredFeature = configuredFeature.getFeatures().findFirst().get();
                final FeatureConfiguration nextFeatureConfig = nextConfiguredFeature.config();
                final ResourceLocation featureRegistryName = ForgeRegistries.FEATURES.getKey(nextConfiguredFeature.feature());
                if (featureRegistryName != null) {
                    nameSpace = featureRegistryName.getNamespace();
                }
                return isConfigClass(nextFeatureConfig) && !nameSpace.equals("") &&
                        featureCancellations.shouldCancelNamespace(nameSpace); // Removes any individual trees.
            }

            return false;
        }
    };

    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<FeatureCanceller> event) {
        event.getRegistry().registerAll(RU_TREE_CANCELLER, RU_TREE2_CANCELLER, RU_MUSHROOM_CANCELLER, RU_MUSHROOM2_CANCELLER, TREE_NO_SHROOMS_CANCELLER);
    }

}
