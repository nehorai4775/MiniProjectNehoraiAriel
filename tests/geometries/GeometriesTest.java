package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntsersections() {

        Geometries list = new Geometries(new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,2,0)),
                new Triangle(new Point3D(0,1.5,0),new Point3D(1,0,0),new Point3D(-1,0,0)),
                new Sphere(2d, new Point3D(1, 0, 0)));




//the List is null
assertNull(new Geometries().findIntersections(new Ray(new Point3D(1,1,1),new Vector(0,0,1))));

//the list is null
        assertNull(list.findIntersections(new Ray(new Point3D(10,1,10),new Vector(0,0,1))));

        //all shapes are cut

        List<Point3D> result=list.findIntersections(new Ray(new Point3D(0,1,-1),new Vector(0,0,1)));

        assertEquals(3,result.size());

        //some of the shapes are cut
         result=list.findIntersections(new Ray(new Point3D(1,0,-1),new Vector(0,0,1)));

        assertEquals(2,result.size());

        //cut only one shape
        result=list.findIntersections(new Ray(new Point3D(100,100,-1),new Vector(1,10,1)));

        assertEquals(1,result.size());


    }
}
