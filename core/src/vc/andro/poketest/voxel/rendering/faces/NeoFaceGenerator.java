package vc.andro.poketest.voxel.rendering.faces;

import com.badlogic.gdx.math.Vector3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vc.andro.poketest.util.CubicGroup;
import vc.andro.poketest.util.IndexArray;
import vc.andro.poketest.util.VertexArray;
import vc.andro.poketest.voxel.Voxel;
import vc.andro.poketest.voxel.VoxelAttributes;
import vc.andro.poketest.voxel.rendering.uv.UVCalculator;

import static vc.andro.poketest.Direction.*;

public class NeoFaceGenerator implements FaceGenerator {

    private final @NotNull CubicGroup<UVCalculator> uvCalculators;

    public NeoFaceGenerator(@NotNull CubicGroup<UVCalculator> uvCalculators) {
        this.uvCalculators = uvCalculators;
    }

    private final Vector3 vP = new Vector3();
    private final Vector3 vQ = new Vector3();

    private synchronized Vector3 calculateNormals(Vector3 v0, Vector3 v1, Vector3 v2) {
        vP.set(v1).sub(v0);
        vQ.set(v1).sub(v2);
        vP.crs(vQ);
        return vP.nor();
    }

    @Override
    public synchronized void createTopVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                                               @Nullable VoxelAttributes attributes, int wx, int wy, int wz) {
        UVCalculator uvCalc = uvCalculators.getFace(CubicGroup.Face.TOP);

        float hNorthwest = (attributes == null ? 1.0f : attributes.getHeightInDirection(NORTHWEST));
        float hNortheast = (attributes == null ? 1.0f : attributes.getHeightInDirection(NORTHEAST));
        float hSouthEast = (attributes == null ? 1.0f : attributes.getHeightInDirection(SOUTHEAST));
        float hSouthwest = (attributes == null ? 1.0f : attributes.getHeightInDirection(SOUTHWEST));

        Vector3 nwVert = new Vector3(wx, wy + hNorthwest, wz);
        Vector3 neVert = new Vector3(wx + 1, wy + hNortheast, wz);
        // Vector3 swVert = new Vector3(wx + 1, wy + hSouthEast, wz + 1);
        Vector3 seVert = new Vector3(wx, wy + hSouthEast, wz + 1);

        Vector3 normals = calculateNormals(nwVert, neVert, seVert);

        vertices.addVertex8f(
                wx,
                wy + hNorthwest,
                wz,
                normals.x,
                normals.y,
                normals.z,
                uvCalc.getU(CubicGroup.Face.TOP, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.TOP, voxel, wx, wy, wz)
        );

        vertices.addVertex8f(
                wx + 1,
                wy + hNortheast,
                wz,
                normals.x,
                normals.y,
                normals.z,
                uvCalc.getU2(CubicGroup.Face.TOP, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.TOP, voxel, wx, wy, wz)
        );

        vertices.addVertex8f(
                wx + 1,
                wy + hSouthEast,
                wz + 1,
                normals.x,
                normals.y,
                normals.z,
                uvCalc.getU2(CubicGroup.Face.TOP, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.TOP, voxel, wx, wy, wz)
        );

        vertices.addVertex8f(
                wx,
                wy + hSouthwest,
                wz + 1,
                normals.x,
                normals.y,
                normals.z,
                uvCalc.getU(CubicGroup.Face.TOP, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.TOP, voxel, wx, wy, wz)
        );

        indices.addSquare();
    }

    @Override
    public void createEastVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                                   @Nullable VoxelAttributes attributes, int wx, int wy, int wz) {
        UVCalculator uvCalc = uvCalculators.getFace(CubicGroup.Face.EAST);
        vertices.addVertex8f(
                wx + 1,
                wy,
                wz,
                1,
                0,
                0,
                uvCalc.getU(CubicGroup.Face.EAST, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.EAST, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy,
                wz + 1,
                1,
                0,
                0,
                uvCalc.getU2(CubicGroup.Face.EAST, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.EAST, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy + (attributes == null ? 1.0f : attributes.getHeightInDirection(SOUTHEAST)),
                wz + 1,
                1,
                0,
                0,
                uvCalc.getU2(CubicGroup.Face.EAST, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.EAST, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy + (attributes == null ? 1.0f : attributes.getHeightInDirection(NORTHEAST)),
                wz,
                1,
                0,
                0,
                uvCalc.getU(CubicGroup.Face.EAST, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.EAST, voxel, wx, wy, wz));

        indices.addSquare();
    }

    @Override
    public void createNorthVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                                    @Nullable VoxelAttributes attributes, int wx, int wy, int wz) {
        UVCalculator uvCalc = uvCalculators.getFace(CubicGroup.Face.NORTH);
        vertices.addVertex8f(
                wx,
                wy,
                wz,
                0,
                0,
                1,
                uvCalc.getU(CubicGroup.Face.NORTH, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.NORTH, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy,
                wz,
                0,
                0,
                1,
                uvCalc.getU2(CubicGroup.Face.NORTH, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.NORTH, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy + (attributes == null ? 1.0f : attributes.getHeightInDirection(NORTHEAST)),
                wz,
                0,
                0,
                1,
                uvCalc.getU2(CubicGroup.Face.NORTH, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.NORTH, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx,
                wy + (attributes == null ? 1.0f : attributes.getHeightInDirection(NORTHWEST)),
                wz,
                0,
                0,
                1,
                uvCalc.getU(CubicGroup.Face.NORTH, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.NORTH, voxel, wx, wy, wz));

        indices.addSquare();
    }

    @Override
    public void createSouthVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                                    @Nullable VoxelAttributes attributes, int wx, int wy, int wz) {
        UVCalculator uvCalc = uvCalculators.getFace(CubicGroup.Face.SOUTH);
        vertices.addVertex8f(
                wx,
                wy,
                wz + 1,
                0,
                0,
                -1,
                uvCalc.getU(CubicGroup.Face.SOUTH, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.SOUTH, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx,
                wy + (attributes == null ? 1.0f : attributes.getHeightInDirection(SOUTHWEST)),
                wz + 1,
                0,
                0,
                -1,
                uvCalc.getU(CubicGroup.Face.SOUTH, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.SOUTH, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy + (attributes == null ? 1.0f : attributes.getHeightInDirection(SOUTHEAST)),
                wz + 1,
                0,
                0,
                -1,
                uvCalc.getU2(CubicGroup.Face.SOUTH, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.SOUTH, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy,
                wz + 1,
                0,
                0,
                -1,
                uvCalc.getU2(CubicGroup.Face.SOUTH, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.SOUTH, voxel, wx, wy, wz));

        indices.addSquare();
    }

    @Override
    public void createWestVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                                   @Nullable VoxelAttributes attributes, int wx, int wy, int wz) {
        UVCalculator uvCalc = uvCalculators.getFace(CubicGroup.Face.WEST);
        vertices.addVertex8f(
                wx,
                wy,
                wz,
                -1,
                0,
                0,
                uvCalc.getU(CubicGroup.Face.WEST, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.WEST, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx,
                wy + (attributes == null ? 1.0f : attributes.getHeightInDirection(NORTHWEST)),
                wz,
                -1,
                0,
                0,
                uvCalc.getU(CubicGroup.Face.WEST, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.WEST, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx,
                wy + (attributes == null ? 1.0f : attributes.getHeightInDirection(SOUTHWEST)),
                wz + 1,
                -1,
                0,
                0,
                uvCalc.getU2(CubicGroup.Face.WEST, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.WEST, voxel, wx, wy, wz));

        vertices.addVertex8f(
                wx,
                wy,
                wz + 1,
                -1,
                0,
                0,
                uvCalc.getU2(CubicGroup.Face.WEST, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.WEST, voxel, wx, wy, wz));

        indices.addSquare();
    }

    @Override
    public void createBottomVertices(VertexArray vertices, IndexArray indices, Voxel voxel,
                                     @Nullable VoxelAttributes attributes, int wx, int wy, int wz) {
        UVCalculator uvCalc = uvCalculators.getFace(CubicGroup.Face.BOTTOM);
        vertices.addVertex8f(
                wx,
                wy,
                wz,
                0,
                -1,
                0,
                uvCalc.getU(CubicGroup.Face.BOTTOM, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.BOTTOM, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx,
                wy,
                wz + 1,
                0,
                -1,
                0,
                uvCalc.getU(CubicGroup.Face.BOTTOM, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.BOTTOM, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy,
                wz + 1,
                0,
                -1,
                0,
                uvCalc.getU2(CubicGroup.Face.BOTTOM, voxel, wx, wy, wz),
                uvCalc.getV2(CubicGroup.Face.BOTTOM, voxel, wx, wy, wz));
        vertices.addVertex8f(
                wx + 1,
                wy,
                wz,
                0,
                -1,
                0,
                uvCalc.getU2(CubicGroup.Face.BOTTOM, voxel, wx, wy, wz),
                uvCalc.getV(CubicGroup.Face.BOTTOM, voxel, wx, wy, wz));

        indices.addSquare();
    }
}
