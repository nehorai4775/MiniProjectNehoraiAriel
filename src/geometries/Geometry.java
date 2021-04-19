package geometries;
import primitives.*;

interface Geometry extends Intersectable  {
    public Vector getNormal(Point3D s);
}