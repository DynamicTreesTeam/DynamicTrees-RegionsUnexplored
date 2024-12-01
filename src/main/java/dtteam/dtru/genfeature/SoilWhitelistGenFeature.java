package dtteam.dtru.genfeature;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.block.rooty.SoilHelper;
import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.FullGenerationContext;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictrees.worldgen.GenerationContext;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class SoilWhitelistGenFeature extends GenFeature {

    public static final ConfigurationProperty<Species> REPLACEMENT_SPECIES = ConfigurationProperty.property("replacement_species", Species.class);
    public static final ConfigurationProperty<Float> REPLACEMENT_CHANCE = ConfigurationProperty.floatProperty("replacement_chance");
    public static final ConfigurationProperty<Boolean> BLACKLIST = ConfigurationProperty.bool("use_blacklist");
    public static final ConfigurationProperty<String> ALLOWED_SOIL = ConfigurationProperty.string("allowed_soil");
    public SoilWhitelistGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(ALLOWED_SOIL, BLACKLIST, REPLACEMENT_CHANCE, REPLACEMENT_SPECIES);
    }

    @Override
    protected GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(BLACKLIST, false)
                .with(ALLOWED_SOIL, "")
                .with(REPLACEMENT_CHANCE, 0.4f)
                .with(REPLACEMENT_SPECIES, Species.NULL_SPECIES);
    }

    @Override
    protected boolean generate(GenFeatureConfiguration configuration, FullGenerationContext context) {
        if (configuration.get(BLACKLIST) ^ SoilHelper.isSoilAcceptable(context.level().getBlockState(context.pos()), SoilHelper.getSoilFlags(configuration.get(ALLOWED_SOIL))))
            return super.generate(configuration, context);
        if (context.random().nextFloat() < configuration.get(REPLACEMENT_CHANCE)){
            Species species = configuration.get(REPLACEMENT_SPECIES);
            if (species.isValid())
                species.generate(new GenerationContext(context.levelContext(), species, context.pos(), context.pos().mutable(), context.biome(), Direction.Plane.HORIZONTAL.getRandomDirection(context.random()), context.radius(), context.bounds()));
        }
        return true;
    }

}