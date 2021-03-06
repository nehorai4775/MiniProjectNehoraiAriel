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

class SphereTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        try {
            //constructing a correct Sphere
            new Sphere(5, new Point3D(1,2,3));

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Sphere");
        }
        // =============== Boundary Values Tests ==================
        try {
            // when the radius is zero
            new Sphere(0, new Point3D(1,2,3));
            fail("Failed constructing when the radius is zero");
        }
        catch (IllegalArgumentException e) {
        }


    }
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //test the get normal
       Sphere sp= new Sphere(5, new Point3D(0,1,2));
        double sqrt3 = Math.sqrt(1d / 3);

        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), sp.getNormal(new Point3D(1, 2, 3)),"Bad normal to Sphere");
    }
    @Test
    public void testfindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),"Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals( 2, result.size(),"Wrong number of points");
        if (result.get(0).getX().getCoord() > result.get(1).getX().getCoord())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result,"Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0)));
        p1 = new Point3D(2, 0, 0);
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result,"Ray starts inside the sphere");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0))),"Ray starts after the sphere");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 1)));
        p1 = new Point3D(1, 0, 1);
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result,"Ray starts at sphere and goes inside");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))),"Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(-0.5, 0, 0), new Vector(1, 0, 0)));
        p1 = new Point3D(0, 0, 0);
        p2 = new Point3D(2, 0, 0);
        assertEquals( 2, result.size(),"Wrong number of points");
        if (result.get(0).getX().getCoord() > result.get(1).getX().getCoord())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1,p2), result,"Ray starts before the sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 0)));
        p1 = new Point3D(0, 0, 0);
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result,"Ray starts at sphere and goes inside");
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1.5, 0, 0), new Vector(-1, 0, 0)));
        p1 = new Point3D(0, 0, 0);
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result,"Ray starts inside");
        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-1, 0, 0)));
        p1 = new Point3D(0, 0, 0);
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result,"Ray starts at the center");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))),"Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(1, 0, 0))),"Ray starts after the sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 0, 0))),"Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(1, 0, 0))),"Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 0, 1), new Vector(1, 0, 0))),"Ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 0, 1))),"Ray's line is outside, ray is orthogonal to ray start to sphere's center line");



    }
}
