package geometries;
import primitives.*;

import java.util.List;

/**
 * abstract class
 */
public abstract class Geometry implements Intersectable  {

    private Material Material=new Material();
    protected Color _emission = Color.BLACK;

    /**
     * setter
      * @param emission
     * @return this
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    /**
     * setter
     * @param material
     * @return this
     */
    public Geometry setMaterial(primitives.Material material) {
        Material = material;
        return this;
    }

    /**
     * getter
     * @return material
     */
    public primitives.Material getMaterial() {
        return Material;
    }

    /**
     * getter
     * @return emission
     */
    public Color getEmission() {

        return _emission;
    }


    public abstract Vector getNormal(Point3D s);
}