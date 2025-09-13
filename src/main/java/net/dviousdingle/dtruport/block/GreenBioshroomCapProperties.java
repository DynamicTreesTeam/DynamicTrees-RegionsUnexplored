package net.dviousdingle.dtruport.block;

//import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
//import com.ferreusveritas.dynamictrees.systems.poissondisc.Vec2i;
//import com.ferreusveritas.dynamictrees.util.CoordUtils;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapBlock;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
//import com.ferreusveritas.dynamictreesplus.systems.mushroomlogic.MushroomCapDisc;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.systems.poissondisc.Vec2i;
import com.dtteam.dynamictrees.utility.CoordUtils;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import com.dtteam.dynamictreesplus.block.mushroom.DynamicCapBlock;
import com.dtteam.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
import com.dtteam.dynamictreesplus.systems.mushroomlogic.MushroomCapDisc;
import net.dviousdingle.dtruport.block.BioshroomCapProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenBioshroomCapProperties extends BioshroomCapProperties {

    public static final TypedRegistry.EntryType<CapProperties> TYPE = TypedRegistry.newType(GreenBioshroomCapProperties::new);

    public GreenBioshroomCapProperties(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected DynamicCapCenterBlock createDynamicCapCenter(BlockBehaviour.Properties properties) {

        return new DynamicCapCenterBlock(this, properties){

            @Override
            public List<BlockPos> getRing(LevelAccessor level, BlockPos pos, int radius) {
                return super.getRing(level, pos, Math.min(radius, 3));
            }

            @Override
            public void clearRing(LevelAccessor level, BlockPos pos, int radius) {
                super.clearRing(level, pos, Math.min(radius, 3));
            }

            @Override
            public boolean placeRing(LevelAccessor level, BlockPos pos, int radius, int step, boolean yMoved, boolean negFactor) {
                List<Vec2i> ring = MushroomCapDisc.getPrecomputedRing(Math.min(radius, 3));
                int placed = 0;
                int notPlaced = 0;

                for(Vec2i vec : ring) {
                    BlockPos ringPos = new BlockPos(pos.getX() + vec.x, pos.getY(), pos.getZ() + vec.z);
                    if (canCapReplace(level.getBlockState(ringPos))) {
                        if (step <= 3 || ShouldPlaceBlock(level, ringPos)){
                            level.setBlock(ringPos, this.getStateForAge(this.properties, step, new Vec2i(-vec.x, -vec.z), yMoved, negFactor, this.properties.isPartOfCap(level.getBlockState(ringPos.above()))), 2);
                        }
                        ++placed;
                    } else {
                        ++notPlaced;
                    }
                }

                return placed >= notPlaced;
            }

            private boolean ShouldPlaceBlock(LevelAccessor level, BlockPos pos){
                BlockState upState = level.getBlockState(pos.above());
                Optional<DynamicCapBlock> capBlock = properties.getDynamicCapBlock();
                if (capBlock.isEmpty() || !upState.is(capBlock.get())) return false;

                return CoordUtils.coordHashCode(pos, 2) % 3 != 0;
            }

            @Nonnull
            private BlockState getStateForAge(CapProperties properties, int age, Vec2i centerDirection, boolean yMoved, boolean negativeFactor, boolean topIsCap) {
                boolean[] dirs = new boolean[]{false, !topIsCap, true, true, true, true};
                if (yMoved || age == 1) {
                    for(Direction dir : Direction.Plane.HORIZONTAL) {
                        float dot = (float)(dir.getNormal().getX() * centerDirection.x + dir.getNormal().getZ() * centerDirection.z);
                        if (dot >= 0.0F) {
                            dirs[negativeFactor ? dir.getOpposite().ordinal() : dir.ordinal()] = false;
                        }
                    }
                }

                return properties.getDynamicCapState(age, dirs);
            }

        };
    }
}
