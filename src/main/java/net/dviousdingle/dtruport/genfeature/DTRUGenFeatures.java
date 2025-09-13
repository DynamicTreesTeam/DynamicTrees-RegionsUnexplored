package net.dviousdingle.dtruport.genfeature;

//import com.ferreusveritas.dynamictrees.api.registry.Registry;
//import com.ferreusveritas.dynamictrees.systems.genfeature.BiomePredicateGenFeature;
//import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
//import dtteam.dtru.DtruPort;
import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.genfeature.BiomePredicateGenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import net.dviousdingle.dtruport.DtruPort;
import net.neoforged.bus.api.SubscribeEvent;

public class DTRUGenFeatures {

    public static final GenFeature BIG_BOTTOM_FLARE = new BigBottomFlareGenFeature(DtruPort.location( "big_bottom_flare"));
    public static final GenFeature EXTRA_BOTTOM_FLARE = new ExtraBottomFlareGenFeature(DtruPort.location( "extra_bottom_flare"));
    public static final GenFeature REPLACE_ON_RADIUS = new ReplaceOnRadiusGenFeature(DtruPort.location( "replace_on_radius"));
    public static final GenFeature GEN_BIOME_PREDICATE = new GenerateBiomePredicateGenFeature(DtruPort.location( "biome_predicate_2"));
    public static final GenFeature BIOME_PREDICATE_ALT = new BiomePredicateGenFeature(DtruPort.location( "biome_predicate"));
    public static final GenFeature SOIL_WHITELIST = new SoilWhitelistGenFeature(DtruPort.location( "soil_whitelist"));
    public static final GenFeature ALTERNATIVE_BRANCH = new AlternativeBranchGenFeature(DtruPort.location( "alt_branch"));
    public static final GenFeature MUSHROOM_VINES = new MushroomVinesGenFeature(DtruPort.location( "mushroom_vines"));
    public static final GenFeature PREVENT_WATER_GEN = new PreventWaterGenerationGenFeature(DtruPort.location( "prevent_water_generation"));
    public static final GenFeature STRIPPED_BOTTOM = new StrippedBottomGenFeature(DtruPort.location( "stripped_bottom"));
    public static final GenFeature MAGMA_BOTTOM = new MagmaBottomGenFeature(DtruPort.location( "magma_bottom"));

//    @SubscribeEvent
    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(BIG_BOTTOM_FLARE, EXTRA_BOTTOM_FLARE,
                REPLACE_ON_RADIUS, GEN_BIOME_PREDICATE, SOIL_WHITELIST,
                ALTERNATIVE_BRANCH, MUSHROOM_VINES, PREVENT_WATER_GEN,
                STRIPPED_BOTTOM, BIOME_PREDICATE_ALT, MAGMA_BOTTOM);
        if (DtruPort.isDynamicTreesPlusLoaded()){
            DTRUPlusGenFeatures.register(registry);
        }
    }

}
