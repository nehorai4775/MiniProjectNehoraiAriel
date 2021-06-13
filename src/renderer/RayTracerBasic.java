package renderer;

import elements.DirectionalLight;
import elements.LightSource;
import primitives.*;
import primitives.Color;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.awt.*;
import java.util.List;

import static elements.PointLight.rays;
import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    /**
     * Fixed for size moving first rays for shading rays
     */
    private static final double DELTA = 0.1;

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;



    //constructor
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * calculate color recursive
     *
     * @param point
     * @param ray
     * @param level
     * @param k
     * @return color
     */
    public Color calcColor(GeoPoint point, Ray ray, int level, double k,boolean softShadow) {
        Color color = point._geometry.getEmission();
        color = color.add(calcLocalEffects(point, ray, k,softShadow));
        return 1 == level ? color : color.add(calcGlobalEffects(point, ray.getDir(), level, k,softShadow));
    }

    /**
     * calculate color
     *
     * @param gp
     * @param ray
     * @return color
     */
    private Color calcColor(GeoPoint gp, Ray ray,boolean softShadow) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K,softShadow)
                .add(_scene.ambientLight.getIntensity());

    }

    /**
     * according to the presentation
     * calculate the global effects
     *
     * @param gp
     * @param v
     * @param level
     * @param k
     * @return color
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k,boolean softShadow) {
        Color color = Color.BLACK;
        Vector n = gp._geometry.getNormal(gp._point);
        Material material = gp._geometry.getMaterial();

        double kkr = k * material._kr;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(ConstructingReflectedRay(v, n, gp._point), level, material._kr, kkr,softShadow);

        double kkt = k * material._kt;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(ConstructingRefractedRay(v, n, gp._point), level, material._kt, kkt,softShadow));

        return color;
    }

    /**
     * according to the presentation
     * calculate global effects recursive
     *
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return color
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx,boolean softShadow) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx,softShadow)
                .scale(kx));
    }

    /**
     * according to the presentation
     *
     * @param geoPoint -the geoPoint
     * @param ray-     the ray that cut
     * @return color
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, double k,boolean softShadow) {
        Point3D point = geoPoint._point;
        Vector v = ray.getDir();
        Vector n = geoPoint._geometry.getNormal(point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Material material = geoPoint._geometry.getMaterial();
        int nShininess = material.getNshininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;

        for (var lightSource : _scene.lights) {
            //if we don't want use in soft shadow
            if (softShadow != true) {
                Vector l = lightSource.getL(point);
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) { // sign(nl) == sing(nv)
                    //  if (unshaded(lightSource, l, n, geoPoint)) {
                    double ktr = transparency(lightSource, l, n, geoPoint);
                    if (ktr * k > MIN_CALC_COLOR_K) {

                        Color lightIntensity = lightSource.getIntensity(geoPoint._point).scale(ktr);
                        color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                                calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                    }
                }

            } else if (softShadow == true) {//if we want use in soft shadow
                /**
                 * vectors around the light source
                 */
                if (lightSource.getClass() == DirectionalLight.class) {
                    Vector l = lightSource.getL(point);
                    double nl = alignZero(n.dotProduct(l));
                    if (nl * nv > 0) { // sign(nl) == sing(nv)
                        //  if (unshaded(lightSource, l, n, geoPoint)) {
                        double ktr = transparency(lightSource, l, n, geoPoint);
                        if (ktr * k > MIN_CALC_COLOR_K) {

                            Color lightIntensity = lightSource.getIntensity(geoPoint._point).scale(ktr);
                            color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                                    calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                        }
                    }
                } else {

                    List<Vector> vectorsL = lightSource.getL2(point);
                    Color helpC = Color.BLACK;


                    for (Vector l : vectorsL) {
                        double nl = alignZero(n.dotProduct(l));
                        if (nl * nv > 0) { // sign(nl) == sing(nv)
                            //  if (unshaded(lightSource, l, n, geoPoint)) {
                            double ktr = transparency(lightSource, l, n, geoPoint);
                            if (ktr * k > MIN_CALC_COLOR_K) {

                                Color lightIntensity = lightSource.getIntensity(geoPoint._point).scale(ktr);
                                helpC = helpC.add(calcDiffusive(kd, l, n, lightIntensity),
                                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                            }
                        }
                    }
                    /**
                     * We divide the color each time by the number of rays
                     */
                    helpC = helpC.reduce(rays);
                    color = color.add(helpC);
                }
            }
        }

        return color;

    }

    /**
     * calculate the diffusive
     *
     * @param kd
     * @param l
     * @param n
     * @param LI
     * @return The color calculated
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color LI) {
        return LI.scale(kd * Math.abs(l.dotProduct(n)));

    }

    /**
     * calculate the specular
     *
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShin
     * @param LI
     * @return The color calculated
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShin, Color LI) {

        return LI.scale(ks * Math.pow(v.scale(-1).dotProduct(l.subtract(n.scale(2 * (l.dotProduct(n))))), nShin));
    }

    /**
     * find the closest intersection
     *
     * @param ray
     * @return
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
        if (intersections.isEmpty() || intersections == null)
            return null;
        GeoPoint point = ray.findGeoClosestPoint(intersections);
        return point;
    }

    /**
     * Calculate the color at the point of intersection with the beam
     *
     * @param ray
     * @return color
     */
    @Override
    public Color traceRay(Ray ray,boolean softShadow) {

        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? _scene.background : calcColor(closestPoint, ray,softShadow);
    }


    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point3D point = geopoint._point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        double lightDistance = light.getDistance(geopoint._point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp._point.distance(geopoint._point) - lightDistance) <= 0 && gp._geometry.getMaterial()._kt == 0)
                return false;
        }
        return true;
    }

    /**
     * Constructing reflected ray
     *
     * @param v
     * @param n-normal
     * @param point    -p0
     * @return ray
     */
    private Ray ConstructingReflectedRay(Vector v, Vector n, Point3D point) {

        Vector r = n.scale(2 * v.dotProduct(n)).subtract(v);//Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));

        return new Ray(point, r, n);

    }

    /**
     * Constructing refracted ray
     *
     * @param v
     * @param point-p0
     * @return ray
     */
    private Ray ConstructingRefractedRay(Vector v, Vector n, Point3D point) {
//        Vector delta = n.scale(n.dotProduct(v) > 0 ? DELTA : -DELTA);
//        Point3D _point = point.add(delta);
        return new Ray(point, v, n);
    }


    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point3D point = geoPoint._point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        double lightDistance = ls.getDistance(geoPoint._point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp._point.distance(geoPoint._point) - lightDistance) <= 0) {
                ktr *= gp._geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }

        }
        return ktr;
    }


}
