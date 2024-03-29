package vc.andro.poketest.voxel.rendering.faces;

import org.jetbrains.annotations.Nullable;
import vc.andro.poketest.util.IndexArray;
import vc.andro.poketest.util.VertexArray;
import vc.andro.poketest.voxel.Voxel;
import vc.andro.poketest.voxel.VoxelAttributes;

public interface FaceGenerator {
    void createTopVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                           @Nullable VoxelAttributes attributes, int wx, int wy, int wz);

    void createBottomVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                              @Nullable VoxelAttributes attributes, int wx, int wy, int wz);

    void createNorthVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                             @Nullable VoxelAttributes attributes, int wx, int wy, int wz);

    void createSouthVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                             @Nullable VoxelAttributes attributes, int wx, int wy, int wz);

    void createWestVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                            @Nullable VoxelAttributes attributes, int wx, int wy, int wz);

    void createEastVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                            @Nullable VoxelAttributes attributes, int wx, int wy, int wz);
}
