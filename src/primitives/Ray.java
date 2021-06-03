package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

public class Ray {

    private static final double DELTA = 0.1;


    private final  Point3D _p0;
    private final  Vector _dir;

    public Ray(Point3D point, Vector v, Vector n) {
        Vector delta = n.scale(n.dotProduct(v) > 0 ? DELTA : - DELTA);
        _p0 = point.add(delta);
        _dir = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    //getters
    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    // a constructor
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        dir.normalize();
        _dir = dir;
    }

    public Point3D getPoint(double t)//Auxiliary function
    {//calculate the point p1 it is p0+ (dir*t)
        Point3D p1 = new Point3D(1, 2, 3);
        p1 = _p0.add(_dir.scale(t));
        return p1;
    }

    public Point3D findClosestPoint(List<Point3D> list) {
        Point3D closestPoint = null;//the closest point
        double min = 0;//minimum distance
        for (int i = 0; i < list.size(); ++i) {//I go over all the points
            if (i == 0) {//if it is the first point so we should set the minimum and the closest point
                min = list.get(i).distance(_p0);
                closestPoint = list.get(i);
            } else if (min > list.get(i).distance(_p0))//if the distance small than the min so we reset the minimum and the closest point
            {
                min = list.get(i).distance(_p0);
                closestPoint = list.get(i);
            }
        }
return closestPoint;
    }
    public GeoPoint findGeoClosestPoint(List < GeoPoint > list) {
        GeoPoint closestGeoPoint = null;//the closest point
        double min = 0;//minimum distance
        for (int i = 0; i < list.size(); ++i) {//I go over all the points
            if (i == 0) {//if it is the first point so we should set the minimum and the closest point
                min = list.get(i)._point.distance(_p0);
                closestGeoPoint = list.get(i);
            } else if (min > list.get(i)._point.distance(_p0))//if the distance small than the min so we reset the minimum and the closest point
            {
                min = list.get(i)._point.distance(_p0);
                closestGeoPoint = list.get(i);
            }
        }
        return closestGeoPoint;

    }

}
