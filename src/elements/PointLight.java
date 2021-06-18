package elements;

import geometries.Plane;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * a class for point light
 */
public class PointLight extends Light implements LightSource {
    private Point3D _position;
    private double _kc, _kl, _kq;
    private double LengthOfTheSide = 1;
    /**
     * rays that get out from a point
     */
    public static final int rays = 100;


    /**
     * constructor
     *
     * @param intensity         -intensity of the light
     * @param position-position
     * @param kc                -Discount coefficients
     * @param kl                -Discount coefficients
     * @param kq-Discount       coefficients
     */
    public PointLight(Color intensity, Point3D position, double kc, double kl, double kq) {
        super(intensity);
        _position = position;
        _kc = kc;
        _kl = kl;
        _kq = kq;
    }

    /**
     * constructor
     *
     * @param intensity-intensity of the light
     * @param position-position
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
        _kc = 1;
        _kl = 0;
        _kq = 0;
    }

    /**
     * according to the presentation
     *
     * @param p-point
     * @return intensity
     */
    @Override
    public Color getIntensity(Point3D p) {

        double distance = _position.distance(p);
        return super.getIntensity().scale(1d / (_kc + _kl * distance + _kq * distance * distance));
    }

    /**
     * acoording to the presentation
     *
     * @param p-point
     * @return vector L
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalize();
    }

    /**
     * setter
     *
     * @param kc -Discount coefficients
     * @return Point light by model builder
     */
    public PointLight setKc(double kc) {
        _kc = kc;
        return this;
    }

    /**
     * setter
     * @param kl-Discount coefficients
     * @return Point light by model builder
     */
    public PointLight setKl(double kl) {
        _kl = kl;
        return this;
    }

    /**
     * setter
     * @param kq-Discount coefficients
     * @return Point light by model builder
     */
    public PointLight setKq(double kq) {
        _kq = kq;
        return this;
    }

    /**
     * getter
     *
     * @param point-point
     * @return distance
     */
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }

    /**
     * A function that brings a number of vectors around the point
     *
     * @param p- point pf the light
     * @return vector around the point
     */
    @Override
    public List<Vector> getL2(Point3D p) {
        List<Vector> vectors = new ArrayList<>();
        /**
         * help vectors
         */
        Vector v0, v1;
        /**
         * * A variable that tells how many divide each side
         */
        double divided = Math.sqrt(rays);

        /**
         * plane of the light
         */
        Plane plane = new Plane(_position, getL(p));
        /**
         * vectors of the plane
         */
        List<Vector> vectorsOfThePlane = findVectorsOfPlane(plane);
        /**
         * Starting point of the square around the lighting
         */
        Point3D startPoint = _position.add(vectorsOfThePlane.get(0).normalize().scale(-LengthOfTheSide / 2))
                .add(vectorsOfThePlane.get(1).normalize().scale(-LengthOfTheSide / 2));


        /**
         * A loop that runs as the number of vectors and in each of its runs it brings a vector around the lamp
         */
        for (double i = 0; i < LengthOfTheSide; i += LengthOfTheSide / divided) {
            for (double j = 0; j < LengthOfTheSide; j += LengthOfTheSide / divided) {
                v0 = vectorsOfThePlane.get(0).normalize().scale(randomDoubleBetweenTwoNumbers(i, i + LengthOfTheSide / divided));
                v1 = vectorsOfThePlane.get(1).normalize().scale(randomDoubleBetweenTwoNumbers(j, j + LengthOfTheSide / divided));
                Vector l = p.subtract(startPoint.add(v0).add(v1)).normalize();
                vectors.add(l);
            }

        }
        return vectors;
    }

    /**
     * function that calculate vectors of the plane
     *
     * @param plane- plane
     * @return vectors of the plane
     */
    public List<Vector> findVectorsOfPlane(Plane plane) {
        List<Vector> vectors = new ArrayList<>();
        double d = -(plane.getNormal().getHead().getX().getCoord() * plane.getQ0().getX().getCoord() + plane.getNormal().getHead().getY().getCoord() * plane.getQ0().getY().getCoord() + plane.getNormal().getHead().getZ().getCoord() * plane.getQ0().getZ().getCoord());
        int amount = 0;
        //we calculate a point on the plane and then we create a vector with the point
        if (plane.getNormal().getHead().getX().getCoord() != 0) {
            double x1 = (d / plane.getNormal().getHead().getX().getCoord());
            Vector l1 = (new Point3D(x1, 0, 0)).subtract(plane.getQ0());
            vectors.add(l1);
            amount++;
        }
        //we calculate a point on the plane and then we create a vector with the point

        if (plane.getNormal().getHead().getY().getCoord() != 0) {
            double y2 = (d / plane.getNormal().getHead().getY().getCoord());
            Vector l2 = (new Point3D(0, y2, 0)).subtract(plane.getQ0());
            vectors.add(l2);
            amount++;
        }
        //we calculate a point on the plane and then we create a vector with the point

        if (plane.getNormal().getHead().getZ().getCoord() != 0 && amount < 2) {
            double z3 = (d / plane.getNormal().getHead().getY().getCoord());
            Vector l3 = (new Point3D(0, 0, z3)).subtract(plane.getQ0());
            vectors.add(l3);
            amount++;
        }
        return vectors;
    }

    /**
     * A function that calculates a random number in a particular field
     *
     * @param min-minimum
     * @param max-maximum
     * @return random number in particular field
     */
    public double randomDoubleBetweenTwoNumbers(double min, double max) {
        double rand = min + (max - min) * new Random().nextDouble();
        //We want it to not include these numbers

        if (rand == min || rand == max) {
            return randomDoubleBetweenTwoNumbers(min, max);
        }
        return rand;
    }
}