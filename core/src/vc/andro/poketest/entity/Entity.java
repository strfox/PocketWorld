package vc.andro.poketest.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import vc.andro.poketest.Assets;
import vc.andro.poketest.PokeTest;
import vc.andro.poketest.Pokecam;
import vc.andro.poketest.util.AtlasUtil;

import static vc.andro.poketest.PokeTest.TILE_SIZE;

public class Entity {
    private float worldX;
    private float y;
    private float worldZ;

    protected TextureRegion textureRegion;
    protected Decal decal;

    public Entity(String spriteId) {
        textureRegion = AtlasUtil.findRegion(PokeTest.assetManager.get(Assets.entityAtlas), spriteId);
        decal = Decal.newDecal(textureRegion, true);
        decal.setDimensions(
                textureRegion.getRegionWidth() / (float) TILE_SIZE,
                textureRegion.getRegionHeight() / (float) TILE_SIZE
        );
    }

    public void draw(SpriteBatch spriteBatch) {
        draw(spriteBatch, textureRegion);
    }

    protected void draw(SpriteBatch spriteBatch, TextureRegion sprite) {
        spriteBatch.draw(
                sprite,
                worldX * TILE_SIZE,
                worldZ * TILE_SIZE
        );
    }

    public void draw(DecalBatch decalBatch, Pokecam pokecam) {
        decal.setRotation(Vector3.Y, pokecam.getUp());
        decalBatch.add(decal);
    }

    public void tick() {
    }

    public void update(float delta) {
    }

    public void setPosition(float worldX, float y, float worldZ) {
        this.worldX = worldX;
        this.y = y;
        this.worldZ = worldZ;

        decal.setPosition(worldX, y + 0.1f, worldZ);
    }

    public float getWorldX() {
        return worldX;
    }

    public float getY() {
        return y;
    }

    public float getWorldZ() {
        return worldZ;
    }
}
