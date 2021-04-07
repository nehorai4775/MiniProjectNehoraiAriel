package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    public void testConstructor() {
        try {
            new Triangle(new Point3D(1,2,3),new Point3D(2,3,4),new Point3D(2,5,7));

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Triangle");
        }
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
    }
}