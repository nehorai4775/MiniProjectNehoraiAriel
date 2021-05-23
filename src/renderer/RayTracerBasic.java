package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    //constructor
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    //it is return the Fill / environmental lighting color of the scene
    public Color calcColor(Point3D point){
       return  _scene._ambientLight.getIntensity();
    }

    @Override
    public Color traceRay(Ray ray) {
        //find the intersectionsPoints
        List<Point3D> intersections=_scene._geometries.findIntsersections(ray);
        if(intersections==null)
            return _scene._background;
        else{//calculate the color of the ray
            return calcColor(ray.findClosestPoint(intersections));
        }

    }
}
