package dtteam.dtru.growthlogic;

import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKitConfiguration;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionManipulationContext;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionSelectionContext;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class BambooLogic extends VariateHeightLogic{

    public BambooLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public Direction selectNewDirection(GrowthLogicKitConfiguration configuration, DirectionSelectionContext context) {
        Direction dir = super.selectNewDirection(configuration, context);
        if (context.signal().isInTrunk() && dir != Direction.UP) { // Turned out of trunk
            context.signal().energy = 0.2f;
        }
        return dir;
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        Direction originDir = context.signal().dir.getOpposite();
        int[] probMap = super.populateDirectionProbabilityMap(configuration, context);

        // Alter probability map for direction change
        probMap[0] = 0; // Down is always disallowed for bamboo
        probMap[1] = 10;
        probMap[2] = probMap[3] = probMap[4] = probMap[5] = context.signal().energy <= 1 ? 9 : 0;
        probMap[originDir.ordinal()] = 0; // Disable the direction we came from

        return probMap;
    }

}
