package net.dviousdingle.dtruport.block;

//import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
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
    public BlockBehaviour.Properties getDefaultBlockProperties(MapColor mapColor) {
        return super.getDefaultBlockProperties(mapColor).sound(SoundType.WART_BLOCK);
    }

}