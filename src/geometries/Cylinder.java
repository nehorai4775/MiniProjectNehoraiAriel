package geometries;

import primitives.*;

import java.util.Objects;

/**
 * a class for cylinder
 */
public class Cylinder extends Tube {
    double _height;

    /**
     * constructor
     *
     * @param height        -high
     * @param axisRay-ray
     * @param radius-radius
     */
    public Cylinder(double height, Ray axisRay, double radius) {
        super(axisRay, radius);
        _height = height;
    }

    //a function that returns the normal
    public Vector getNormal(Point3D p) {
//according to what that be displayed
        return super.getAxisRay().getDir();
    }

    //a function that returns the _height
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
