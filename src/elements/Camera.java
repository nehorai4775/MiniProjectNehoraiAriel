package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * a class for camera
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
     * constructor
     *
     * @param p0-point   p0
     * @param vTo-vector To
     * @param vUp-vector up
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
     * @param width-width
     * @param height-high
     * @return the instance of Camera.
     */
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * @param distance-distance
     * @return the instance of Camera.
     */
    public Camera setDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * The function creates a ray from the camera to the pixel
     *
     * @param nX-number of column
     * @param nY-number of row
     * @param j-column of the pixel
     * @param i-row of the pixel
     * @return -ray from the camera
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        //point on the view plane
        Point3D pc = _p0.add(_vTo.scale(_distance));
        double Ry = _height / nY;
        double Rx = _width / nX;
        //xj is the x from the pc
        double xj = Rx * (j - (double) (nX - 1) / 2);
        //yi- is the y from the pc
        double yi = -Ry * (i - (double) (nY - 1) / 2);
//return ray from the camera to thr view plane
        if (isZero(xj) && isZero(yi)) {
            return new Ray(_p0, pc.subtract(_p0));
        } else if (isZero(xj)) {
            return new Ray(_p0, pc.add(_vUp.scale(-Ry * (i - (double) (nY - 1) / 2))).subtract(_p0));
        } else if (isZero(yi)) {
            return new Ray(_p0, pc.add(_vRight.scale(Rx * (j - (double) (nX - 1) / 2))).subtract(_p0));
        } else {
            return new Ray(_p0, pc.add(_vRight.scale(Rx * (j - (double) (nX - 1) / 2))).add(_vUp.scale(-Ry * (i - (double) (nY - 1) / 2))).subtract(_p0));

        }
    }


}

