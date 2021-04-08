package geometries;
/*
Nehorai Cohen 325356814
Ariel Benshushan 325455426
Nehorai4775@gmail.com
benshoshan60@gmail.com

* */
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

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
}