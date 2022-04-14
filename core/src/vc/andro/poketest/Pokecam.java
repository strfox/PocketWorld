package vc.andro.poketest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import static vc.andro.poketest.PokeTest.TILE_SIZE;

public class Pokecam {

    public static final int CAM_SPEED = 5;
    private final PerspectiveCamera camera;

    public Pokecam(float worldWidth, float worldHeight) {
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.near = 0.5f;
        camera.far = 1000f;
        camera.position.set(65, 225, 95);
        camera.lookAt(0, 0, 0);
    }

    public Matrix4 getProjectionMatrix() {
        return camera.combined;
    }

    public Vector3 project(Vector3 worldCoords) {
        return camera.project(worldCoords);
    }

    public boolean isPosOutsideOfCameraView(float x, float z) {
        return !camera.frustum.boundsInFrustum(x, z, 0, TILE_SIZE, TILE_SIZE, 0);
    }

    public void update() {
        updatePosition();
        camera.update();
    }

    private void updatePosition() {
        /*
         * Update camera translation
         */
        int dx = 0;
        int dy = 0;
        int dz = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            dx = -CAM_SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            dx = CAM_SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            dz = CAM_SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            dz = -CAM_SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            dy = -CAM_SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            dy = CAM_SPEED;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET)) {
            camera.rotate(0.01f, 1, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT_BRACKET)) {
            camera.rotate(-0.01f, 1, 0, 0);
        }

        camera.translate(dx, dy, dz);
    }

    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    public Vector3 getPosition() {
        return camera.position;
    }

    public Vector3 getDirection() {
        return camera.direction;
    }

    public Camera getUnderlying() {
        return camera;
    }
}