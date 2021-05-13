package elements;
import geometries.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class CameraIntegrationsTests {
    @Test
    void testSphereWithCamera(){
        Sphere sphere=new Sphere(new Point3D(0,0,-3),1);
        Camera camera=new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0)).setDistance(1).setViewPlaneSize(3,3);
        List<Point3D> allPoints=null;
        for(int i=0;i<3;++i)
        {
            for(int j=0;j<3;++j)
            {
                Ray ray=camera.constructRayThroughPixel(3,3,j,i);
                List<Point3D>list=sphere.findIntersections(ray);
                if(list!=null){
                    if(allPoints==null){
                        allPoints= new LinkedList<>();
                    }
                    allPoints.addAll(list);
                }
            }
        }
        assertEquals(allPoints.size(),2,"wrong number of intersections");
    }
}
