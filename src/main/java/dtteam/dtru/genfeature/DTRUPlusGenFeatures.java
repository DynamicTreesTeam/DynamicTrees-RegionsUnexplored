package dtteam.dtru.genfeature;

import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import dtteam.dtru.DynamicTreesRU;
import net.minecraft.resources.ResourceLocation;

public class DTRUPlusGenFeatures {

    public static final GenFeature GLOWING_PINK_BIOSHROOM = new GlowingPinkBioshroomGenFeature(new ResourceLocation(DynamicTreesRU.MOD_ID, "glowing_pink_bioshroom"));

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(GLOWING_PINK_BIOSHROOM);
    }

}
