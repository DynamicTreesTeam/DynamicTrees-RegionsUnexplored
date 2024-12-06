package dtteam.dtru.block;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
import com.ferreusveritas.dynamictrees.block.branch.ThickBranchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class TransitionLogBranchBlock extends ThickBranchBlock {

    public static final BooleanProperty TRANSITION =  BooleanProperty.create("transition");

    boolean transitionOnStripped;

    public TransitionLogBranchBlock(ResourceLocation name, Properties properties, boolean stripped) {
        super(name, properties);
        this.transitionOnStripped = stripped;
    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TRANSITION);
    }

    @Override
    public int setRadius(LevelAccessor level, BlockPos pos, int radius, @Nullable Direction originDir, int flags) {
        int rad = super.setRadius(level, pos, radius, originDir, flags);
        if (isStrippedBranch()) return rad;
        boolean doTransition = false;
        if (transitionOnStripped){
            BranchBlock downBranch = TreeHelper.getBranch(level.getBlockState(pos.below()));
            if (downBranch != null && downBranch.isStrippedBranch()){
                doTransition = true;
            }
        } else {
            if (TreeHelper.isRooty(level.getBlockState(pos.below()))){
                doTransition = true;
            }
        }
        if (doTransition){
            level.setBlock(pos, level.getBlockState(pos).setValue(TRANSITION, true), flags);
        }
        return rad;
    }

    @Override
    public void stripBranch(BlockState state, LevelAccessor level, BlockPos pos, int radius) {
        super.stripBranch(state, level, pos, radius);
        if (transitionOnStripped){
            BranchBlock upBranch = TreeHelper.getBranch(level.getBlockState(pos.above()));
            if (upBranch != null && !upBranch.isStrippedBranch()){
                level.setBlock(pos.above(), level.getBlockState(pos.above()).setValue(TRANSITION, true), 3);
            }
        }
    }

    @Override
    public BlockState getStateForRadius(int radius) {
        return super.getStateForRadius(radius).setValue(TRANSITION, false);
    }
}
