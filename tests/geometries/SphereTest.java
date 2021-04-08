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

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        try {
            //constructing a correct Sphere
            new Sphere(new Point3D(1,2,3),5);

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Sphere");
        }
        // =============== Boundary Values Tests ==================
        try {
            // when the radius is zero
            new Sphere(new Point3D(1,2,3),0);
            fail("Failed constructing when the radius is zero");
        }
        catch (IllegalArgumentException e) {
        }


    }
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //test the get normal
       Sphere sp= new Sphere(new Point3D(0,1,2),5);
        double sqrt3 = Math.sqrt(1d / 3);

        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), sp.getNormal(new Point3D(1, 2, 3)),"Bad normal to Sphere");
    }
}