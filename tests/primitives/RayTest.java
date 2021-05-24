package primitives;

import org.junit.jupiter.api.Test;
import scene.Scene;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {
        Ray ray=new Ray(new Point3D(0,0,0),new Vector(1,0,0));
        Point3D p0=new Point3D(0,2,0);
        Point3D p1=new Point3D(0,0,1);
        Point3D p2=new Point3D(3,0,0);
List<Point3D> list = new ArrayList<Point3D>();


list.add(p0);
list.add(p1);
list.add(p2);
        // ============ Equivalence Partitions Tests ==============

        assertTrue(ray.findClosestPoint(list).equals(new Point3D(0,0,1)),"the closest point in the mid of the array");
        // =============== Boundary Values Tests ==================

        ray=new Ray(new Point3D(0,1,0),new Vector(1,0,0));
        assertTrue(ray.findClosestPoint(list).equals(new Point3D(0,2,0)),"the closest point in the first of the array");
        ray=new Ray(new Point3D(2,0,0),new Vector(1,0,0));
        assertTrue(ray.findClosestPoint(list).equals(new Point3D(3,0,0)),"the closest point in the last of the array");
        list.remove(p0);
        list.remove(p1);
        list.remove(p2);
        assertNull(ray.findClosestPoint(list),"empty list");

    }
    @Test
    void testFindGeoClosestPoint() {
        Ray ray=new Ray(new Point3D(0,0,0),new Vector(1,0,0));
        Sphere sphere=new Sphere(new Point3D(1,1,1),20);
        GeoPoint p0=new GeoPoint(sphere,new Point3D(0,2,0));
        GeoPoint p1=new GeoPoint(sphere,new Point3D(0,0,1));
        GeoPoint p2=new GeoPoint(sphere,new Point3D(3,0,0));
        List<GeoPoint> list = new ArrayList<GeoPoint>(List.of(p0,p1,p2));

        // ============ Equivalence Partitions Tests ==============

        assertTrue(ray.findGeoClosestPoint(list).equals(new GeoPoint(sphere,new Point3D(0,0,1))),"the closest GeoPoint in the mid of the array");
        // =============== Boundary Values Tests ==================

        ray=new Ray(new Point3D(0,1,0),new Vector(1,0,0));
        assertTrue(ray.findGeoClosestPoint(list).equals(new GeoPoint(sphere,new Point3D(0,2,0))),"the closest GeoPoint in the first of the array");
        ray=new Ray(new Point3D(2,0,0),new Vector(1,0,0));
        assertTrue(ray.findGeoClosestPoint(list).equals(new GeoPoint(sphere,new Point3D(3,0,0))),"the closest GeoPoint in the last of the array");
        list.remove(p0);
        list.remove(p1);
        list.remove(p2);
        assertNull(ray.findGeoClosestPoint(list),"empty Geolist");
    }

}