package net.dviousdingle.dtruport.tree;

//import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
//import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
//import com.ferreusveritas.dynamictrees.block.DynamicSaplingBlock;
//import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
//import com.ferreusveritas.dynamictrees.systems.nodemapper.NetVolumeNode;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
//import com.ferreusveritas.dynamictrees.tree.species.Species;
//import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
//import com.ferreusveritas.dynamictrees.worldgen.JoCode;
//import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
//import com.ferreusveritas.dynamictreesplus.tree.HugeMushroomSpecies;
import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.sapling.DynamicSaplingBlock;
import com.dtteam.dynamictrees.registry.NeoForgeRegistryHandler;
import com.dtteam.dynamictrees.systems.nodemapper.NetVolumeNode;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import com.dtteam.dynamictreesplus.tree.HugeMushroomSpecies;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;

public class BioshroomSpecies extends HugeMushroomSpecies {
    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultMushroomType(BioshroomSpecies::new);

    public BioshroomSpecies(ResourceLocation name, Family family, CapProperties capProperties) {
        super(name, family, capProperties);
    }

    @Override
    public LogsAndSticks getLogsAndSticks(NetVolumeNode.Volume volume, boolean silkTouch, int fortuneLevel) {
        return super.getLogsAndSticks(volume, true, fortuneLevel);
    }

    @Override
    public Species generateSapling() {
        return !this.shouldGenerateSapling() || this.saplingBlock != null ? this :
                this.setSapling(NeoForgeRegistryHandler.addBlock(this.getSaplingRegName(), () -> new DynamicSaplingBlock(this){
                    @Override
                    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
                        return 10;
                    }
                }));
    }
}
