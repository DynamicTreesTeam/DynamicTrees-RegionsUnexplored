package dtteam.dtru.genfeature;

import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.systems.genfeature.VinesGenFeature;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.utility.CoordUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.block.type.leaves.HangingVinesBlock;

public class WisteriaVinesGenFeature extends VinesGenFeature {

	public WisteriaVinesGenFeature(ResourceLocation registryName) {
		super(registryName);
	}

	protected void addVerticalVines(GenFeatureConfiguration configuration, LevelAccessor level, Species species, BlockPos rootPos, BlockPos branchPos, boolean worldgen) {
		if (!configuration.get(BLOCK).defaultBlockState().hasProperty(HangingVinesBlock.TIP)) {
			super.addVerticalVines(configuration, level, species, rootPos, branchPos, worldgen);
			return;
		}

		BlockPos vinePos = CoordUtils.getRayTraceFruitPos(level, species, rootPos, branchPos, worldgen);

		if (configuration.get(VINE_TYPE) == VineType.FLOOR)
			vinePos = this.findGround(level, vinePos);

		if (vinePos == BlockPos.ZERO) return;

		BlockState vineState = configuration.get(BLOCK).defaultBlockState().setValue(HangingVinesBlock.TIP, false);
		BlockState tipState = vineState.setValue(HangingVinesBlock.TIP, true);

		this.placeVines(level, vinePos, vineState, configuration.get(MAX_LENGTH),
				tipState, configuration.get(VINE_TYPE), worldgen);
	}

	private BlockPos findGround(LevelAccessor level, BlockPos vinePos) {
		BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos(vinePos.getX(), vinePos.getY(), vinePos.getZ());
		do {
			mPos.move(Direction.DOWN);
			if (mPos.getY() <= 0) return BlockPos.ZERO;
		} while (level.isEmptyBlock(vinePos) || level.getBlockState(vinePos).getBlock() instanceof DynamicLeavesBlock);

		return mPos.above();
	}

}
