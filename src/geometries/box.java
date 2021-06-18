package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * a class for box
 */
public class box {
    /**
     * two point of the box
     */
    private Point3D _p1;
    private Point3D _p2;

    /**
     * setter
     *
     * @param p1- point 1
     */
    public void setP1(Point3D p1) {
        _p1 = p1;
    }

    /**
     * setter
     *
     * @param p2- point 2
     */
    public void setP2(Point3D p2) {
        _p2 = p2;
    }

    /**
     * getter
     *
     * @return p1
     */
    public Point3D getP1() {
        return _p1;
    }

    /**
     * getter
     *
     * @return p2
     */
    public Point3D getP2() {
        return _p2;
    }

    /**
     * constructor
     *
     * @param p1-point 1
     * @param p2-point 2
     */
    public box(Point3D p1, Point3D p2) {
        _p1 = p1;
        _p2 = p2;
    }

    public boolean isInterscted(Ray ray) {
        /**
         * two temp point that Represent the points when moving the beam that will start from the beginning of the axes
         */
        Point3D tempPoint1 = _p1.add(new Point3D(0, 0, 0).subtract(ray.getP0()));
        Point3D tempPoint2 = _p2.add(new Point3D(0, 0, 0).subtract(ray.getP0()));
/**
 * we divided every axis of every point in the axis of the ray
 */
        double temp1 = tempPoint1.getX().getCoord() / ray.getDir().getHead().getX().getCoord();
        double temp2 = tempPoint2.getX().getCoord() / ray.getDir().getHead().getX().getCoord();
        double temp3 = tempPoint1.getY().getCoord() / ray.getDir().getHead().getY().getCoord();
        double temp4 = tempPoint2.getY().getCoord() / ray.getDir().getHead().getY().getCoord();
        double temp5 = tempPoint1.getZ().getCoord() / ray.getDir().getHead().getZ().getCoord();
        double temp6 = tempPoint2.getZ().getCoord() / ray.getDir().getHead().getZ().getCoord();
//if there is an intersection
        if (intersectionCheck(temp1, temp2, temp3, temp4) && intersectionCheck(temp3, temp4, temp5, temp6) && intersectionCheck(temp5, temp6, temp1, temp2))
            return true;
        else
            return false;

    }

    public boolean intersectionCheck(double t1, double t2, double m1, double m2) {
        //if there isn't have intersection
        if (Math.min(t1, t2) > Math.max(m1, m2) || Math.min(m1, m2) > Math.max(t1, t2))
            return false;
        else
            return true;
    }

}
