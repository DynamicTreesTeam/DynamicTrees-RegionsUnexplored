package net.dviousdingle.dtruport.genfeature;

//import com.ferreusveritas.dynamictrees.api.TreeHelper;
//import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
//import com.ferreusveritas.dynamictrees.api.network.MapSignal;
//import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
//import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
//import com.ferreusveritas.dynamictrees.systems.genfeature.context.FullGenerationContext;
//import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGenerationContext;
//import com.ferreusveritas.dynamictrees.systems.nodemapper.FindEndsNode;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
//import com.ferreusveritas.dynamictreesplus.systems.mushroomlogic.context.MushroomCapContext;
//import com.ferreusveritas.dynamictreesplus.tree.HugeMushroomSpecies;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.systems.genfeature.context.FullGenerationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluids;

public class PreventWaterGenerationGenFeature extends GenFeature {

	public PreventWaterGenerationGenFeature(ResourceLocation registryName) {
		super(registryName);
	}

	@Override
	protected void registerProperties() {}

	@Override
	public GenFeatureConfiguration createDefaultConfiguration() {return super.createDefaultConfiguration();}

	@Override
	protected boolean generate(GenFeatureConfiguration configuration, FullGenerationContext context) {
		if (context.level().getFluidState(context.pos().above()).is(Fluids.WATER)){
			return true;
		}
		return super.generate(configuration, context);
	}
}
