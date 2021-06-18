package geometries;

import primitives.*;
import scene.Scene;

import java.util.*;

/**
 * a class for geometries
 */
public class Geometries implements Intersectable {
    /**
     *
     */
    List<Intersectable> _list = new LinkedList<>();
    ;

    /**
     * constructor
     *
     * @param geometries list
     */
    public Geometries(Intersectable... geometries) {
        Collections.addAll(_list, geometries);
    }



    /**
     * add a shape to the list
     *
     * @param geometries-geometries
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(_list, geometries);
    }

    /**
     * function findIntersections
     *
     * @param ray that intersect the geometries
     * @return intersection points
     */

    public List<Point3D> findIntersections(Ray ray) {
        if (_list.isEmpty()) {
            return null;
        }
        List<Point3D> points = new ArrayList<Point3D>();//list for the point intersection
        List<Point3D> temp = new ArrayList<Point3D>();

        for (int i = 0; i < _list.size(); ++i) {//if the point intersection the ray we add her to the list
            temp = _list.get(i).findIntersections(ray);
            if (temp != null)
                points.addAll(temp);
        }
        if (points.isEmpty())//if the list empty we return null
            return null;
        return points;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {


        List<GeoPoint> intersections = new ArrayList<GeoPoint>();
        for (int i = 0; i < _list.size(); ++i) {
            //we put the geo intersection in a list
            List<GeoPoint> geoIntersections = _list.get(i).findGeoIntersections(ray);
            if (geoIntersections != null)
                intersections.addAll(geoIntersections);
        }

        return intersections;
    }


}

