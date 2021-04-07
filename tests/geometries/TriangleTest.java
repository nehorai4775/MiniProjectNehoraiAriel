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

class TriangleTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        try {
            new Triangle(new Point3D(1,2,3),new Point3D(2,3,4),new Point3D(2,5,7));

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Triangle");
        }
        // =============== Boundary Values Tests ==================
        try {
            new Triangle(new Point3D(1,2,3),new Point3D(1,2,3),new Point3D(2,5,7));
            fail("Failed constructing when the two points are the same");
        }
        catch (IllegalArgumentException e) {
        }
        try {
            new Triangle(new Point3D(1,2,3),new Point3D(2,5,7),new Point3D(2,5,7));
            fail("Failed constructing when the two points are the same");
        }
        catch (IllegalArgumentException e) {
        }
        try {
            new Triangle(new Point3D(1,2,3),new Point3D(1,2,3),new Point3D(1,2,3));
            fail("Failed constructing when the two points are the same");
        }
        catch (IllegalArgumentException e) {
        }

    }
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Triangle tr = new Triangle(new Point3D(0,0,1),new Point3D(1,0,0),new Point3D(0,1,0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), tr.getNormal(new Point3D(0, 0, 1)),"Bad normal to trinagle");
    }
}