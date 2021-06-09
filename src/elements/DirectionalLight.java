package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

public class DirectionalLight extends Light implements LightSource  {
    private Vector _direction;

    /**
     * constructor
     *
     * @param intensity -intensity of the light
     * @param direction - vector direction
     */
    protected DirectionalLight(Color intensity,Vector direction) {
        super(intensity);
        _direction=direction;
    }

    /**
     * getter
     * @param p-point
     * @return intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction.normalize();
    }

    /**
     * getter
     * @param point
     * @return Double.POSITIVE_INFINITY
     */
    public double getDistance(Point3D point){
        return Double.POSITIVE_INFINITY;
    }

    /*
    in direction light we shouldn't do soft shadow
     */
    @Override
    public List<Vector> getL2(Point3D p) {
        return null;
    }
}
