package dtteam.dtru.tree;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.tree.family.Family;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.regions_unexplored.config.state.client.RUClientConfig;

import java.awt.*;

public class EucalyptusFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(EucalyptusFamily::new);
    public EucalyptusFamily(ResourceLocation name) {
        super(name);
    }

    @OnlyIn(Dist.CLIENT)
    public int branchColorMultiplier(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        var color = RUClientConfig.DEFAULT.eucalyptusColors;
        Color rainbow = Color.getHSBColor(
                ((float)pos.getX() + (float)pos.getY() + (float)pos.getZ()) / (float)color.transitionSize,
                (float)color.saturation,
                (float)color.brightness);
        return rainbow.getRGB();
    }
}
