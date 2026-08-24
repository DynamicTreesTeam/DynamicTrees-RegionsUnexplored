package dtteam.dtru.genfeature;

//import com.ferreusveritas.dynamictrees.api.registry.Registry;
//import com.ferreusveritas.dynamictrees.systems.genfeature.BiomePredicateGenFeature;
//import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
//import dtteam.dtru.DtruPort;
import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.genfeature.BiomePredicateGenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import dtteam.dtru.DynamicTreesRU;

public class DTRUGenFeatures {

    public static final GenFeature BIG_BOTTOM_FLARE = new BigBottomFlareGenFeature(DynamicTreesRU.location( "big_bottom_flare"));
    public static final GenFeature EXTRA_BOTTOM_FLARE = new ExtraBottomFlareGenFeature(DynamicTreesRU.location( "extra_bottom_flare"));
    public static final GenFeature REPLACE_ON_RADIUS = new ReplaceOnRadiusGenFeature(DynamicTreesRU.location( "replace_on_radius"));
    public static final GenFeature GEN_BIOME_PREDICATE = new GenerateBiomePredicateGenFeature(DynamicTreesRU.location( "biome_predicate_2"));
    public static final GenFeature BIOME_PREDICATE_ALT = new BiomePredicateGenFeature(DynamicTreesRU.location( "biome_predicate"));
    public static final GenFeature SOIL_WHITELIST = new SoilWhitelistGenFeature(DynamicTreesRU.location( "soil_whitelist"));
    public static final GenFeature ALTERNATIVE_BRANCH = new AlternativeBranchGenFeature(DynamicTreesRU.location( "alt_branch"));
    public static final GenFeature MUSHROOM_VINES = new MushroomVinesGenFeature(DynamicTreesRU.location( "mushroom_vines"));
    public static final GenFeature PREVENT_WATER_GEN = new PreventWaterGenerationGenFeature(DynamicTreesRU.location( "prevent_water_generation"));
    public static final GenFeature STRIPPED_BOTTOM = new StrippedBottomGenFeature(DynamicTreesRU.location( "stripped_bottom"));
    public static final GenFeature MAGMA_BOTTOM = new MagmaBottomGenFeature(DynamicTreesRU.location( "magma_bottom"));
    public static final GenFeature WISTERIA_VINES = new WisteriaVinesGenFeature(DynamicTreesRU.location( "wisteria_vines"));

//    @SubscribeEvent
    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(BIG_BOTTOM_FLARE, EXTRA_BOTTOM_FLARE,
                REPLACE_ON_RADIUS, GEN_BIOME_PREDICATE, SOIL_WHITELIST,
                ALTERNATIVE_BRANCH, MUSHROOM_VINES, PREVENT_WATER_GEN,
                STRIPPED_BOTTOM, BIOME_PREDICATE_ALT, MAGMA_BOTTOM,
                WISTERIA_VINES);
        if (DynamicTreesRU.isDynamicTreesPlusLoaded()){
            DTRUPlusGenFeatures.register(registry);
        }
    }

}
