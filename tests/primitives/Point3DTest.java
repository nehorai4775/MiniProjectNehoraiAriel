package primitives;
/*
Nehorai Cohen 325356814
Ariel Benshushan 325455426
Nehorai4775@gmail.com
benshoshan60@gmail.com

* */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {
//we test the function of point3D
    @Test
    void testDistancrSquared() {
        // ============ Equivalence Partitions Tests ==============
        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(-2, -4, -6);
assertTrue(p1.distancrSquared(p2)==126,"distanceSquared doesn't good");
    }

    @Test
    void testDistancr() {
        // ============ Equivalence Partitions Tests ==============
        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(-2, -4, -6);
        assertTrue(p1.distance(p2)==Math.sqrt(126),"distanceSquared doesn't good");
    }

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Point3D p1 = new Point3D(1, 2, 3);
        assertTrue(Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3)))
           ,"ERROR: Point + Vector does not work correctly");

    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        Point3D p1 = new Point3D(1, 2, 3);
        assertTrue(new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1))
            ,"ERROR: Point - Point does not work correctly");
    }
}