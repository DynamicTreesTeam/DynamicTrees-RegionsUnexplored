package dtteam.dtru.block;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BioshroomCapProperties extends CapProperties {
    public static final TypedRegistry.EntryType<CapProperties> TYPE = TypedRegistry.newType(BioshroomCapProperties::new);

    public BioshroomCapProperties(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public BlockBehaviour.Properties getDefaultBlockProperties() {
        return super.getDefaultBlockProperties().sound(SoundType.WART_BLOCK);
    }

}