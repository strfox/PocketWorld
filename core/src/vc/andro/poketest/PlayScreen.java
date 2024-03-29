package vc.andro.poketest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import vc.andro.poketest.entity.Player;
import vc.andro.poketest.graphics.DebugRenderSystem;
import vc.andro.poketest.graphics.MainRenderSystem;
import vc.andro.poketest.graphics.camera.PlayerCameraStrategy;
import vc.andro.poketest.graphics.camera.PocketCamera;
import vc.andro.poketest.registry.GeneralSettingsRegistry;
import vc.andro.poketest.world.World;
import vc.andro.poketest.world.WorldCreationParams;
import vc.andro.poketest.world.chunk.Chunk;
import vc.andro.poketest.world.generation.WorldGenerator;

import static vc.andro.poketest.world.chunk.Chunk.CHUNK_DEPTH;

public class PlayScreen implements Screen {

    private final PocketCamera      cam;
    private final World             world;
    private final MainRenderSystem  mainRenderSystem;
    private final DebugRenderSystem debugRenderSystem;

    private float timeSinceLastTick;

    public PlayScreen(WorldCreationParams worldCreationParams) {
        world = new WorldGenerator(worldCreationParams).getWorld();
        cam = new PocketCamera(world, new PlayerCameraStrategy(null));
        mainRenderSystem = new MainRenderSystem(world, cam);
        debugRenderSystem = new DebugRenderSystem(world, cam);

        world.chunkGenerationFinished.addOneTimeListener(chunk -> {
            if (chunk.getCx() == 0 && chunk.getCz() == 0) {
                int playerY = Math.min(chunk.getSurfaceVoxelWy_LP(0, 0) + 1, CHUNK_DEPTH);
                Player player = new Player(world, cam);
                player.setPositionWp(0, playerY, 0);
                world.addEntity(player);
                cam.setFollowPlayer(player);
                return true;
            }
            return false;
        });
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);

        mainRenderSystem.render();
        debugRenderSystem.render();
    }

    private void update(float delta) {
        if (timeSinceLastTick >= 1.0f / PocketWorld.TICKS_PER_SECOND) {
            world.tick();
            timeSinceLastTick = Math.max(timeSinceLastTick - (1.0f / PocketWorld.TICKS_PER_SECOND), 0.0f);
        }
        timeSinceLastTick += delta;
        world.update(delta);
        mainRenderSystem.update();

        if (GeneralSettingsRegistry.debugChunkGenerateOnKeyPress && Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            world.generateChunkAtViewpoint();
        }

        world.generateQueuedChunks();

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Chunk c = world.getChunkAtViewpoint();
            if (c != null) {
                c.slopifyVoxels(true);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        cam.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setCursorCatched(false);
    }

    @Override
    public void dispose() {
        mainRenderSystem.dispose();
        debugRenderSystem.dispose();
    }
}
