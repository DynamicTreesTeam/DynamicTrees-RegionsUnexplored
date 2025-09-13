package net.dviousdingle.dtruport.tree;

//import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
//import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
//import com.ferreusveritas.dynamictrees.data.provider.BranchLoaderBuilder;
//import com.ferreusveritas.dynamictrees.tree.family.Family;
//import com.ferreusveritas.dynamictrees.util.BlockBounds;
//import dtteam.dtru.block.BambooBranchBlock;
//import dtteam.dtru.event.BakedModelEventHandler;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.api.voxmap.BlockPosBounds;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.data.builder.BranchLoaderBuilder;
import com.dtteam.dynamictrees.tree.family.Family;
import net.dviousdingle.dtruport.block.BambooBranchBlock;
import net.dviousdingle.dtruport.event.BakedModelEventHandler;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
//import net.minecraftforge.client.model.generators.BlockModelBuilder;
//import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiFunction;

public class BambooFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(BambooFamily::new);

    public BambooFamily(ResourceLocation name) {
        super(name);
    }

    @Override
    protected BranchBlock createBranchBlock(ResourceLocation name) {
        return new BambooBranchBlock(name, this.getProperties());
    }

//    @Override
//    public int getPrimaryThickness() {
//        return 1;
//    }
//
//    @Override
//    public int getSecondaryThickness() {
//        return 2;
//    }

    public BlockPosBounds expandLeavesBlockBounds(BlockPosBounds bounds) {
        return bounds.expand(3).expand(Direction.DOWN, 3);
    }

//    @Override
//    public BiFunction<BlockModelBuilder, ExistingFileHelper, BranchLoaderBuilder> generateStateData() {
//        return (parent, existingFileHelper) -> new BranchLoaderBuilder(BakedModelEventHandler.BAMBOO, parent, existingFileHelper);
//    }


}
