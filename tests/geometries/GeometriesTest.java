package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntsersections() {

        Geometries list = new Geometries(new Triangle(new Point3D(0,1.5,0),new Point3D(1,0,0),new Point3D(-1,0,0)),
                new Sphere(new Point3D(1, 0, 0),1d),
                new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,2,0)));



assertNull(list.findIntsersections(new Ray(new Point3D(1,1,0),new Vector(0,0,1))));



    }
}
