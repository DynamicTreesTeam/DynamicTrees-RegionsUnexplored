package dtteam.dtru.genfeature;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import dtteam.dtru.block.TransitionLogBranchBlock;
import dtteam.dtru.tree.BrimwoodFamily;
import dtteam.dtru.tree.TransitionLogFamily;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class MagmaBottomGenFeature extends GenFeature {

	public static final ConfigurationProperty<Integer> MIN_HEIGHT = ConfigurationProperty.integer("min_height");

	public static final ConfigurationProperty<Integer> MAX_HEIGHT = ConfigurationProperty.integer("max_height");

	public MagmaBottomGenFeature(ResourceLocation registryName) {
		super(registryName);
	}

	@Override
	protected void registerProperties() {
		this.register(MIN_HEIGHT, MAX_HEIGHT);
	}

	@Override
	public GenFeatureConfiguration createDefaultConfiguration() {
		return super.createDefaultConfiguration()
				.with(MIN_HEIGHT, 3)
				.with(MAX_HEIGHT, 5);
	}

	@Override
	public boolean shouldApply(Species species, GenFeatureConfiguration configuration) {
		return species.getFamily() instanceof BrimwoodFamily;
	}

	@Override
	protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
		int height = context.random().nextInt(configuration.get(MIN_HEIGHT), configuration.get(MAX_HEIGHT)+1);
		LevelAccessor level = context.level();
		for (int i=1; i<height; i++){
			BlockPos trunkPos = context.pos().above(i);
			int rad = TreeHelper.getRadius(level, trunkPos);
			if (rad != 0){
				((BrimwoodFamily)context.species().getFamily()).getMagmaBranch().ifPresent(sb -> sb.setRadius(level, trunkPos, rad, Direction.UP));
			}
		}
		if (context.species().getFamily() instanceof TransitionLogFamily){
			BlockPos trunkPos = context.pos().above(height);
			BlockState trunkState = level.getBlockState(trunkPos);
			if (trunkState.hasProperty(TransitionLogBranchBlock.TRANSITION)){
				level.setBlock(trunkPos, trunkState.setValue(TransitionLogBranchBlock.TRANSITION, true), 2);
			}
		}
		return true;
	}

}
