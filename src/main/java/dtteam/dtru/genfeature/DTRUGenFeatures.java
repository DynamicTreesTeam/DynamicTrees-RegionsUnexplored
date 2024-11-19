package dtteam.dtru.genfeature;

import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeature.BiomePredicateGenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import dtteam.dtru.DynamicTreesRU;
import net.minecraft.resources.ResourceLocation;

public class DTRUGenFeatures {

    public static final GenFeature BIG_BOTTOM_FLARE = new BigBottomFlareGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "big_bottom_flare"));
    public static final GenFeature EXTRA_BOTTOM_FLARE = new ExtraBottomFlareGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "extra_bottom_flare"));
    public static final GenFeature REPLACE_ON_RADIUS = new ReplaceOnRadiusGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "replace_on_radius"));
    public static final GenFeature BIOME_PREDICATE_2 = new GenerateBiomePredicateGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "biome_predicate"));
    public static final GenFeature SOIL_WHITELIST = new SoilWhitelistGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "soil_whitelist"));
    public static final GenFeature SYTHIAN_TOPPER = new SythianTopperGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "sythian_topper"));
    public static final GenFeature ALTERNATIVE_BRANCH = new AlternativeBranchGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "alt_branch"));
    public static final GenFeature MUSHROOM_VINES = new MushroomVinesGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "mushroom_vines"));

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(BIG_BOTTOM_FLARE, EXTRA_BOTTOM_FLARE,
                REPLACE_ON_RADIUS,
                BIOME_PREDICATE_2, SOIL_WHITELIST,
                SYTHIAN_TOPPER, ALTERNATIVE_BRANCH, MUSHROOM_VINES);
    }

}
