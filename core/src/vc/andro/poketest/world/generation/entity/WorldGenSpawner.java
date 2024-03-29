package vc.andro.poketest.world.generation.entity;

import org.jetbrains.annotations.NotNull;
import vc.andro.poketest.world.chunk.Chunk;

import static vc.andro.poketest.world.chunk.Chunk.CHUNK_SIZE;
import static vc.andro.poketest.world.World.LxWx;
import static vc.andro.poketest.world.World.LzWz;

public class WorldGenSpawner<T extends ProspectorResult> {

    private final SpawnProspector<T> spawnProspector;
    private final Spawner<T>         spawner;

    public WorldGenSpawner(SpawnProspector<T> spawnProspector, Spawner<T> spawner) {
        this.spawnProspector = spawnProspector;
        this.spawner = spawner;
    }

    public void spawnInChunk(@NotNull Chunk chunk) {
        for (int lx = 0; lx < CHUNK_SIZE; lx++) {
            for (int lz = 0; lz < CHUNK_SIZE; lz++) {
                int wx = LxWx(chunk.getCx(), lx);
                int wz = LzWz(chunk.getCz(), lz);
                T result = spawnProspector.prospect(chunk, wx, wz, chunk.getCx(), chunk.getCz(), lx, lz);
                if (result.shouldSpawn) {
                    assert result.spawnY != null : "Prospector should have determined Y position";
                    spawner.spawn(result, chunk, wx, result.spawnY, wz, chunk.getCx(), chunk.getCz(), lx, lz);
                }
            }
        }
    }

}
