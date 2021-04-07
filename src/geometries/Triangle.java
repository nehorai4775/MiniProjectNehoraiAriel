package geometries;
import primitives.*;

public class Triangle extends Polygon {
//a contractor that gets three Point3Ds as parameters And uses the father's contractor
    public Triangle(Point3D a,Point3D b,Point3D c){
        super(a,b,c);
    }
    //a function that returns the normal
    public  Vector getNormal(Point3D p) {

        return super.getNormal(p);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
}
