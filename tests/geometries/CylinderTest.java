package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {
    @Test
    public void testConstructor() {
        try {
            new Cylinder( 8,new Ray(new Point3D(1,2,3),new Vector(3,2,4)),4);

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Cylinder");
        }
        try {
            new Cylinder( 8,new Ray(new Point3D(1,2,3),new Vector(0,0,0)),4);
            fail("Failed constructing when the vector is the vector zero");

        }
        catch (IllegalArgumentException e) {
        }
    }

    @Test
    void testGetNormal() {
    }
}