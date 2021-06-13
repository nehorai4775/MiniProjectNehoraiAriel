package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class
 */
public class Plane extends Geometry {
    private Point3D _q0;
    private Vector _normal;

    /**
     * contractor,makes a Plane from three points
     *
     * @param a-point 1
     * @param b-point 2
     * @param c-point 3
     */
    public Plane(Point3D a, Point3D b, Point3D c) {


        Vector v1 = a.subtract(b);
        Vector v2 = b.subtract(c);

        Vector v3 = v1.crossProduct(v2);


        _q0 = a;
        _normal = v3.normalize();

    }

    /**
     * contractor,makes a Plane from one point and one vector
     *
     * @param a-point
     * @param v-vector
     */
    public Plane(Point3D a, Vector v) {
        _normal = v.normalize();
        _q0 = a;
    }

    /**
     * getter
     *
     * @param s-point
     * @return the normal by a point
     */
    public Vector getNormal(Point3D s) {

        return _normal;
    }

    /**
     * getter
     *
     * @return the normal
     */
    public Vector getNormal() {

        return _normal;
    }

    /**
     * getter
     *
     * @return q0
     */
    public Point3D getQ0() {
        return _q0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return _q0.equals(plane._q0) && _normal.equals(plane._normal);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        if (isZero(ray.getP0().getX().getCoord() - _q0.getX().getCoord()) && isZero(ray.getP0().getY().getCoord() - _q0.getY().getCoord()) && isZero(ray.getP0().getZ().getCoord() - _q0.getZ().getCoord()))
            return null;
        if (_normal.dotProduct(ray.getDir()) != 0) {
            double t = alignZero(_normal.dotProduct(_q0.subtract(ray.getP0())) / _normal.dotProduct(ray.getDir()));
            if (t > 0) {
                List<GeoPoint> points = new ArrayList<GeoPoint>();
                GeoPoint temp = new GeoPoint(this, ray.getPoint(t));
                points.add(temp);
                return points;
            } else
                return null;
        } else
            return null;

    }


}
