package dtteam.dtru.block;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.block.branch.BasicBranchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;

public class BambooBranchBlock extends BasicBranchBlock {

    public static final BooleanProperty LEAVES =  BooleanProperty.create("leaves");

    public BambooBranchBlock(ResourceLocation name, Properties properties) {
        super(name, properties);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LEAVES);
    }

    @Override
    public int setRadius(LevelAccessor level, BlockPos pos, int radius, @Nullable Direction originDir, int flags) {
        destroyMode = DynamicTrees.DestroyMode.SET_RADIUS;
        BlockState oldState = level.getBlockState(pos);
        boolean replacingWater = oldState.getFluidState() == Fluids.WATER.getSource(false);
        boolean setWaterlogged = replacingWater && radius <= 7;
        boolean leaves = oldState.hasProperty(LEAVES) ? oldState.getValue(LEAVES) : level.getRandom().nextBoolean();
        level.setBlock(pos, getStateForRadius(radius).setValue(WATERLOGGED, setWaterlogged).setValue(LEAVES, leaves), flags);
        destroyMode = DynamicTrees.DestroyMode.SLOPPY;
        return radius;
    }
}
