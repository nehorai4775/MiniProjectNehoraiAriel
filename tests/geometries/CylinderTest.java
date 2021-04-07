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

class CylinderTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        try {
            new Cylinder( 8,new Ray(new Point3D(1,2,3),new Vector(3,2,4)),4);

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Cylinder");
        }
        // =============== Boundary Values Tests ==================
        try {
            new Cylinder( 8,new Ray(new Point3D(1,2,3),new Vector(0,0,0)),4);
            fail("Failed constructing when the vector is the vector zero");

        }
        catch (IllegalArgumentException e) {
        }
    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Cylinder cy = new Cylinder(5,new Ray(new Point3D(1,1,2),new Vector(1,1,1)),5);
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), cy.getNormal(new Point3D(0, 0, 1)),"Bad normal to Cylinder");
    }
}