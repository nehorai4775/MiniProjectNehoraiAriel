package renderer;

import primitives.*;
import scene.Scene;
//abstarct class
public abstract class RayTracerBase {
    protected Scene _scene;

    public RayTracerBase(Scene scene) {
        _scene = scene;
    }
//We implement the function in the RayTracerBasic
    public abstract Color traceRay(Ray ray,boolean softShadow);
}
