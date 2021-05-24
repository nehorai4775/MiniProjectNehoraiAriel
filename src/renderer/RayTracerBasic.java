package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
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
     * @param point -the geoPoint
     * @param ray- the ray that cut
     * @return color
     */
    private Color calcLocalEffects(GeoPoint point,Ray ray) {
            Vector v = ray.getDir ();
            Vector n = point._geometry.getNormal(point._point);
            double nv = alignZero(n.dotProduct(v));
            if (nv == 0) return Color.BLACK;
            Material material = point._geometry.getMaterial();
            int nShininess = material.getNshininess();
            double kd = material.getKd(), ks = material.getKs();
            Color color = Color.BLACK;
                for(int i=0;i<_scene.lights.size();++i){
                Vector l = _scene.lights.get(i).getL(point._point);
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) { // sign(nl) == sing(nv)
                    Color lightIntensity = _scene.lights.get(i).getIntensity(point._point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
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
}
