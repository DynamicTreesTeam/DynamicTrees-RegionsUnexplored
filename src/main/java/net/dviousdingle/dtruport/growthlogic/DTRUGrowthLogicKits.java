package net.dviousdingle.dtruport.growthlogic;

//import com.ferreusveritas.dynamictrees.api.registry.Registry;
//import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import net.dviousdingle.dtruport.DtruPort;
import net.minecraft.resources.ResourceLocation;
//import dtteam.dtru.DtruPort;

public class DTRUGrowthLogicKits {
    public static final GrowthLogicKit THIN_CONIFER = new PineLogic(DtruPort.location( "thin_conifer"));
    public static final GrowthLogicKit MEGA_PINE = new MegaPineLogic(DtruPort.location( "mega_pine"));
    public static final GrowthLogicKit REDWOOD = new RedwoodLogic(DtruPort.location( "redwood"));
    public static final GrowthLogicKit SMALL_REDWOOD = new SmallRedwoodLogic(DtruPort.location( "small_redwood"));
    public static final GrowthLogicKit BAOBAB = new BaobabLogic(DtruPort.location( "baobab"));
    public static final GrowthLogicKit PALM = new DiagonalPalmLogic(DtruPort.location( "diagonal_palm"));
    public static final GrowthLogicKit VARIATE_HEIGHT = new VariateHeightLogic(DtruPort.location( "variate_height"));
    public static final GrowthLogicKit CYPRESS = new CypressLogic(DtruPort.location( "cypress"));
    public static final GrowthLogicKit MEGA_EUCALYPTUS = new MegaEucalyptusLogic(DtruPort.location( "mega_eucalyptus"));
    public static final GrowthLogicKit WILLOW = new WillowLogic(DtruPort.location( "willow"));
    public static final GrowthLogicKit BAMBOO = new BambooLogic(DtruPort.location( "bamboo"));
    public static final GrowthLogicKit TWISTING_TREE = new TwistingTreeLogic(DtruPort.location( "twisting_tree"));
    public static final GrowthLogicKit CANOPY = new CanopyLogic(DtruPort.location( "canopy"));
    public static final GrowthLogicKit POPLAR = new PoplarLogic(DtruPort.location( "poplar"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(THIN_CONIFER, MEGA_PINE, REDWOOD, SMALL_REDWOOD,
                BAOBAB, VARIATE_HEIGHT, CYPRESS, MEGA_EUCALYPTUS, WILLOW, PALM,
                TWISTING_TREE, BAMBOO, CANOPY, POPLAR);
    }

}
