package net.dviousdingle.dtruport.block;

//import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
//import com.ferreusveritas.dynamictrees.systems.poissondisc.Vec2i;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
//import com.ferreusveritas.dynamictreesplus.systems.mushroomlogic.MushroomCapDisc;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.systems.poissondisc.Vec2i;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import com.dtteam.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
import com.dtteam.dynamictreesplus.systems.mushroomlogic.MushroomCapDisc;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class YellowBioshroomCapProperties extends BioshroomCapProperties {

    public static final TypedRegistry.EntryType<CapProperties> TYPE = TypedRegistry.newType(YellowBioshroomCapProperties::new);

    public YellowBioshroomCapProperties(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected DynamicCapCenterBlock createDynamicCapCenter(BlockBehaviour.Properties properties) {

        return new DynamicCapCenterBlock(this, properties){
            @Override
            public boolean placeRing(LevelAccessor level, BlockPos pos, int radius, int step, boolean yMoved, boolean negFactor) {
                List<Vec2i> ring = MushroomCapDisc.getPrecomputedRing(radius);
                int placed = 0;
                int notPlaced = 0;

                for(Vec2i vec : ring) {
                    BlockPos ringPos = new BlockPos(pos.getX() + vec.x, pos.getY(), pos.getZ() + vec.z);
                    if (canCapReplace(level.getBlockState(ringPos))) {
                        level.setBlock(ringPos, this.getStateForAge(this.properties, step), 2);
                        ++placed;
                    } else {
                        ++notPlaced;
                    }
                }

                return placed >= notPlaced;
            }

            @Nonnull
            private BlockState getStateForAge(CapProperties properties, int age) {
                boolean[] dirs = new boolean[]{true, true, false, false, false, false};
                return properties.getDynamicCapState(age, dirs);
            }

        };
    }
}
