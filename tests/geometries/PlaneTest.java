package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    @Test
    public void testConstructor() {
        try {
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 2),
                    new Point3D(0, 0, 3));
            fail("Failed constructing when all the points on the same line");

        }
         catch (IllegalArgumentException e) {
        }
        try {
            new Plane(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(0, 0, 1));
            fail("Failed constructing when the first and the last point are the same");

        }
        catch (IllegalArgumentException e) {
        }
        try {
            new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }

    }

    @Test
    void testGetNormal() {
    }
}