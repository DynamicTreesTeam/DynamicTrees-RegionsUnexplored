package dtteam.dtru.genfeature;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.worldgen.GenerationContext;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

/**
 * @author Max Hyper
 */
public class ReplaceOnRadiusGenFeature extends GenFeature {

    public static final ConfigurationProperty<Species> SPECIES = ConfigurationProperty.property("species", Species.class);
    public static final ConfigurationProperty<Integer> MIN_RADIUS = ConfigurationProperty.integer("min_radius");

    public ReplaceOnRadiusGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(SPECIES, MIN_RADIUS);
    }

    @Override
    public GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(SPECIES, Species.NULL_SPECIES)
                .with(MIN_RADIUS, 5);
    }

    @Override
    public boolean shouldApply(Species species, GenFeatureConfiguration configuration) {
        return configuration.get(SPECIES).isValid();
    }

    @Override
    protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
        if (context.radius() > configuration.get(MIN_RADIUS)){
            Species species = configuration.get(SPECIES);
            species.generate(new GenerationContext(context.levelContext(), species, context.originPos(), context.pos().mutable(), context.biome(), Direction.Plane.HORIZONTAL.getRandomDirection(context.random()), context.radius(), context.bounds()));
            return false;
        }
        return true;
    }


}
