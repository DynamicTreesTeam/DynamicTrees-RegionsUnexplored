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
    boolean transitionOnBase;

    public TransitionLogBranchBlock(ResourceLocation name, Properties properties, boolean stripped, boolean base) {
        super(name, properties);
        this.transitionOnStripped = stripped;
        this.transitionOnBase = base;
    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TRANSITION);
    }

    @Override
    public int setRadius(LevelAccessor level, BlockPos pos, int radius, @Nullable Direction originDir, int flags) {
        boolean doTransition = false;
        BlockState branch = level.getBlockState(pos);
        //Preserve transition property if its already there
        if (branch.hasProperty(TRANSITION) && branch.getValue(TRANSITION)){
            doTransition = true;
        }
        //Do the actual radius setting
        int rad = super.setRadius(level, pos, radius, originDir, flags);
        if (isStrippedBranch()) return rad;

        //If it should transition on base check here.
        if (!doTransition && transitionOnBase) {
            if (TreeHelper.isRooty(level.getBlockState(pos.below()))){
                doTransition = true;
            }
        }
        //Set transition block
        if (doTransition){
            level.setBlock(pos, level.getBlockState(pos).setValue(TRANSITION, true), flags);
        }
        return rad;
    }

    @Override
    public void stripBranch(BlockState state, LevelAccessor level, BlockPos pos, int radius) {
        super.stripBranch(state, level, pos, radius);
        //If it should transition when stripped update the upper block
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
