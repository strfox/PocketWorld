package vc.andro.poketest.voxel;

import org.jetbrains.annotations.Nullable;
import vc.andro.poketest.util.CubicGroup;
import vc.andro.poketest.world.chunk.render.DefaultVoxelRenderingStrategy;

public class VoxelSpecs {

    public static final VoxelSpec AIR = new VoxelSpec(null, null, true, false, DefaultVoxelRenderingStrategy.getInstance());

    public static final VoxelSpec GRASS = new VoxelSpec(
            new CubicGroup<>(
                    "tile/bwgrass",
                    "tile/bwwall",
                    "tile/bwwall",
                    "tile/bwwall",
                    "tile/bwwall",
                    "tile/bwwall"),
            null,
            false,
            true, DefaultVoxelRenderingStrategy.getInstance());

    public static final VoxelSpec WATER = new VoxelSpec(
            CubicGroup.newAllSameFaces("tile/water"),
            null,
            false,
            false, DefaultVoxelRenderingStrategy.getInstance());

    public static final VoxelSpec SAND = new VoxelSpec(
            CubicGroup.newAllSameFaces("tile/sand"),
            null,
            false,
            true, DefaultVoxelRenderingStrategy.getInstance());

    public static final VoxelSpec DIRT = new VoxelSpec(
            CubicGroup.newAllSameFaces("tile/bwwall"),
            null,
            false,
            true, DefaultVoxelRenderingStrategy.getInstance());

    public static final VoxelSpec TALL_GRASS = new VoxelSpec(
            CubicGroup.newOnlyTop("tile/tall_grass/tgrass00"),
            null,
            true,
            false, DefaultVoxelRenderingStrategy.getInstance());

    public static final VoxelSpec[] VOXEL_TYPES = new VoxelSpec[]{
            /*   0 */ AIR,
            /*   1 */ GRASS,
            /*   2 */ WATER,
            /*   3 */ SAND,
            /*   4 */ DIRT,
            /*   5 */ TALL_GRASS,
            /*   6 */ null,
            /*   7 */ null,
            /*   8 */ null,
            /*   9 */ null,
            /*  10 */ null,
            /*  11 */ null,
            /*  12 */ null,
            /*  13 */ null,
            /*  14 */ null,
            /*  15 */ null,
            /*  16 */ null,
            /*  17 */ null,
            /*  18 */ null,
            /*  19 */ null,
            /*  20 */ null,
            /*  21 */ null,
            /*  22 */ null,
            /*  23 */ null,
            /*  24 */ null,
            /*  25 */ null,
            /*  26 */ null,
            /*  27 */ null,
            /*  28 */ null,
            /*  29 */ null,
            /*  30 */ null,
            /*  31 */ null,
            /*  32 */ null,
            /*  33 */ null,
            /*  34 */ null,
            /*  35 */ null,
            /*  36 */ null,
            /*  37 */ null,
            /*  38 */ null,
            /*  39 */ null,
            /*  40 */ null,
            /*  41 */ null,
            /*  42 */ null,
            /*  43 */ null,
            /*  44 */ null,
            /*  45 */ null,
            /*  46 */ null,
            /*  47 */ null,
            /*  48 */ null,
            /*  49 */ null,
            /*  50 */ null,
            /*  51 */ null,
            /*  52 */ null,
            /*  53 */ null,
            /*  54 */ null,
            /*  55 */ null,
            /*  56 */ null,
            /*  57 */ null,
            /*  58 */ null,
            /*  59 */ null,
            /*  60 */ null,
            /*  61 */ null,
            /*  62 */ null,
            /*  63 */ null,
            /*  64 */ null,
            /*  65 */ null,
            /*  66 */ null,
            /*  67 */ null,
            /*  68 */ null,
            /*  69 */ null,
            /*  70 */ null,
            /*  71 */ null,
            /*  72 */ null,
            /*  73 */ null,
            /*  74 */ null,
            /*  75 */ null,
            /*  76 */ null,
            /*  77 */ null,
            /*  78 */ null,
            /*  79 */ null,
            /*  80 */ null,
            /*  81 */ null,
            /*  82 */ null,
            /*  83 */ null,
            /*  84 */ null,
            /*  85 */ null,
            /*  86 */ null,
            /*  87 */ null,
            /*  88 */ null,
            /*  89 */ null,
            /*  90 */ null,
            /*  91 */ null,
            /*  92 */ null,
            /*  93 */ null,
            /*  94 */ null,
            /*  95 */ null,
            /*  96 */ null,
            /*  97 */ null,
            /*  98 */ null,
            /*  99 */ null,
            /* 100 */ null,
            /* 101 */ null,
            /* 102 */ null,
            /* 103 */ null,
            /* 104 */ null,
            /* 105 */ null,
            /* 106 */ null,
            /* 107 */ null,
            /* 108 */ null,
            /* 109 */ null,
            /* 110 */ null,
            /* 111 */ null,
            /* 112 */ null,
            /* 113 */ null,
            /* 114 */ null,
            /* 115 */ null,
            /* 116 */ null,
            /* 117 */ null,
            /* 118 */ null,
            /* 119 */ null,
            /* 120 */ null,
            /* 121 */ null,
            /* 122 */ null,
            /* 123 */ null,
            /* 124 */ null,
            /* 125 */ null,
            /* 126 */ null,
            /* 127 */ null
    };

    static {
        //noinspection ConstantConditions
        assert VOXEL_TYPES.length == 128 : "Size of VOXEL_TYPES seems incorrect, should be 128 but is " + VOXEL_TYPES.length;
    }

    public static byte getVoxelId(VoxelSpec voxelSpec) {
        for (int i = 0; i < VOXEL_TYPES.length; i++) {
            VoxelSpec type = VOXEL_TYPES[i];
            if (type == voxelSpec) {
                return (byte) i;
            }
        }
        return -1;
    }

    public static boolean canVoxelConnectWithSlopes(@Nullable VoxelSpec voxelSpec) {
        return voxelSpec == null || voxelSpec.canBeSloped;
    }

    public static VoxelSpec getSpecForVoxel(byte voxel) {
        return VoxelSpecs.VOXEL_TYPES[voxel];
    }
}
