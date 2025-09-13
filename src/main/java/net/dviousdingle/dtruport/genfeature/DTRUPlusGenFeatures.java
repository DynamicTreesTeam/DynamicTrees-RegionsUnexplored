package net.dviousdingle.dtruport.genfeature;


import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import net.dviousdingle.dtruport.DtruPort;

public class DTRUPlusGenFeatures {

    public static final GenFeature GLOWING_PINK_BIOSHROOM = new GlowingPinkBioshroomGenFeature(DtruPort.location("glowing_pink_bioshroom"));
    public static final GenFeature GLOWING_BIOSHROOM = new GlowingBioshroomGenFeature(DtruPort.location("glowing_bioshroom"));
    public static final GenFeature TRUNK_BIOSHROOM = new TrunkBioshroomGenFeature(DtruPort.location("trunk_bioshroom"));

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(GLOWING_PINK_BIOSHROOM, GLOWING_BIOSHROOM, TRUNK_BIOSHROOM);
    }

}
