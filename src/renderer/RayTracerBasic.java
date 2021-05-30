package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    /**
     * Fixed for size moving first rays for shading rays
     */
    //private static final double DELTA = 0.1;
    //constructor
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    //it is return the Fill / environmental lighting color of the scene
    public Color calcColor(GeoPoint point,Ray ray){
       return  _scene._ambientLight.getIntensity().add(point._geometry.getEmission())
               // add calculated light contribution from all light sources)
               .add(calcLocalEffects(point, ray));
    }

    /**
     * according to the presentation
     * @param geoPoint -the geoPoint
     * @param ray- the ray that cut
     * @return color
     */
    private Color calcLocalEffects(GeoPoint geoPoint,Ray ray) {
            Vector v = ray.getDir ();
            Vector n = geoPoint._geometry.getNormal(geoPoint._point);
            double nv = alignZero(n.dotProduct(v));
            if (nv == 0) return Color.BLACK;
            Material material = geoPoint._geometry.getMaterial();
            int nShininess = material.getNshininess();
            double kd = material.getKd(), ks = material.getKs();
            Color color = Color.BLACK;
                for(int i=0;i<_scene.lights.size();++i){
                Vector l = _scene.lights.get(i).getL(geoPoint._point);
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) { // sign(nl) == sing(nv)
                    if (unshaded(_scene.lights.get(i),l,n,geoPoint)){
                        Color lightIntensity = _scene.lights.get(i).getIntensity(geoPoint._point);
                        color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                                calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                    }

                }
            }
            return color;
        }

    /**
     * calculate the diffusive
     * @param kd
     * @param l
     * @param n
     * @param LI
     * @return The color calculated
     */
    private Color calcDiffusive(double kd, Vector l,Vector n,Color LI) {
        return LI.scale(kd*Math.abs(l.dotProduct(n)));

    }

    /**
     * calculate the specular
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShin
     * @param LI
     * @return The color calculated
     */
    private Color calcSpecular(double ks,Vector l,Vector n,Vector v,int nShin,Color LI) {

        return LI.scale(ks*Math.pow(v.scale(-1).dotProduct(l.subtract(n.scale(2*(l.dotProduct(n))))),nShin));
    }


    @Override
    public Color traceRay(Ray ray) {
        //find the intersectionsPoints
        List<GeoPoint> intersections=_scene._geometries.findGeoIntersections(ray);
        if(intersections.isEmpty()||intersections==null)
            return _scene._background;
        else{//calculate the color of the ray
            return calcColor(ray.findGeoClosestPoint(intersections),ray);
        }

    }
    private static final double DELTA = 0.1;
    /**
     * Checks if the place is shaded or not
     * @param l- vector l
     * @param n -normal
     * @param geoPoint -point
     * @return is the place shaded
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geoPoint._point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        double lightDistance = light.getDistance(geoPoint._point);

        for (GeoPoint gp : intersections) {

            if (alignZero(gp._point.distance(geoPoint._point) - lightDistance) <= 0)
                return false;
        }
        return true;
    }
}
