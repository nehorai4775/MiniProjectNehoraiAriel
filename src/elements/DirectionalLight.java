package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

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


    @Override
    public Color getIntensity(Point3D p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction.normalize();
    }
}
