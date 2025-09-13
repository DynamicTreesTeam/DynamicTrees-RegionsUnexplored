package dtteam.dtru.genfeature;

//import com.ferreusveritas.dynamictrees.api.TreeHelper;
//import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
//import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
//import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
//import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGenerationContext;
//import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGrowContext;
//import com.ferreusveritas.dynamictrees.tree.species.Species;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
//import com.ferreusveritas.dynamictreesplus.tree.HugeMushroomSpecies;
import com.dtteam.dynamictrees.api.configuration.ConfigurationProperty;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGrowContext;
import com.dtteam.dynamictrees.tree.TreeHelper;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
import com.dtteam.dynamictreesplus.tree.HugeMushroomSpecies;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;


public class TrunkBioshroomGenFeature extends GenFeature {

	public static final ConfigurationProperty<Block> BLOCK = ConfigurationProperty.block("block");
	public static final ConfigurationProperty<Integer> LOWEST_BLOCK_POS = ConfigurationProperty.integer("lowest_block_pos");

	public TrunkBioshroomGenFeature(ResourceLocation registryName) {
		super(registryName);
	}

	@Override
	protected void registerProperties() {
		this.register(BLOCK, LOWEST_BLOCK_POS, PLACE_CHANCE);
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
				.with(LOWEST_BLOCK_POS, 3)
				.with(PLACE_CHANCE, 0.05F);
	}

	@Override
	protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
		LevelAccessor level = context.level();
		BlockPos.MutableBlockPos testPos = context.pos().above(configuration.get(LOWEST_BLOCK_POS)).mutable();
		List<BlockPos> validPositions = FindValidPositions(level, testPos);
		if (!(context.level().getBlockState(testPos).getBlock() instanceof DynamicCapCenterBlock))
			return false;
		boolean placed = false;
		for (BlockPos pos : validPositions){
			if (level.getRandom().nextFloat() <= configuration.get(PLACE_CHANCE) && IsFreeAboveAndBelow(level, pos)){
				level.setBlock(pos, configuration.get(BLOCK).defaultBlockState(), 2);
				placed = true;
			}
		}

		return placed;
	}

	@Override
	protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {
		if (context.fertility() == 0) return false;
		LevelAccessor level = context.level();
		BlockPos.MutableBlockPos testPos = context.pos().above(configuration.get(LOWEST_BLOCK_POS)).mutable();
		List<BlockPos> validPositions = FindValidPositions(level, testPos);

		boolean placed = false;
		if (level.getRandom().nextFloat() <= configuration.get(PLACE_CHANCE) && !validPositions.isEmpty()){
			BlockPos pos = validPositions.get(context.random().nextInt(validPositions.size()));
			if (IsFreeAboveAndBelow(level, pos)){
				level.setBlock(pos, configuration.get(BLOCK).defaultBlockState(), 0);
			}
			placed = true;
		}

		return placed;
	}

	protected List<BlockPos> FindValidPositions (LevelAccessor level, BlockPos.MutableBlockPos testPos){
		List<BlockPos> validPositions = new ArrayList<>();
		while (TreeHelper.isBranch(level.getBlockState(testPos))){
			testPos.move(Direction.UP);
			for (Direction dir : Direction.Plane.HORIZONTAL){
				BlockPos side = testPos.offset(dir.getNormal());
				validPositions.add(side);
			}
		}
		return validPositions;
	}

	private boolean IsFreeAboveAndBelow(LevelAccessor level, BlockPos pos){
		return level.getBlockState(pos).canBeReplaced()
				&& level.getBlockState(pos.above()).canBeReplaced()
				&& level.getBlockState(pos.below()).canBeReplaced();
	}

}
