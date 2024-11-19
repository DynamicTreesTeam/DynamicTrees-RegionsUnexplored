package dtteam.dtru.genfeature;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.init.DTTrees;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.FullGenerationContext;
import net.minecraft.resources.ResourceLocation;

public class GenerateBiomePredicateGenFeature extends GenFeature {

    public static final ConfigurationProperty<GenFeatureConfiguration> GEN_FEATURE = ConfigurationProperty.property("gen_feature", GenFeatureConfiguration.class);

    public GenerateBiomePredicateGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(BIOME_PREDICATE, GEN_FEATURE);
    }

    @Override
    protected GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(BIOME_PREDICATE, i -> true)
                .with(GEN_FEATURE, GenFeatureConfiguration.getNull());
    }

    @Override
    protected boolean generate(GenFeatureConfiguration configuration, FullGenerationContext context) {
        final GenFeatureConfiguration configurationToPlace = configuration.get(GEN_FEATURE);

        if (configuration.getGenFeature().getRegistryName().equals(DTTrees.NULL)) { // If the gen feature was null, do nothing.
            return false;
        }

        if (configuration.get(BIOME_PREDICATE).test(context.biome())) {
            return configurationToPlace.generate(Type.FULL, context);
        }

        return false;
    }

}
