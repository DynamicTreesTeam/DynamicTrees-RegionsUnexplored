package dtteam.dtru.cell;

import com.ferreusveritas.dynamictrees.api.cell.Cell;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.cell.CellNull;
import com.ferreusveritas.dynamictrees.api.cell.CellSolver;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.cell.*;
import com.ferreusveritas.dynamictrees.util.SimpleVoxmap;
import dtteam.dtru.DynamicTreesRU;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class DTRUCellKits {

    public static final CellKit SPARSE = new CellKit(new ResourceLocation(DynamicTreesRU.MOD_ID, "sparse")){
        private final Cell sparseBranch = new SparseBranchCell();
        private final Cell sparseLeaves = new NormalCell(1);

        private final CellSolver solver = new CellKits.BasicSolver(new short[]{0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return hydro > 0 ? sparseLeaves : CellNull.NULL_CELL;
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? sparseBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTRULeafClusters.SPARSE;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 1;
        }
    };

    public static final CellKit BRUSH = new CellKit(new ResourceLocation(DynamicTreesRU.MOD_ID, "brush")) {

        private final Cell branch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            final int[] map = {3, 3, 5, 5, 5, 5};

            @Override
            public int getValueFromSide(Direction side) {
                return map[side.ordinal()];
            }
        };

        private final Cell[] normalCells = {
                CellNull.NULL_CELL,
                new NormalCell(1),
                new NormalCell(2),
                new NormalCell(3),
                new NormalCell(4),
                new NormalCell(5),
                new NormalCell(6),
                new NormalCell(7),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[]{
                0x0513, 0x0412, 0x0322, 0x0311, 0x0211,
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return normalCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (radius == 1) return branch;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTRULeafClusters.BRUSH;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 3;
        }

    };

    public static final CellKit EUCALYPTUS = new CellKit(new ResourceLocation(DynamicTreesRU.MOD_ID, "eucalyptus")) {

        private final Cell eucalyptusTopBranch = new BambooTopBranchCell();
        private final Cell eucalyptusBranch = new NormalCell(2);
        private final Cell eucalyptusUpperTrunk = new NormalCell(3);

        private final Cell[] eucalyptusLeaves = new Cell[]{
                CellNull.NULL_CELL,
                new BambooLeafCell(1),
                new BambooLeafCell(2),
                new BambooLeafCell(3),
                new BambooLeafCell(4),
                new BambooLeafCell(5),
                new BambooLeafCell(6),
                new BambooLeafCell(7),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[]{
                0x0514, 0x0423, 0x0411, 0x0312, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return eucalyptusLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return eucalyptusTopBranch;
            if (radius == 1) return eucalyptusBranch;
            if (radius <= 3) return eucalyptusUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTRULeafClusters.BAMBOO;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit POPLAR = new CellKit(new ResourceLocation(DynamicTreesRU.MOD_ID, "poplar")) {

        private final Cell poplarBranch = new PoplarBranchCell();
        private final Cell poplarTopBranch = new PoplarTopBranchCell();
        private final Cell poplarUpperTrunk = new NormalCell(4);

        private final Cell[] poplarLeaves = new Cell[] {
                CellNull.NULL_CELL,
                new PoplarLeafCell(1),
                new PoplarLeafCell(2),
                new PoplarLeafCell(3),
                new PoplarLeafCell(4),
                new PoplarLeafCell(5),
                new PoplarLeafCell(6),
                new PoplarLeafCell(7),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {
                0x0412, 0x0311, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return poplarLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return poplarTopBranch;
            if (radius == 1) return poplarBranch;
            if (radius < 4) return poplarUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTRULeafClusters.POPLAR;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit BAMBOO = new CellKit(new ResourceLocation(DynamicTreesRU.MOD_ID, "bamboo")) {

        private final Cell bambooTopBranch = new BambooTopBranchCell();
        private final Cell bambooUpperTrunk = new NormalCell(2);

        private final Cell[] bambooLeaves = new Cell[] {
                CellNull.NULL_CELL,
                new BambooLeafCell(1),
                new BambooLeafCell(2),
                new BambooLeafCell(3),
                new BambooLeafCell(4),
                new BambooLeafCell(5),
                new BambooLeafCell(6),
                new BambooLeafCell(7)
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[] {
                0x0514, 0x0423, 0x0411, 0x0312, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return bambooLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return bambooTopBranch;
            if (radius == 2) return bambooUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTRULeafClusters.BAMBOO;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit DOME = new CellKit(DynamicTreesRU.location("dome")) {

        private final Cell acaciaBranch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            final int[] map = {0, 3, 5, 5, 5, 5};

            @Override
            public int getValueFromSide(Direction side) {
                return map[side.ordinal()];
            }

        };

        private final Cell[] acaciaLeafCells = {
                CellNull.NULL_CELL,
                new AcaciaLeafCell(1),
                new AcaciaLeafCell(2),
                new AcaciaLeafCell(3),
                new AcaciaLeafCell(4),
                new AcaciaLeafCell(5),
                new AcaciaLeafCell(6),
                new AcaciaLeafCell(7)
        };

        private final CellKits.BasicSolver acaciaSolver = new CellKits.BasicSolver(new short[]{0x0514, 0x0423, 0x0412, 0x0312, 0x0211});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return acaciaLeafCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return radius == 1 ? acaciaBranch : CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return LeafClusters.ACACIA;
        }

        @Override
        public CellSolver getCellSolver() {
            return acaciaSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static final CellKit JOSHUA = new CellKit(DynamicTreesRU.location("joshua")) {

        private final Cell joshuaBranch = new Cell() {
            @Override
            public int getValue() {
                return 5;
            }

            @Override
            public int getValueFromSide(Direction side) {
                return side == Direction.UP ? getValue() : 0;
            }

        };

        private final Cell[] joshuaFrondCells = {
                CellNull.NULL_CELL,
                new JoshuaFrondCell(1),
                new JoshuaFrondCell(2),
                new JoshuaFrondCell(3),
                new JoshuaFrondCell(4),
                new JoshuaFrondCell(5),
                new JoshuaFrondCell(6),
                new JoshuaFrondCell(7)
        };

        private final CellKits.BasicSolver joshuaSolver = new CellKits.BasicSolver(new short[]{0x0514, 0x0413});

        @Override
        public Cell getCellForLeaves(int hydro) {
            return joshuaFrondCells[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            return joshuaBranch;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTRULeafClusters.JOSHUA;
        }

        @Override
        public CellSolver getCellSolver() {
            return joshuaSolver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

        class JoshuaFrondCell extends MatrixCell {

            public JoshuaFrondCell(int value) {
                super(value, valMap);
            }

            static final byte[] valMap = {
                    0, 0, 0, 0, 0, 0, 0, 0, //D Maps * -> 0
                    0, 1, 2, 3, 4, 5, 6, 7, //U Maps
                    0, 0, 0, 0, 0, 0, 0, 0, //N Maps * -> 0
                    0, 0, 0, 0, 0, 0, 0, 0, //S Maps * -> 0
                    0, 0, 0, 0, 0, 0, 0, 0, //W Maps * -> 0
                    0, 0, 0, 0, 0, 0, 0, 0  //E Maps * -> 0
            };

        }
    };

    public static void register(final Registry<CellKit> registry) {
        registry.registerAll(SPARSE, BRUSH, EUCALYPTUS, POPLAR, BAMBOO, DOME, JOSHUA);
    }

}
