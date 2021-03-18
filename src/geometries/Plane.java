package geometries;
import primitives.*;

public class Plane implements  Geometry{
    private  Point3D q0;
    private Vector normal;

    public Plane(Point3D a,Point3D b,Point3D c)
    {
        Vector v1 = a.subtract(b);

        q0 = a;
        normal = null;
    }

    public Plane(Point3D a,Vector d)
    {
        normal = d;
        q0 = a;
    }

    public Vector getNormal(Point3D s){
        return null;
    }

}
