package vc.andro.poketest.world.generation.entity;

import vc.andro.poketest.voxel.Voxel;
import vc.andro.poketest.world.chunk.Chunk;

public class SimpleVoxelSpawner implements Spawner<ProspectorResult> {

    private final Voxel voxel;

    public SimpleVoxelSpawner(Voxel voxel) {
        this.voxel = voxel;
    }

    @Override
    public void spawn(ProspectorResult prospectorResult, Chunk chunk, int wx, int y, int wz, int cx, int cz, int lx, int lz) {
        chunk.putVoxelAt_LP(lx, y, lz, this.voxel, false);
    }
}
