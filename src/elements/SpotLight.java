package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector direction;

    /**
     * constructor
     * @param intensity -intensity of the light
     * @param position -position
     * @param kc -Discount coefficients
     * @param kl -Discount coefficients
     * @param kq-Discount coefficients
     * @param direction- vector direction
     */
    public SpotLight(Color intensity, Point3D position, double kc, double kl, double kq, Vector direction) {
        super(intensity, position, kc, kl, kq);
        this.direction = direction;
    }

    /**
     * constructor
     * @param intensity-intensity of the light
     * @param position-position
     * @param direction- vector direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    /**
     * acoording to the presentation
     * @param p-point
     * @return intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity(p).scale(Math.max(0,direction.normalize().dotProduct(getL(p).normalize())));
    }


    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }

    /**
     * getter
     * @param point
     * @return distance
     */
    public double getDistance(Point3D point){
       return super.getDistance(point);
    }
}
