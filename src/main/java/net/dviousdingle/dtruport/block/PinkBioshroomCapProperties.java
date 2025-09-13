package net.dviousdingle.dtruport.block;

//import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
//import com.ferreusveritas.dynamictrees.systems.poissondisc.Vec2i;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.systems.poissondisc.Vec2i;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import com.dtteam.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
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

public class PinkBioshroomCapProperties extends BioshroomCapProperties {

    public static final TypedRegistry.EntryType<CapProperties> TYPE = TypedRegistry.newType(PinkBioshroomCapProperties::new);

    public PinkBioshroomCapProperties(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected DynamicCapCenterBlock createDynamicCapCenter(BlockBehaviour.Properties properties) {
        return new DynamicCapCenterBlock(this, properties){

            @Override
            public void clearRing(LevelAccessor level, BlockPos pos, int radius) {
                if (radius == 1){
                    for(Direction dir : Direction.Plane.HORIZONTAL) {
                        BlockPos ringPos = new BlockPos(pos.getX() + dir.getStepX(), pos.getY(), pos.getZ() + dir.getStepZ());
                        if (getProperties(this.defaultBlockState()).isPartOfCap(level.getBlockState(ringPos))) {
                            level.setBlock(ringPos, Blocks.AIR.defaultBlockState(), 2);
                        }
                    }
                    return;
                }
                super.clearRing(level, pos, radius-1);
            }

            @Override
            public boolean placeRing(LevelAccessor level, BlockPos pos, int radius, int step, boolean yMoved, boolean negFactor) {
                if (radius == 1){
                    int placed = 0;
                    int notPlaced = 0;
                    CapProperties capProperties = getProperties(this.defaultBlockState());
                    for (Direction dir : Direction.Plane.HORIZONTAL){
                        int x = dir.getStepX();
                        int z = dir.getStepZ();
                        BlockPos ringPos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                        if (canCapReplace(level.getBlockState(ringPos))){
                            level.setBlock(ringPos, getStateForAge(capProperties, step, new Vec2i(-x,-z), yMoved, negFactor, capProperties.isPartOfCap(level.getBlockState(ringPos.above()))),2);
                            placed++;
                        } else notPlaced ++;
                    }
                    //if more than half of the blocks failed then theres not enough support for the next rings
                    return placed >= notPlaced;
                } else {
                    return super.placeRing(level, pos, radius-1, step, yMoved, negFactor);
                }
            }

            @Override
            public List<BlockPos> getRing(LevelAccessor level, BlockPos pos, int radius) {
                if (radius == 1){
                    List<BlockPos> positions = new ArrayList<>();
                    positions.add(pos.north());
                    positions.add(pos.east());
                    positions.add(pos.south());
                    positions.add(pos.west());
                    return positions;
                } else {
                    return super.getRing(level, pos, radius - 1);
                }
            }

            @Nonnull
            private BlockState getStateForAge(CapProperties properties, int age, Vec2i centerDirection, boolean yMoved, boolean negativeFactor, boolean topIsCap) {
                boolean[] dirs = new boolean[]{false, !topIsCap, false, false, false, false};
                if (yMoved || age == 1) {

                    for (Direction dir : Direction.Plane.HORIZONTAL) {
                        float dot = (float) (dir.getNormal().getX() * centerDirection.x + dir.getNormal().getZ() * centerDirection.z);
                        if (dot >= 0) {
                            dirs[negativeFactor ? dir.ordinal() : dir.getOpposite().ordinal()] = true;
                        }
                    }
                }

                return properties.getDynamicCapState(age, dirs);
            }

        };
    }
}
