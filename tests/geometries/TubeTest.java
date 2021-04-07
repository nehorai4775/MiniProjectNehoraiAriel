package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    @Test
    public void testConstructor() {
        try {
            new Tube(new Ray(new Point3D(1,2,3),new Vector(2,5,7)),5);

        }
        catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Tube");
        }
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
    }
}