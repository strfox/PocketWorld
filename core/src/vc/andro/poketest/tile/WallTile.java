package vc.andro.poketest.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jetbrains.annotations.Nullable;
import vc.andro.poketest.Assets;
import vc.andro.poketest.PokeTest;

import static java.lang.Math.abs;
import static vc.andro.poketest.PokeTest.TILE_SIZE;

public class WallTile extends BasicTile {

    private WallType wallType;
    private TextureRegion fallbackFloorTexture;

    private @Nullable
    BasicTile neighborTile = null;

    public WallTile(float altitude, WallType wallType) {
        super(TileType.WALL, altitude);
        fallbackFloorTexture = PokeTest.assetManager.get(Assets.textureAtlas).findRegion("rocky-floor");
        updateWallType(wallType);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (neighborTile == null) {
            spriteBatch.draw(fallbackFloorTexture, worldX * TILE_SIZE, worldZ * TILE_SIZE);
        } else {
            neighborTile.draw(spriteBatch, worldX, worldZ);
        }
        super.draw(spriteBatch);
    }

    public void updateWallType(WallType newType) {
        setSprite(newType.getSpriteId());
        wallType = newType;
    }

    @Override
    public void doTileUpdate() {
        out:
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (abs(dx) == abs(dz)) {
                    continue;
                }
                BasicTile potentialNeighbor = world.getTileAt(worldX + dx, worldZ + dz);
                if (potentialNeighbor == null) {
                    continue;
                }
                if (considerPotentialNeighborTile(potentialNeighbor)) {
                    break out;
                }
            }
        }
        super.doTileUpdate();
    }

    @Override
    public void receiveTileUpdate(BasicTile updateOrigin) {
        super.receiveTileUpdate(updateOrigin);
        considerPotentialNeighborTile(updateOrigin);
    }

    private boolean considerPotentialNeighborTile(BasicTile potentialNeighbor) {
        { // Ensure the tile is a neighbor tile.
            final int dx = abs(abs(worldX) - abs(potentialNeighbor.worldX));
            final int dz = abs(abs(worldZ) - abs(potentialNeighbor.worldZ));
            if (!(dx == 1 && dz == 0) && !(dz == 1 && dx == 0)) {
                throw new IllegalStateException(
                        "Tiles are not neighbors. This tile: %s. Other tile: %s".formatted(this, neighborTile)
                );
            }
        }

        if (!potentialNeighbor.type.isFlatTile || potentialNeighbor.altitude >= altitude) {
            return false;
        }

        int dirX = worldX.compareTo(potentialNeighbor.worldX);
        int dirZ = -worldZ.compareTo(potentialNeighbor.worldZ);

        boolean acceptNeighbor;
        switch (wallType) {
            case TOP_LEFT_CORNER -> acceptNeighbor = dirX > 0 || dirZ < 0;
            case TOP_EDGE -> acceptNeighbor = dirX == 0 && dirZ < 0;
            case TOP_RIGHT_CORNER -> acceptNeighbor = dirX < 0 || dirZ < 0;
            case LEFT_EDGE -> acceptNeighbor = dirX > 0 && dirZ == 0;
            case RIGHT_EDGE -> acceptNeighbor = dirX < 0 && dirZ == 0;
            case BOTTOM_LEFT_CORNER -> acceptNeighbor = dirX > 0 || dirZ > 0;
            case BOTTOM_EDGE -> acceptNeighbor = dirX == 0 && dirZ > 0;
            case BOTTOM_RIGHT_CORNER -> acceptNeighbor = dirX < 0 || dirZ > 0;
            case TOP_LEFT_INNER_CORNER -> acceptNeighbor = dirX > 0 && dirZ < 0;
            case TOP_RIGHT_INNER_CORNER -> acceptNeighbor = dirX < 0 && dirZ < 0;
            case BOTTOM_LEFT_INNER_CORNER -> acceptNeighbor = dirX > 0 && dirZ > 0;
            case BOTTOM_RIGHT_INNER_CORNER -> acceptNeighbor = dirX < 0 && dirZ > 0;
            default -> throw new AssertionError();
        }

        if (acceptNeighbor) {
            neighborTile = potentialNeighbor;
            return true;
        }
        return false;
    }
}
