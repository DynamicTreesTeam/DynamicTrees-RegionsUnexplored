package dtteam.dtru.genfeature;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.deserialisation.ListDeserialiser;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.FullGenerationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.LinkedList;
import java.util.List;

public class DontGenerateOnSoilGenFeature extends GenFeature {

    public static final ConfigurationProperty<List<Block>> ALLOWED_BLOCKS = ConfigurationProperty.property("forbidden_block", ListDeserialiser.getListClass(Block.class));
    public DontGenerateOnSoilGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(ALLOWED_BLOCKS);
    }

    @Override
    protected GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(ALLOWED_BLOCKS, new LinkedList<>());
    }

    @Override
    protected boolean generate(GenFeatureConfiguration configuration, FullGenerationContext context) {
        for (Block block : configuration.get(ALLOWED_BLOCKS)){
            if (context.level().getBlockState(context.pos()).is(block))
                return false;
        }

        return super.generate(configuration, context);
    }

}