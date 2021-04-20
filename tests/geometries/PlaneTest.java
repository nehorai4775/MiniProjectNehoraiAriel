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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    @Test
    public void testConstructor() {
// =============== Boundary Values Tests ==================
        try {
            //when all the points on the same line
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 2),
                    new Point3D(0, 0, 3));
            fail("Failed constructing when all the points on the same line");

        }
         catch (IllegalArgumentException e) {
        }
        try {
            //when the first and the last point are the same
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(0, 0, 1));
            fail("Failed constructing when the first and the last point are the same");

        }
        catch (IllegalArgumentException e) {
        }
        // ============ Equivalence Partitions Tests ==============
        try {
            //constructing a correct Plane
            new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }

    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //test the get normal
        Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)),"Bad normal to trinagle");
    }
    @Test
    void testfindIntersections()
    {
        //EP
        Plane p =new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,2,0));
        //Ray intersects the plane
        List<Point3D> result = p.findIntersections(new Ray(new Point3D(0.9,0.9,-1),new Vector(0,0,1)));
        Point3D p1=new Point3D(0.9,0.9,0);
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result,"Ray intersects the plane");
        //Ray doesn't intersect the plane
        assertNull(p.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(0, 0, -1))),"Ray doesn't intersect the plane");

        //BVA
        //Ray is parallel to the plane
        assertNull(p.findIntersections(new Ray(new Point3D(3, 0, 1), new Vector(1, 0, 0))),"the ray is in not included in the plane");
//– the ray is not included in the plane
        assertNull(p.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0))),"the ray is  included in the plane");

        //Ray is orthogonal to the plane
        assertNull(p.findIntersections(new Ray(new Point3D(3, 0, 1), new Vector(0, 0, 1))),"the ray after");
        assertNull(p.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(0, 0, 1))),"the ray in");


         p1= new Point3D(3,0,0);
         result=p.findIntersections(new Ray(new Point3D(3, 0, -1), new Vector(0, 0, 1)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result,"the ray is before");


        //Ray is neither orthogonal nor parallel to and begins at the plane
        assertNull(p.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, -1, 1))),"Ray is orthogonal nor parallel to and begins at the plane");

        //Ray is neither orthogonal nor parallel to and begins in the same point which appears as reference point in the plane (Q)
        assertNull(p.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, -1, 1))),"        //Ray is neither orthogonal nor parallel to and begins in the same point which appears as reference point in the plane (Q)");




    }
}