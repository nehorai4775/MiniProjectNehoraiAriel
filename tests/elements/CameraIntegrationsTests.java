package elements;
import geometries.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

public class CameraIntegrationsTests {
    @Test
    private void testShapesWithCamera(Camera c,Intersectable shape,int excepted){//Auxiliary function
        int count = 0;//count of intersections
        c.setViewPlaneSize(3,3).setDistance(1);
        for(int i=0;i<3;++i){//I go through all the view plane
            for(int j=0;j<3;++j){
                var intersections = shape.findIntersections(c.constructRayThroughPixel(3,3,j,i));
                count+= intersections== null? 0 : intersections.size();//if there is intersection it add the number of intersections else it add 0
            }
        }
        assertEquals(count,excepted,"wrong number of intersections");

    }
@Test
    public  void tests(){
        //the Spheres tests
    Sphere sphere=new Sphere(1, new Point3D(0,0,-3));
    Camera camera1=new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0)).setDistance(1).setViewPlaneSize(3,3);
    Camera camera2=new Camera(new Point3D(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0)).setDistance(1).setViewPlaneSize(3,3);

    //First test case Sphere r=1
    testShapesWithCamera(camera1,sphere,2);

        //Second test case (r=2.5)
     sphere=new Sphere(2.5, new Point3D(0,0,-2.5));
    testShapesWithCamera(camera2,sphere,18);

    //third test case (r=2)
    sphere=new Sphere(2, new Point3D(0,0,-2));
    testShapesWithCamera(camera2,sphere,10);

    //Fourth test case (r=4)
    sphere=new Sphere(4, new Point3D(0,0,-1));
    testShapesWithCamera(camera2,sphere,9);

    //Fifth test case (r=0.5)
    sphere=new Sphere(0.5, new Point3D(0,0,1));
    testShapesWithCamera(camera1,sphere,0);



    //Plane tests:

    Camera camera3=new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,-1,0)).setDistance(1).setViewPlaneSize(3,3);
    //First test Plane in front of the camera
    testShapesWithCamera(camera3,new Plane(new Point3D(0,0,-5),new Vector(0,0,1)),9);

    //second test Plane in with small angel
    testShapesWithCamera(camera3,new Plane(new Point3D(0,0,-5),new Vector(0,1,2)),9);

    //plane parallel to lower rays
    testShapesWithCamera(camera3,new Plane(new Point3D(0,0,-5),new Vector(0,1,1)),6);


   //Triangle tests
    Camera camera4=new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,-1,0)).setDistance(1).setViewPlaneSize(3,3);

    //First test small Triangle
    testShapesWithCamera(camera3,new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2)),1);

    //Second test big Triangle
    testShapesWithCamera(camera3,new Triangle(new Point3D(0,20,-1),new Point3D(1,-1,-2),new Point3D(-1,-1,-2)),2);

}
}
