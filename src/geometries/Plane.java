package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

//Plane class
public class Plane implements  Geometry{
    private  Point3D _q0;
    private Vector _normal;

    //a contractor that gets three Point3Ds as parameters
    public Plane(Point3D a,Point3D b,Point3D c)
    {


        Vector v1 = a.subtract(b);
        Vector v2 = b.subtract(c);

            Vector v3 = v1.crossProduct(v2);


        _q0 = a;
        _normal = v3.normalize();

    }
//a constructor that gets a vector and a point as parameters
    public Plane(Point3D a,Vector v)
    {
        _normal = v.normalize();
        _q0 = a;
    }
    //a function that gets a Point3D as parameter and returns the normal
    public Vector getNormal(Point3D s){

        return _normal;
    }
    //a function that returns the normal
    public Vector getNormal(){

        return _normal;
    }
    //a function that returns the q0
    public Point3D getQ0() {
        return _q0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return _q0.equals(plane._q0) && _normal.equals(plane._normal);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }
    @Override
    public List<Point3D> findIntersections(Ray ray) {

        if(_normal.dotProduct(ray.getDir())!=0)
        {
            double t= (_normal.dotProduct(_q0.subtract(ray.getP0()))/_normal.dotProduct(ray.getDir()));
            if(t>0)
            {
                List<Point3D> points=new ArrayList<Point3D>();
                Point3D temp=ray.getP0().add(ray.getDir().scale(t));
                points.add(temp);
                return points;
            }
            else
                return null;
        }
        else
            return null;
    }


}
