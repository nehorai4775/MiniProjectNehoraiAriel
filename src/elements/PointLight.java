package elements;

import geometries.Plane;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointLight extends Light implements LightSource {
    private Point3D _position;
    private double _kc, _kl, _kq;
    private int LengthOfTheSide = 1;
    /**
     * rays that get out from a point
     */
    public static final int rays = 16;


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
     * @param kc kl,kq -Discount coefficients
     * @return this
     */
    public PointLight setKc(double kc) {
        _kc = kc;
        return this;
    }

    public PointLight setKl(double kl) {
        _kl = kl;
        return this;
    }

    public PointLight setKq(double kq) {
        _kq = kq;
        return this;
    }

    /**
     * getter
     *
     * @param point
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
 * A variable that tells how many divide each side
 */
        double divided = Math.sqrt(rays);
        Random random = new Random();
        double rand;
        int min = 1;
        int max = 2;
/**
 * plane of the light
 */
        Plane plane = new Plane(_position, getL(p));
        /**
         * vectors of the plane
         */
        List<Vector> vectorsOfThePlane = findVectorsOfPlane(plane);
        /**
         * start point
         */
        Point3D startPoint = _position.add(vectorsOfThePlane.get(0).normalize().scale(-2)).add(vectorsOfThePlane.get(1).normalize().scale(-2));

        /**
         * A loop that runs as the number of vectors and in each of its runs it brings a vector around the lamp
         */
        for (double i = 0; i < LengthOfTheSide; i += LengthOfTheSide / divided) {
            for (double j = 0; j < LengthOfTheSide; j += LengthOfTheSide / divided) {
                Vector l = p.subtract(startPoint.add(vectorsOfThePlane.get(0).normalize().scale(randomDouble(i, i + LengthOfTheSide / divided))).add(vectorsOfThePlane.get(1).normalize().scale(randomDouble(j, j + LengthOfTheSide / divided))));
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
            Vector l1 = plane.getQ0().subtract(new Point3D(x1, 0, 0));
            vectors.add(l1);
            amount++;
        }
        //we calculate a point on the plane and then we create a vector with the point

        if (plane.getNormal().getHead().getY().getCoord() != 0) {
            double y2 = (d / plane.getNormal().getHead().getY().getCoord());
            Vector l2 = plane.getQ0().subtract(new Point3D(0, y2, 0));
            vectors.add(l2);
            amount++;
        }
        //we calculate a point on the plane and then we create a vector with the point

        if (plane.getNormal().getHead().getZ().getCoord() != 0 && amount < 2) {
            double z3 = (d / plane.getNormal().getHead().getY().getCoord());
            Vector l3 = plane.getQ0().subtract(new Point3D(0, 0, z3));
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
    public double randomDouble(double min, double max) {
        return min + (max - min) * new Random().nextDouble();
    }
}