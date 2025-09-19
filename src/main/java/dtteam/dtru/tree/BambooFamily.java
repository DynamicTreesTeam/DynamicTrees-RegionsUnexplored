package dtteam.dtru.tree;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.api.voxmap.BlockPosBounds;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.tree.family.Family;
import dtteam.dtru.block.BambooBranchBlock;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

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
//    public BiFunction<BlockModelBuilder, ExistingFileHelper, BranchLoaderBuilder> getBranchLoaderConstructor() {
//        return (parent, existingFileHelper) -> new BranchLoaderBuilder(BakedModelEventHandler.BAMBOO, parent, existingFileHelper);
//    }

}
