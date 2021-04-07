package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    @Test
    public void testConstructor() {
        try {
            new Sphere(new Point3D(1,2,3),5);

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Sphere");
        }
        try {
            new Sphere(new Point3D(1,2,3),0);
            fail("Failed constructing when the radius is zero");
        }
        catch (IllegalArgumentException e) {
        }


    }
    @Test
    void testGetNormal() {
    }
}