package elements;

import primitives.*;

/**
 * interface
 */
public interface LightSource {
    /**
     * getter
     * @param p-point
     * @return intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * getter
     * @param p-point
     * @return vector L
     */
    public Vector getL(Point3D p);
    double getDistance(Point3D point);
}
