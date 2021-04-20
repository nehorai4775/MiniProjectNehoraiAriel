package geometries;
/*
Nehorai Cohen 325356814
Ariel Benshushan 325455426
Nehorai4775@gmail.com
benshoshan60@gmail.com

* */
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        try {
            //constructing a correct Triangle
            new Triangle(new Point3D(1,2,3),new Point3D(2,3,4),new Point3D(2,5,7));

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Triangle");
        }
        // =============== Boundary Values Tests ==================
        try {
            //when the two points are the same
            new Triangle(new Point3D(1,2,3),new Point3D(1,2,3),new Point3D(2,5,7));
            fail("Failed constructing when the two points are the same");
        }
        catch (IllegalArgumentException e) {
        }
        try {
            //when the two points are the same
            new Triangle(new Point3D(1,2,3),new Point3D(2,5,7),new Point3D(2,5,7));
            fail("Failed constructing when the two points are the same");
        }
        catch (IllegalArgumentException e) {
        }
        try {
            //when the all points are the same
            new Triangle(new Point3D(1,2,3),new Point3D(1,2,3),new Point3D(1,2,3));
            fail("Failed constructing when the all points are the same");
        }
        catch (IllegalArgumentException e) {
        }

    }
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //test the get normal
        Triangle tr = new Triangle(new Point3D(0,0,1),new Point3D(1,0,0),new Point3D(0,1,0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), tr.getNormal(new Point3D(0, 0, 1)),"Bad normal to trinagle");
    }

    @Test
    public void testfindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //ray intersection the triangle inside
        Triangle t1=new Triangle(new Point3D(0,1.5,0),new Point3D(1,0,0),new Point3D(-1,0,0));
        Point3D p1=new Point3D(0,1,0);
        List<Point3D> result = t1.findIntersections(new Ray(new Point3D(0,1,-1),new Vector(0,0,1)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result,"ray intersection the triangle inside");

        //ray intersection the triangle outside
        //ray intersection the trinagle between the two segment
        // =============== Boundary Values Tests ==================

        //ray intersection the trinagle in the ray of his segment
        //ray intersection the triangle in his segment
        // ray intersection the triangle in his vertex
    }
}