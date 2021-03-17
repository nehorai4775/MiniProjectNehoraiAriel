package primitives;

public class Point3D {
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;
    private static final Point3D ZERO = new Point3D(0,0,0);
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

    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }
    public double distancrSquared(Point3D p2) {
        double x=p2._x._coord- _x._coord;
        double y=p2._y._coord- _y._coord;
        double z=p2._z._coord- _z._coord;
        return (x+x+y+y+z+z);
    }
    public double distancr(Point3D p2) {
        return Math.sqrt(distancrSquared(p2));
    }
    public Point3D add(Vector v0) {
        Point3D addPoint=new Point3D(_x._coord+v0.head._x._coord,_y._coord+v0.head._y._coord,_z._coord+v0.head._z._coord);
        return addPoint;
    }
    public Vector subtract(Point3D p0) {
        Vector subtractVector =new Vector(new Point3D(p0._x._coord- _x._coord,p0._y._coord- _y._coord,p0._z._coord- _z._coord));
        return subtractVector;
    }

    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public Coordinate getZ() {
        return _z;
    }
}
