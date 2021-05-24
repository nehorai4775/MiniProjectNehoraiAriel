package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * blablablablabla
 */
public class Camera {
    private final Point3D _p0;
    private final Vector _vTo;
    private final Vector _vUp;
    private final Vector _vRight;

    private double _height;
    private double _width;
    private double _distance;

    /**
     *constructor
     * @param p0
     * @param vTo
     * @param vUp
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("the vectors should be orthogonal");
        }

        _p0 = p0;

        _vTo = vTo.normalized();
        _vUp = vUp.normalized();

        _vRight = _vTo.crossProduct(_vUp);

    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getvTo() {
        return _vTo;
    }

    public Vector getvUp() {
        return _vUp;
    }

    public Vector getvRight() {
        return _vRight;
    }

    public double getHeight() {
        return _height;
    }

    public double getWidth() {
        return _width;
    }

    public double getDistance() {
        return _distance;
    }
    //setters using method chaining

    /**
     *
     * @param width
     * @param height
     * @return the instance of Camera.
     */
    public Camera setViewPlaneSize(double width, double height){
        _width=width;
        _height=height;
        return this;
    }

    /**
     *
     * @param distance
     * @return the instance of Camera.
     */
    public Camera setDistance(double distance){
        _distance=distance;
        return this;
    }
    //according to the presentation
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D pc = _p0.add(_vTo.scale(_distance));
        double Ry = _height / nY;
        double Rx = _width / nX;
        double xj = Rx * (j - (double)(nX - 1) / 2);
        double yi = -Ry*(i - (double)(nY - 1) / 2);

        if (isZero(xj) && isZero(yi)) {
            return new Ray(_p0, pc.subtract(_p0));
        } else if (isZero(xj)) {
            return new Ray(_p0, pc.add(_vUp.scale(-Ry *  (i - (double)(nY - 1) / 2))).subtract(_p0));
        } else if (isZero(yi)) {
            return new Ray(_p0, pc.add(_vRight.scale(Rx *  (j - (double)(nX - 1) / 2))).subtract(_p0));
        } else {
            return new Ray(_p0, pc.add(_vRight.scale(Rx *  (j - (double)(nX - 1) / 2))).add(_vUp.scale(-Ry *  (i - (double)(nY - 1) / 2))).subtract(_p0));

        }
    }


}

