package dtteam.dtru.init;

import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;
import com.dtteam.dynamictrees.block.CommonVoxelShapes;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.event.RegistryEvent;
import com.dtteam.dynamictrees.event.TypeRegistryEvent;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.worldgen.featurecancellation.TreeFeatureCanceller;
import dev.worldgen.lithostitched.api.util.Weighted;
import dev.worldgen.lithostitched.api.util.WeightedList;
import dev.worldgen.lithostitched.worldgen.feature.SimplePlacedFeature;
import dev.worldgen.lithostitched.worldgen.feature.config.SelectConfig;
import dev.worldgen.lithostitched.worldgen.feature.config.SimplePlacedConfig;
import dev.worldgen.lithostitched.worldgen.feature.config.WeightedSelectorConfig;
import dtteam.dtru.DynamicTreesRU;
import dtteam.dtru.cell.DTRUCellKits;
import dtteam.dtru.genfeature.DTRUGenFeatures;
import dtteam.dtru.growthlogic.DTRUGrowthLogicKits;
import dtteam.dtru.tree.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.regions_unexplored.world.level.feature.configuration.GiantBioshroomConfiguration;
import net.regions_unexplored.world.level.feature.configuration.RUTreeConfiguration;
import net.regions_unexplored.world.level.feature.tree.*;
import net.regions_unexplored.world.level.feature.tree.nether.BrimWillowFeature;
import net.regions_unexplored.world.level.feature.tree.nether.TallBrimWillowFeature;

@EventBusSubscriber(modid = DynamicTreesRU.MOD_ID)
public class DTRURegistries {
    public static final VoxelShape MUSHROOM_CAP_CONE_BASE = Shapes.box(5.0D/16, 3.0D/16, 5.0D/16, 11.0D/16, 6.0D/16, 11.0D/16);
    public static final VoxelShape MUSHROOM_CAP_TIP_1 = Shapes.box(6.0D/16, 6.0D/16, 6.0D/16, 10.0D/16, 9.0D/16, 10.0D/16);
    public static final VoxelShape MUSHROOM_CAP_SHORT_ROUND = Shapes.box(4.5D/16, 3.0D/16, 4.5D/16, 11.5D/16, 8.0D/16, 11.5D/16);

    public static final VoxelShape SHORT_ROUND_MUSHROOM = Shapes.or(CommonVoxelShapes.SAPLING_TRUNK, MUSHROOM_CAP_SHORT_ROUND);
    public static final VoxelShape CONE_MUSHROOM = Shapes.or(CommonVoxelShapes.SAPLING_TRUNK, Shapes.or(MUSHROOM_CAP_CONE_BASE, MUSHROOM_CAP_TIP_1));

    public static void setup() {
        CommonVoxelShapes.SHAPES.put(DynamicTreesRU.location("blue_bioshroom").toString(), SHORT_ROUND_MUSHROOM);
        CommonVoxelShapes.SHAPES.put(DynamicTreesRU.location("pink_bioshroom").toString(), CONE_MUSHROOM);
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry(final RegistryEvent<GenFeature> event) {
        if (!event.isEntryOfType(GenFeature.class)) return;
        DTRUGenFeatures.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry(final RegistryEvent<GrowthLogicKit> event) {
        if (!event.isEntryOfType(GrowthLogicKit.class)) return;
        DTRUGrowthLogicKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onCellKitRegistry(final RegistryEvent<CellKit> event) {
        if (!event.isEntryOfType(CellKit.class)) return;
        DTRUCellKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(TypeRegistryEvent<LeavesProperties> event) {
        if (!event.isEntryOfType(LeavesProperties.class)) return;
//        event.registerType(DtruPort.location(DtruPort.MOD_ID, "cobweb"), CobwebLeavesProperties.TYPE);
    }

    @SubscribeEvent
    public static void registerSpeciesTypes(final TypeRegistryEvent<Species> event) {
        if (!event.isEntryOfType(Species.class)) return;
        event.registerType(DynamicTreesRU.location("generate_underwater"), GenUnderwaterSpecies.TYPE);
        event.registerType(DynamicTreesRU.location("cypress"), GenUnderwaterSpecies.TYPE);//Marked for removal
        if (DynamicTreesRU.isDynamicTreesPlusLoaded()){
            DTRUPlusRegistries.registerSpeciesTypes(event);
        }
    }

    @SubscribeEvent
    public static void registerFamilyTypes(final TypeRegistryEvent<Family> event) {
        if (!event.isEntryOfType(Family.class)) return;
        event.registerType(DynamicTreesRU.location("bamboo"), BambooFamily.TYPE);
        event.registerType(DynamicTreesRU.location("eucalyptus"), EucalyptusFamily.TYPE);
        event.registerType(DynamicTreesRU.location("stripped_transition_log"), TransitionLogFamily.TYPE_STRIPPED);
        event.registerType(DynamicTreesRU.location("base_transition_log"), TransitionLogFamily.TYPE_BASE);
        event.registerType(DynamicTreesRU.location("brimwood"), BrimwoodFamily.TYPE);
    }

    public static final FeatureCanceller RU_TREE_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesRU.location("tree"), RUTreeConfiguration.class);
    public static final FeatureCanceller RU_TREE2_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesRU.location("tree_2"), NoneFeatureConfiguration.class){
        @Override
        public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
            final Feature<?> feature = configuredFeature.feature();

            if (configuredFeature.config() instanceof SimplePlacedConfig(Holder<PlacedFeature> feature1)){
                var config = feature1.value().feature().value().config();
                return config instanceof TreeConfiguration || config instanceof RUTreeConfiguration;
            }

            if (configuredFeature.config() instanceof WeightedSelectorConfig(WeightedList<Holder<PlacedFeature>> features)){
                return features.unwrap().stream().map(w -> w.value().value().feature().value())
                        .anyMatch(cf -> cf.feature() instanceof TreeFeature || cf.config() instanceof RUTreeConfiguration);
            }

            return feature instanceof LargeJoshuaTreeFeature ||
                    feature instanceof MediumJoshuaTreeFeature ||
                    feature instanceof SmallSocotraTreeFeature ||
                    feature instanceof CobaltShrubFeature ||
                    feature instanceof BrimWillowFeature ||
                    feature instanceof TallBrimWillowFeature;
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
            return config instanceof TreeConfiguration || config instanceof RUTreeConfiguration;
        }
        @Override
        public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
            final FeatureConfiguration featureConfig = configuredFeature.config();

            if (isConfigClass(featureConfig)) {
//                if (featureConfig instanceof TreeConfiguration treeConfiguration
//                        && !treeConfiguration.decorators.isEmpty()
//                        && treeConfiguration.decorators.getFirst() instanceof BlackwoodBioshroomDecorator){
//                    return false;
//                }
                String nameSpace = "";
                var firstFeature = configuredFeature.getFeatures().findFirst();
                if (firstFeature.isEmpty()) return false;
                final ConfiguredFeature<?, ?> nextConfiguredFeature = firstFeature.get();
                final FeatureConfiguration nextFeatureConfig = nextConfiguredFeature.config();
                final ResourceLocation featureRegistryName = BuiltInRegistries.FEATURE.getKey(nextConfiguredFeature.feature());

                if (featureRegistryName != null) {
                    nameSpace = featureRegistryName.getNamespace();
                }
                return isConfigClass(nextFeatureConfig) && !nameSpace.isEmpty() &&
                        featureCancellations.shouldCancelNamespace(nameSpace); // Removes any individual trees.
            }

            return false;
        }
    };

    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final RegistryEvent<FeatureCanceller> event) {
        if (!event.isEntryOfType(FeatureCanceller.class)) return;
        event.getRegistry().registerAll(RU_TREE_CANCELLER, RU_TREE2_CANCELLER, RU_MUSHROOM_CANCELLER, RU_MUSHROOM2_CANCELLER, TREE_NO_SHROOMS_CANCELLER);
    }
}
