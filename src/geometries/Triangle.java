package geometries;
import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

public class Triangle extends Polygon {
//a contractor that gets three Point3Ds as parameters And uses the father's contractor
    public Triangle(Point3D a,Point3D b,Point3D c){
        super(a,b,c);
    }
    //a function that returns the normal
    public  Vector getNormal(Point3D p) {
//according to what that be displayed

        return super.getNormal(p);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        if(ray.getP0().equals(super.plane.getQ0()))
            return null;
        else
        {
List<Point3D> pointIntersection=super.plane.findIntersections(ray);

    Vector v1 = super.vertices.get(0).subtract(ray.getP0());
    Vector v2 = super.vertices.get(1).subtract(ray.getP0());
    Vector v3 = super.vertices.get(2).subtract(ray.getP0());
    Vector n1 = (v1.crossProduct(v2)).normalize();
    Vector n2 = (v2.crossProduct(v3)).normalize();
    Vector n3 = (v3.crossProduct(v1)).normalize();
    if (((ray.getDir().dotProduct(n1))>0&& (ray.getDir().dotProduct(n2)>0 && (ray.getDir().dotProduct(n3))>0)||(ray.getDir().dotProduct(n1))<0&& (ray.getDir().dotProduct(n2)<0 && (ray.getDir().dotProduct(n3))<0)))
        return pointIntersection;
    else
        return null;

        }
    }
}
