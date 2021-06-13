package primitives;

/**
 * Point3D class
 */
public class Point3D {
    public static final Point3D ZERO = new Point3D(0, 0, 0);
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;


    /**
     * a constructor that gets three coordinates as parameters
     *
     * @param x-Coordinate x
     * @param y-Coordinate y
     * @param z-Coordinate z
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = x;
        _y = y;
        _z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D poiint3D = (Point3D) o;
        return _x.equals(poiint3D._x) && _y.equals(poiint3D._y) && _z.equals(poiint3D._z);
    }

    @Override
    public String toString() {
        return "(" +
                "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z +
                ')';
    }


    /**
     * a constructor that gets three parameters of the double type
     *
     * @param x-coordinate x
     * @param y-coordinate y
     * @param z-           coordinate z
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }


    /**
     * a function that returns the distance between two points squared
     *
     * @param p2-point
     * @return- distance squared
     */
    public double distancrSquared(Point3D p2) {
        double x = (p2._x._coord - _x._coord) * (p2._x._coord - _x._coord);
        double y = (p2._y._coord - _y._coord) * (p2._y._coord - _y._coord);
        double z = (p2._z._coord - _z._coord) * (p2._z._coord - _z._coord);
        return (x + y + z);
    }


    /**
     * a function that returns the distance between two points
     *
     * @param p2-point
     * @return -distance
     */
    public double distance(Point3D p2) {
        return Math.sqrt(distancrSquared(p2));
    }


    /**
     * A function that adds a vector to a point, and returns a new point
     *
     * @param v0-vector
     * @return- point
     */
    public Point3D add(Vector v0) {
        Point3D addPoint = new Point3D(_x._coord + v0.head._x._coord, _y._coord + v0.head._y._coord, _z._coord + v0.head._z._coord);
        return addPoint;
    }


    /**
     * a function that subtracts between two points and returns a new vector that is the result
     *
     * @param p0-point
     * @return- vector
     */
    public Vector subtract(Point3D p0) {
        Vector subtractVector = new Vector(new Point3D(_x._coord - p0._x._coord, _y._coord - p0._y._coord, _z._coord - p0._z._coord));
        return subtractVector;
    }

    /**
     * getter
     *
     * @return x
     */
    public Coordinate getX() {
        return _x;
    }

    /**
     * getter
     *
     * @return y
     */
    public Coordinate getY() {
        return _y;
    }

    /**
     * getter
     *
     * @return z
     */
    public Coordinate getZ() {
        return _z;
    }
}
