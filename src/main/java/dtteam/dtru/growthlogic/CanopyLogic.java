package dtteam.dtru.growthlogic;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKitConfiguration;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionManipulationContext;
import com.ferreusveritas.dynamictrees.systems.GrowSignal;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class CanopyLogic extends VariateHeightLogic {

    public static final ConfigurationProperty<Boolean> FORCE_UP_AFTER_BRANCHING = ConfigurationProperty.bool("force_up_after_branching");
    public static final ConfigurationProperty<Integer> BRANCHING_HEIGHT = ConfigurationProperty.integer("branching_height");
    public static final ConfigurationProperty<Integer> ZIGZAG_UP_CHANCE = ConfigurationProperty.integer("zigzag_up_chance");

    public CanopyLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(FORCE_UP_AFTER_BRANCHING, true)
                .with(HEIGHT_VARIATION, 4)
                .with(LOWEST_BRANCH_VARIATION, 6)
                .with(BRANCHING_HEIGHT, 3)
                .with(ZIGZAG_UP_CHANCE, 5);
    }

    @Override
    protected void registerProperties() {
        this.register(FORCE_UP_AFTER_BRANCHING, HEIGHT_VARIATION, LOWEST_BRANCH_VARIATION, BRANCHING_HEIGHT, ZIGZAG_UP_CHANCE);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final GrowSignal signal = context.signal();
        final int[] probMap = context.probMap();

        int lowestBranch =  context.species().getLowestBranchHeight() + getHashedVariation(context.level(), signal.rootPos, configuration.get(LOWEST_BRANCH_VARIATION));
        int deltaYFromLowest = signal.delta.getY() - lowestBranch;
        if (deltaYFromLowest < 0) return new int[]{0,1,0,0,0,0};
        int branchingHeight = configuration.get(BRANCHING_HEIGHT);
        // disable down direction
        probMap[0] = 0;
        if (!signal.isInTrunk() && deltaYFromLowest <= branchingHeight) {
            boolean forceUp = configuration.get(ZIGZAG_UP_CHANCE) != 0 && (CoordUtils.coordHashCode(context.pos(), 2) % configuration.get(ZIGZAG_UP_CHANCE)) == 0;
            boolean goUp =  forceUp || (signal.energy % 2 == 0 && configuration.get(FORCE_UP_AFTER_BRANCHING));
            probMap[1] = goUp ? 1 : 0;
            probMap[2] = probMap[3] = probMap[4] = probMap[5] = goUp ? 0 : 1;
        } else {
            // disable up in the trunk if the signal is above the branching height, to force branching
            probMap[1] = 0;
            probMap[2] = probMap[3] = probMap[4] = probMap[5] = 3;
            // If we're not in the trunk, have a small chance of growing up and never grow down
            if (!signal.isInTrunk() && signal.dir != Direction.UP) {
                // Reinforce current growth direction
                // Makes branches more straight to start out with, and then twistier
                int increase = signal.numTurns > 2 ? 0 : 2;
                probMap[signal.dir.ordinal()] += increase;
            }
        }

        // Disable the direction we came from
        Direction originDir = signal.dir.getOpposite();
        probMap[originDir.ordinal()] = 0;

        return probMap;
    }

}