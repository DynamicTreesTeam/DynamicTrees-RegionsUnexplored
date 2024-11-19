package dtteam.dtru.tree;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.regions_unexplored.config.RuCommonConfig;

import java.awt.*;

public class EucalyptusFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(EucalyptusFamily::new);

    public EucalyptusFamily(ResourceLocation name) {
        super(name);
    }

    @OnlyIn(Dist.CLIENT)
    public int branchColorMultiplier(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        Color rainbow = Color.getHSBColor(
                ((float)pos.getX() + (float)pos.getY() + (float)pos.getZ()) / RuCommonConfig.EUCALYPTUS_TRANSITION_SIZE.get().floatValue(),
                RuCommonConfig.EUCALYPTUS_SATURATION.get().floatValue(),
                RuCommonConfig.EUCALYPTUS_BRIGHTNESS.get().floatValue());
        return rainbow.getRGB();
    }
}
