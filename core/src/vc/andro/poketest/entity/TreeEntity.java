package vc.andro.poketest.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector2;
import vc.andro.poketest.Assets;
import vc.andro.poketest.PocketWorld;
import vc.andro.poketest.util.AtlasUtil;

import static vc.andro.poketest.PocketWorld.TILE_SIZE;

public class TreeEntity extends Entity {

    public TreeEntity() {
        super(new Vector2(2f, 2f));
        TextureAtlas atlas = PocketWorld.assetManager.get(Assets.entityAtlas);
        {
            var mainDecal = new EntityDecal(Decal.newDecal(
                    AtlasUtil.findRegion(atlas, "entity/tree/tree_main"),
                    true
            ));
            mainDecal.offsetWy = mainDecal.getTextureRegion().getRegionHeight() / TILE_SIZE / 2f;
            addDecal(mainDecal);
        }
        {
            var mainDecal90 = new EntityDecal(Decal.newDecal(
                    AtlasUtil.findRegion(atlas, "entity/tree/tree_main"),
                    true
            ));
            mainDecal90.offsetWy = mainDecal90.getTextureRegion().getRegionHeight() / TILE_SIZE / 2f;
            mainDecal90.yaw = 90.0f;
            addDecal(mainDecal90);
        }
        {
            var treeLeafBase = new EntityDecal(Decal.newDecal(
                    AtlasUtil.findRegion(atlas, "entity/tree/tree_leaf_base"),
                    true
            ));
            treeLeafBase.pitch = 90.0f;
            treeLeafBase.offsetWy = 26.0f / TILE_SIZE;
            addDecal(treeLeafBase);
        }
        {
            var treeLeafTop = new EntityDecal(Decal.newDecal(
                    AtlasUtil.findRegion(atlas, "entity/tree/tree_leaf_top"),
                    true
            ));
            treeLeafTop.pitch = 90.0f;
            treeLeafTop.offsetWy = 53.0f / TILE_SIZE;
            addDecal(treeLeafTop);
        }
        {
            var treeTrunk = new EntityDecal(Decal.newDecal(
                    AtlasUtil.findRegion(atlas, "entity/tree/tree_trunk"),
                    true
            ));
            treeTrunk.pitch = 90.0f;
            treeTrunk.offsetWy = 1.0f / TILE_SIZE;
            addDecal(treeTrunk);
        }
    }
}
