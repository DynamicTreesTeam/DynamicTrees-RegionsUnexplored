package net.dviousdingle.dtruport.cell;

//import com.ferreusveritas.dynamictrees.util.SimpleVoxmap;
import com.dtteam.dynamictrees.api.voxmap.SimpleVoxmap;
import net.minecraft.core.BlockPos;

public class DTRULeafClusters {

    public static final SimpleVoxmap SPARSE = new SimpleVoxmap(3, 2, 3, new byte[] {
            0, 1, 0,
            1, 0, 1,
            0, 1, 0,

            0, 0, 0,
            0, 1, 0,
            0, 0, 0,
    }).setCenter(new BlockPos(1, 0, 1));

    public static final SimpleVoxmap POPLAR = new SimpleVoxmap(3, 4, 3, new byte[] {
            0, 0, 0,
            0, 1, 0,
            0, 0, 0,

            0, 2, 0,
            2, 0, 2,
            0, 2, 0,

            0, 1, 0,
            1, 2, 1,
            0, 1, 0,

            0, 0, 0,
            0, 1, 0,
            0, 0, 0,
    }).setCenter(new BlockPos(1, 1, 1));


    public static final SimpleVoxmap BRUSH = new SimpleVoxmap(5, 3, 5, new byte[] {
            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 1, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,

            0, 1, 1, 1, 0,
            1, 2, 3, 2, 1,
            1, 3, 0, 3, 1,
            1, 2, 3, 2, 1,
            0, 1, 1, 1, 0,

            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 1, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,
    }).setCenter(new BlockPos(2, 1, 2));

    public static final SimpleVoxmap BAMBOO = new SimpleVoxmap(5, 7, 5, new byte[] {
            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 0, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,

            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 0, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,

            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 0, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,

            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 0, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0,

            0, 1, 1, 1, 0,
            1, 3, 4, 3, 1,
            1, 4, 0, 4, 1,
            1, 3, 4, 3, 1,
            0, 1, 1, 1, 0,

            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 2, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0,

            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
    }).setCenter(new BlockPos(2, 4, 2));

    public static final SimpleVoxmap JOSHUA = new SimpleVoxmap(3, 3, 3, new byte[]{

            //Layer 0(Bottom)
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,

            //Layer 1(Middle)
            0, 0, 0,
            0, 2, 0,
            0, 0, 0,

            //Layer 2 (Top)
            0, 0, 0,
            0, 1, 0,
            0, 0, 0,

    }).setCenter(new BlockPos(1, 0, 1));

    public static final SimpleVoxmap COBALT = new SimpleVoxmap(5, 2, 5, new byte[] {
            0, 0, 0, 0, 0,
            0, 1, 2, 1, 0,
            0, 2, 0, 2, 0,
            0, 1, 2, 1, 0,
            0, 0, 0, 0, 0,

            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 2, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,
    }).setCenter(new BlockPos(2, 0, 2));

    public static final SimpleVoxmap WILLOW = new SimpleVoxmap(5, 4, 5, new byte[]{
            //Layer 0 (Bottom)
            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0,

            //Layer 1
            0, 1, 1, 1, 0,
            1, 3, 4, 3, 1,
            1, 4, 0, 4, 1,
            1, 3, 4, 3, 1,
            0, 1, 1, 1, 0,

            //Layer 2
            0, 1, 1, 1, 0,
            1, 2, 3, 2, 1,
            1, 3, 4, 3, 1,
            1, 2, 3, 2, 1,
            0, 1, 1, 1, 0,

            //Layer 3(Top)
            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0

    }).setCenter(new BlockPos(2, 1, 2));
    
}
