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

class TubeTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        try {
            new Tube(new Ray(new Point3D(1,2,3),new Vector(2,5,7)),5);

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Tube");
        }
        // =============== Boundary Values Tests ==================
        try {
            new Tube(new Ray(new Point3D(1,2,3),new Vector(0,0,0)),5);
            fail("Failed constructing when the vector is zero");
        }
        catch (IllegalArgumentException e) {
        }
        try {
            new Tube(new Ray(new Point3D(1,2,3),new Vector(2,5,7)),0);
            fail("Failed constructing when the radius is zero");
        }
        catch (IllegalArgumentException e) {
        }
    }
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Tube tu= new Tube(new Ray(Point3D.ZERO, new Vector(0,0,1)),1);
        double sqrt3 = Math.sqrt(1d / 3);

        assertEquals(new Vector(0, 1, 0), tu.getNormal(new Point3D(0, 1, 1d/2)),"Bad normal to Tube");
    }
}