package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * a class for triangle
 */
public class Triangle extends Polygon {
    /**
     * contractor, uses the father's contractor
     *
     * @param a-point 1
     * @param b-point 2
     * @param c-point 3
     */
    public Triangle(Point3D a, Point3D b, Point3D c) {
        super(a, b, c);
    }

    /**
     * getter
     *
     * @param p-point
     * @return
     */
    public Vector getNormal(Point3D p) {
//according to what that be displayed

        return super.getNormal(p);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
//if p0 is q0
        if (ray.getP0().equals(super.plane.getQ0()))
            return null;
        else {
            //we find the intersection on the plane
            List<GeoPoint> pointIntersection = super.plane.findGeoIntersections(ray);
            if (pointIntersection != null)
                pointIntersection.get(0)._geometry = this;//we change the geometry because ut was plane
            else
                return null;
            //we check if it is on the triangle
            Vector v1 = super.vertices.get(0).subtract(ray.getP0());
            Vector v2 = super.vertices.get(1).subtract(ray.getP0());
            Vector v3 = super.vertices.get(2).subtract(ray.getP0());
            Vector n1 = (v1.crossProduct(v2)).normalize();
            Vector n2 = (v2.crossProduct(v3)).normalize();
            Vector n3 = (v3.crossProduct(v1)).normalize();
            //if it is we return the point
            if (((ray.getDir().dotProduct(n1)) > 0 && (ray.getDir().dotProduct(n2) > 0 && (ray.getDir().dotProduct(n3)) > 0) || (ray.getDir().dotProduct(n1)) < 0 && (ray.getDir().dotProduct(n2) < 0 && (ray.getDir().dotProduct(n3)) < 0))) {
                return pointIntersection;
            } else
                return null;
        }
    }
}
