package dtteam.dtru.growthlogic;

import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import dtteam.dtru.DynamicTreesRU;

public class DTRUGrowthLogicKits {
    public static final GrowthLogicKit THIN_CONIFER = new PineLogic(DynamicTreesRU.location( "thin_conifer"));
    public static final GrowthLogicKit MEGA_PINE = new MegaPineLogic(DynamicTreesRU.location( "mega_pine"));
    public static final GrowthLogicKit REDWOOD = new RedwoodLogic(DynamicTreesRU.location( "redwood"));
    public static final GrowthLogicKit SMALL_REDWOOD = new SmallRedwoodLogic(DynamicTreesRU.location( "small_redwood"));
    public static final GrowthLogicKit BAOBAB = new BaobabLogic(DynamicTreesRU.location( "baobab"));
    public static final GrowthLogicKit PALM = new DiagonalPalmLogic(DynamicTreesRU.location( "diagonal_palm"));
    public static final GrowthLogicKit VARIATE_HEIGHT = new VariateHeightLogic(DynamicTreesRU.location( "variate_height"));
    public static final GrowthLogicKit CYPRESS = new CypressLogic(DynamicTreesRU.location( "cypress"));
    public static final GrowthLogicKit MEGA_EUCALYPTUS = new MegaEucalyptusLogic(DynamicTreesRU.location( "mega_eucalyptus"));
    public static final GrowthLogicKit WILLOW = new WillowLogic(DynamicTreesRU.location( "willow"));
    public static final GrowthLogicKit BAMBOO = new BambooLogic(DynamicTreesRU.location( "bamboo"));
    public static final GrowthLogicKit TWISTING_TREE = new TwistingTreeLogic(DynamicTreesRU.location( "twisting_tree"));
    public static final GrowthLogicKit CANOPY = new CanopyLogic(DynamicTreesRU.location( "canopy"));
    public static final GrowthLogicKit POPLAR = new PoplarLogic(DynamicTreesRU.location( "poplar"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(THIN_CONIFER, MEGA_PINE, REDWOOD, SMALL_REDWOOD,
                BAOBAB, VARIATE_HEIGHT, CYPRESS, MEGA_EUCALYPTUS, WILLOW, PALM,
                TWISTING_TREE, BAMBOO, CANOPY, POPLAR);
    }

}
