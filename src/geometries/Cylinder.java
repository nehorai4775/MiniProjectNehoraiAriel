package geometries;
import primitives.*;

import java.util.Objects;

public class Cylinder extends Tube{
    double _height;

    public Cylinder(double height,Ray axisRay,double radius) {
        super(axisRay,radius);
        _height = height;
    }

    public  Vector getNormal(Point3D p){
        return null;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cylinder cylinder = (Cylinder) o;
        return Double.compare(cylinder._height, _height) == 0;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                '}';
    }
}
