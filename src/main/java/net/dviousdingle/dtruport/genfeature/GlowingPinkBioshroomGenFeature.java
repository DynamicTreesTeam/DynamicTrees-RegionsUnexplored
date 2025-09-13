package net.dviousdingle.dtruport.genfeature;

//import com.ferreusveritas.dynamictrees.api.TreeHelper;
//import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
//import com.ferreusveritas.dynamictrees.api.network.MapSignal;
//import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
//import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
//import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGenerationContext;
//import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGrowContext;
//import com.ferreusveritas.dynamictrees.systems.nodemapper.FindEndsNode;
//import com.ferreusveritas.dynamictrees.tree.species.Species;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapBlock;
//import com.ferreusveritas.dynamictreesplus.tree.HugeMushroomSpecies;
import com.dtteam.dynamictrees.api.configuration.ConfigurationProperty;
import com.dtteam.dynamictrees.api.network.MapSignal;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGrowContext;
import com.dtteam.dynamictrees.systems.nodemapper.FindEndsNode;
import com.dtteam.dynamictrees.tree.TreeHelper;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictreesplus.block.mushroom.DynamicCapBlock;
import com.dtteam.dynamictreesplus.tree.HugeMushroomSpecies;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class GlowingPinkBioshroomGenFeature extends GenFeature {

	public static final ConfigurationProperty<Block> BLOCK = ConfigurationProperty.block("block");
	public static final ConfigurationProperty<Boolean> CUTOUT = ConfigurationProperty.bool("cutout");

	public GlowingPinkBioshroomGenFeature(ResourceLocation registryName) {
		super(registryName);
	}

	@Override
	protected void registerProperties() {
		this.register(BLOCK, CAN_GROW_PREDICATE, CUTOUT);
	}

	@Override
	public boolean shouldApply(Species species, GenFeatureConfiguration configuration) {
		if (configuration.get(BLOCK) == Blocks.AIR) return false;
		if (!(species instanceof HugeMushroomSpecies)) return false;
		return super.shouldApply(species, configuration);
	}

	@Override
	public GenFeatureConfiguration createDefaultConfiguration() {
		return super.createDefaultConfiguration()
				.with(BLOCK, Blocks.AIR)
				.with(CUTOUT, true)
				.with(CAN_GROW_PREDICATE, (level, blockPos) -> level.getRandom().nextFloat() <= 0.05F);
	}

	@Override
	protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
 		BlockPos.MutableBlockPos testPos = context.pos().above().mutable();
		while (TreeHelper.isBranch(context.level().getBlockState(testPos))){
			testPos.move(Direction.UP);
		}
		testPos.move(Direction.DOWN);
		boolean placed = false;
		for (Direction dir : Direction.Plane.HORIZONTAL){
			if (this.placeGlowingBlocksInValidPlace(configuration, context.level(), testPos, dir)){
				placed = true;
			}
		}
		return placed;
	}

	protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {
		FindEndsNode endFinder = new FindEndsNode();
		TreeHelper.startAnalysisFromRoot(context.level(), context.pos(), new MapSignal(endFinder));
		List<BlockPos> endPoints = endFinder.getEnds();
		if (endPoints.isEmpty()) return false;
		BlockPos pos = endPoints.get(0);
		Direction randomDir = Direction.Plane.HORIZONTAL.getRandomDirection(context.level().getRandom());
		return context.natural()
				&& configuration.get(CAN_GROW_PREDICATE).test(context.level(), context.pos().above())
				&& context.fertility() == 0
				&& this.placeGlowingBlocksInValidPlace(configuration, context.level(), pos, randomDir);
	}

	private boolean placeGlowingBlocksInValidPlace(GenFeatureConfiguration configuration, LevelAccessor level, BlockPos end, Direction dir) {
		Block glowingBlock = configuration.get(BLOCK);
		BlockPos.MutableBlockPos testPos = end.offset(dir.getNormal()).mutable();
		int layers = 0;
		while (level.getBlockState(testPos).getBlock() instanceof DynamicCapBlock){
			BlockState right = level.getBlockState(testPos.offset(dir.getClockWise().getNormal()));
			BlockState left = level.getBlockState(testPos.offset(dir.getCounterClockWise().getNormal()));
			if (left.getBlock() instanceof DynamicCapBlock && right.getBlock() instanceof DynamicCapBlock){
				layers++;
			}
			testPos.move(Direction.DOWN);
		}
		BlockState edgeState = level.getBlockState(testPos);
		if (edgeState.getBlock() == glowingBlock){
			return false;
		}
		if (configuration.get(CUTOUT)){
			if (layers >= 2 && edgeState.canBeReplaced()){
				level.removeBlock(testPos.above(), false);
				level.setBlock(testPos.above().above(), glowingBlock.defaultBlockState(), 3);
			}
		} else {
			if (layers >= 1 && edgeState.canBeReplaced()){
				level.setBlock(testPos.above(), glowingBlock.defaultBlockState(), 3);
			}
		}
		return true;
	}

}
