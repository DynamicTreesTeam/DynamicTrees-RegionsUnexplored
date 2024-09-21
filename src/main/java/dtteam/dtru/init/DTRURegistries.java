package dtteam.dtru.init;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class DTRURegistries {

//    public static final VoxelShape MUSHROOM_STEM_LONG = Block.box(7D, 0D, 7D, 9D, 10D, 9D);
//    public static final VoxelShape TALL_MUSHROOM_CAP_FLAT = Block.box(5.0D, 7.0D, 5.0D, 11.0D, 10.0D, 11.0D);
//    public static final VoxelShape SMALL_MUSHROOM_CAP_FLAT = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 7.0D, 11.0D);
//    public static final VoxelShape MUSHROOM_CAP_SHORT_ROUND = Block.box(5.0D, 3.0D, 5.0D, 11.0D, 7.0D, 11.0D);
//    public static final VoxelShape SOUL_SHROOM_CAP = Block.box(5.5D, 3.0D, 5.5D, 10.5D, 10.0D, 10.5D);
//    public static final VoxelShape SYTHIAN_CAP_A = Block.box(5D, 3D, 5D, 11D, 5D, 11D);
//    public static final VoxelShape SYTHIAN_CAP_B = Block.box(4D, 6D, 4D, 12D, 8D, 12D);
//    public static final VoxelShape SYTHIAN_CAP_C = Block.box(5D, 9D, 5D, 11D, 11D, 11D);
//    public static final VoxelShape SHULKREN_CAP_A = Block.box(4D, 3D, 4D, 12D, 6D, 12D);
//    public static final VoxelShape SHULKREN_CAP_B = Block.box(5D, 6D, 5D, 11D, 9D, 11D);
//    public static final VoxelShape SHULKREN_CAP_C = Block.box(6D, 9D, 6D, 10D, 11D, 10D);
//
//    public static final VoxelShape TALL_FLAT_MUSHROOM = Shapes.or(MUSHROOM_STEM_LONG, TALL_MUSHROOM_CAP_FLAT);
//    public static final VoxelShape SMALL_FLAT_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SMALL_MUSHROOM_CAP_FLAT);
//    public static final VoxelShape SHORT_ROUND_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, MUSHROOM_CAP_SHORT_ROUND);
//    public static final VoxelShape SOUL_SHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SOUL_SHROOM_CAP);
//    public static final VoxelShape SYTHIAN_MUSHROOM = Shapes.or(MUSHROOM_STEM_LONG, SYTHIAN_CAP_A, SYTHIAN_CAP_B, SYTHIAN_CAP_C);
//    public static final VoxelShape SHULKREN_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SHULKREN_CAP_A, SHULKREN_CAP_B, SHULKREN_CAP_C);
//
//
//    public static Supplier<DynamicArisianBloomBranch> ARISIAN_BLOOM_BRANCH = RegistryHandler.addBlock(maxhyper.dtbyg.DynamicTreesRU.location("arisian_bloom_branch"), ()->new DynamicArisianBloomBranch(BYGBlocks.ARISIAN_BLOOM_BRANCH.get()));
//    public static Supplier<DynamicArisianBloomBranch> EMBUR_GEL_BRANCH = RegistryHandler.addBlock(maxhyper.dtbyg.DynamicTreesRU.location("embur_gel_branch"), ()->new DynamicArisianBloomBranch(BYGBlocks.EMBUR_GEL_BRANCH.get()));
//    public static Supplier<DynamicArisianBloomBranch> WITCH_HAZEL_BRANCH = RegistryHandler.addBlock(maxhyper.dtbyg.DynamicTreesRU.location("witch_hazel_side_branch"), ()->new DynamicArisianBloomBranch(BYGBlocks.WITCH_HAZEL_BRANCH.get()));
//    public static Supplier<DynamicArisianBloomBranch> IMPARIUS_MUSHROOM_BRANCH = RegistryHandler.addBlock(maxhyper.dtbyg.DynamicTreesRU.location("imparius_mushroom_side_branch"), ()->new DynamicArisianBloomBranch(BYGBlocks.IMPARIUS_MUSHROOM_BRANCH.get()));
//    public static Supplier<FungalImpariusFilamentBlock> FUNGAL_IMPARIUS_FILAMENT = RegistryHandler.addBlock(maxhyper.dtbyg.DynamicTreesRU.location("fungal_imparius_filament"), () -> new FungalImpariusFilamentBlock(
//            BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_LIGHT_BLUE).instabreak().sound(SoundType.HONEY_BLOCK).noOcclusion().noCollission().lightLevel((state) -> 15)
//    ){
//        @Override public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
//            return worldIn.getBlockState(pos.above()).getBlock() instanceof DynamicCapBlock;
//        }
//        @Override public Item asItem() {
//            return BYGBlocks.FUNGAL_IMPARIUS_FILAMENT.asItem();
//        }
//    });
//
    public static void setup() {
//        BYGConstants.ENABLE_CACTI = false;
//
//        CommonVoxelShapes.SHAPES.put(maxhyper.dtbyg.DynamicTreesRU.location("tall_flat_mushroom").toString(), TALL_FLAT_MUSHROOM);
//        CommonVoxelShapes.SHAPES.put(maxhyper.dtbyg.DynamicTreesRU.location("small_flat_mushroom").toString(), SMALL_FLAT_MUSHROOM);
//        CommonVoxelShapes.SHAPES.put(maxhyper.dtbyg.DynamicTreesRU.location("short_round_mushroom").toString(), SHORT_ROUND_MUSHROOM);
//        CommonVoxelShapes.SHAPES.put(maxhyper.dtbyg.DynamicTreesRU.location("soul_shroom").toString(), SOUL_SHROOM);
//        CommonVoxelShapes.SHAPES.put(maxhyper.dtbyg.DynamicTreesRU.location("sythian_mushroom").toString(), SYTHIAN_MUSHROOM);
//        CommonVoxelShapes.SHAPES.put(maxhyper.dtbyg.DynamicTreesRU.location("shulkren_mushroom").toString(), SHULKREN_MUSHROOM);
//
    }
//
    public static void setupBlocks() {
//        setUpSoils();
//        setupConnectables();
//        if (DTConfigs.REPLACE_NYLIUM_FUNGI.get()) {
//            VegetationReplacement.replaceNyliumFungiFeatures();
//        }
    }
//
//    private static void setUpSoils() {
//        SoilProperties netherrackProperties = SoilHelper.getProperties(Blocks.NETHERRACK);
//        if (netherrackProperties instanceof SpreadableSoilProperties)
//            ((SpreadableSoilProperties) netherrackProperties).addSpreadableSoils(
//                    BYGBlocks.SYTHIAN_NYLIUM.get(), BYGBlocks.OVERGROWN_NETHERRACK.get(), BYGBlocks.MYCELIUM_NETHERRACK.get());
//    }
//
//    private static void setupConnectables() {
//        BranchConnectables.makeBlockConnectable(BYGBlocks.POLLEN_BLOCK.get(), (state, world, pos, side) -> {
//            if (side == Direction.DOWN) return 1;
//            return 0;
//        });
//
//        BranchConnectables.makeBlockConnectable(BYGBlocks.PURPLE_SHROOMLIGHT.get(), (state, world, pos, side) -> {
//            if (side == Direction.DOWN) {
//                BlockState branchState = world.getBlockState(pos.relative(Direction.UP));
//                BranchBlock branch = TreeHelper.getBranch(branchState);
//                if (branch != null)
//                    return Math.min(Math.max(branch.getRadius(branchState) - 1, 1), 8);
//                else return 8;
//            }
//            return 0;
//        });
//
//        for (Block block : new Block[]{ARISIAN_BLOOM_BRANCH.get(), EMBUR_GEL_BRANCH.get(), WITCH_HAZEL_BRANCH.get(), IMPARIUS_MUSHROOM_BRANCH.get()}){
//            BranchConnectables.makeBlockConnectable(block, (state, world, pos, side) -> {
//                if (state.hasProperty(HorizontalDirectionalBlock.FACING)) {
//                    return state.getValue(HorizontalDirectionalBlock.FACING) == side ? 1 : 0;
//                }
//                return 0;
//            });
//        }
//    }
//
//    @SubscribeEvent
//    public static void onGenFeatureRegistry (final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GenFeature> event) {
//        DTBYGGenFeatures.register(event.getRegistry());
//    }
//
//    @SubscribeEvent
//    public static void onCellKitRegistry (final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<CellKit> event) {
//        DTBYGCellKits.register(event.getRegistry());
//    }
//
//    @SubscribeEvent
//    public static void onGrowthLogicKitRegistry (final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GrowthLogicKit> event) {
//        DTBYGGrowthLogicKits.register(event.getRegistry());
//    }
//
//
//
//    @SubscribeEvent
//    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("poplar"), PoplarSpecies.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("twiglet"), TwigletSpecies.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("generates_underwater"), GenUnderwaterSpecies.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("generates_on_extra_soil"), GenOnExtraSoilSpecies.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("mangrove"), MangroveSpecies.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("lament"), LamentSpecies.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("woody_mushroom"), WoodyHugeMushroomSpecies.TYPE);
//    }
//
//    @SubscribeEvent
//    public static void registerFamilyTypes (final TypeRegistryEvent<Family> event) {
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("imbued_log"), ImbuedLogFamily.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("diagonal_palm"), DiagonalPalmFamily.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("sythian_fungus"), SythianFungusFamily.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("warty_mushroom"), WartyMushroomFamily.TYPE);
//    }
//
//    @SubscribeEvent
//    public static void registerSoilPropertiesTypes (final TypeRegistryEvent<SoilProperties> event) {
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location( "lava"), LavaSoilProperties.TYPE);
//    }
//
//    @SubscribeEvent
//    public static void registerFruitTypes(final TypeRegistryEvent<Fruit> event) {
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("ether_bulbs"), EtherBulbsFruit.TYPE);
//    }
//
//    @SubscribeEvent
//    public static void registerCapPropertiesTypes (final TypeRegistryEvent<CapProperties> event){
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("embur_gel_cap"), EmburGelCapProperties.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("fungal_imparius_cap"), FungalImpariusCapProperties.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("imparius_mushroom_cap"), ImpariusMushroomCapProperties.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("warty_cap"), WartyCapProperties.TYPE);
//        event.registerType(maxhyper.dtbyg.DynamicTreesRU.location("weeping_milkcap_cap"), WeepingMilkcapCapProperties.TYPE);
//    }
//
//    @SubscribeEvent
//    public static void onMushroomShapeKitRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<MushroomShapeKit> event) {
//        BYGMushroomShapeKits.register(event.getRegistry());
//    }
//
//    public static final FeatureCanceller BYG_TREE_CANCELLER = new BYGTreeFeatureCanceller<>(maxhyper.dtbyg.DynamicTreesRU.location("tree"), TreeFromStructureNBTConfig.class);
//    public static final FeatureCanceller BYG_FUNGUS_CANCELLER = new TreeFeatureCanceller<>(maxhyper.dtbyg.DynamicTreesRU.location("fungus"), BYGMushroomConfig.class);
//    public static final FeatureCanceller GIANT_FLOWER_CANCELLER = new TreeFeatureCanceller<>(maxhyper.dtbyg.DynamicTreesRU.location("giant_flower"), GiantFlowerConfig.class);
//
//    @SubscribeEvent
//    public static void onFeatureCancellerRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<FeatureCanceller> event) {
//        event.getRegistry().registerAll(BYG_TREE_CANCELLER);
//        event.getRegistry().registerAll(BYG_FUNGUS_CANCELLER);
//        event.getRegistry().registerAll(GIANT_FLOWER_CANCELLER);
//    }

//    public static void onBiomeLoading(final BiomeLoadingEvent event){
//        VegetationReplacement.OnBiomeLoadingEvent(event);
//    }

}
