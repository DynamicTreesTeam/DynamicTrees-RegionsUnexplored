package dtteam.dtru.init;

import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.block.rooty.SoilProperties;
import com.ferreusveritas.dynamictrees.systems.BranchConnectables;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import dtteam.dtru.DynamicTreesRU;
import dtteam.dtru.block.BiomeshroomCapProperties;
import dtteam.dtru.tree.BioshroomSpecies;
import dtteam.dtru.tree.GenUnderwaterSpecies;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.regions_unexplored.block.RuBlocks;

public class DTRUPlusRegistries {

    public static void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherAllData(DynamicTreesRU.MOD_ID, event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                CapProperties.REGISTRY
        );
    }

    @SubscribeEvent
    public void registerCapPropertiesTypes(final TypeRegistryEvent<CapProperties> event) {
        event.registerType(DynamicTreesRU.location("bioshroom_cap"), BiomeshroomCapProperties.TYPE);
    }

    public static void registerSpeciesTypes(final TypeRegistryEvent<Species> event) {
        event.registerType(new ResourceLocation(DynamicTreesRU.MOD_ID, "bioshroom"), BioshroomSpecies.TYPE);
    }

    public static void setup(){
        setupBioshroomConnectable(RuBlocks.BLUE_BIOSHROOM_BLOCK.get());
        setupBioshroomConnectable(RuBlocks.GLOWING_BLUE_BIOSHROOM_BLOCK.get());
        setupBioshroomConnectable(RuBlocks.GLOWING_GREEN_BIOSHROOM_BLOCK.get());
        setupBioshroomConnectable(RuBlocks.GLOWING_PINK_BIOSHROOM_BLOCK.get());
        setupBioshroomConnectable(RuBlocks.GLOWING_YELLOW_BIOSHROOM_BLOCK.get());
    }

    private static void setupBioshroomConnectable(Block block) {
        BranchConnectables.makeBlockConnectable(block, (state, level, pos, side) -> {
            BlockState branchState = level.getBlockState(pos.relative(Direction.UP));
            BranchBlock branch = TreeHelper.getBranch(branchState);
            return branch != null ? Mth.clamp(branch.getRadius(branchState) - 1, 1, 3) : 3;
        });
    }

}
