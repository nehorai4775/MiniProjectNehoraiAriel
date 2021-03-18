package geometries;
import primitives.*;

public class Plane implements  Geometry{
    private  Point3D _q0;
    private Vector _normal;

    public Plane(Point3D a,Point3D b,Point3D c)
    {
        Vector v1 = a.subtract(b);
        Vector v2 = b.subtract(c);
        Vector v3 = v1.crossProduct(v2);

        _q0 = a;
        _normal = v3.normalize();
    }

    public Plane(Point3D a,Vector v)
    {
        _normal = v.normalize();
        _q0 = a;
    }

    public Vector getNormal(Point3D s){
        return _normal;
    }

}
