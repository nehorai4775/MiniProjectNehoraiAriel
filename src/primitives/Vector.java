package primitives;

public class Vector {
   protected Point3D head;

    public Point3D getHead() {
        return head;
    }

    public Vector(Point3D _head) {
        this.head = _head;
    }
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D _head=new Point3D(x,y,z);

        head=_head;
    }
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    public Vector add(Vector v0) {
        Vector addVector=new Vector(new Point3D(head._x._coord+v0.head._x._coord,head._y._coord+v0.head._y._coord,head._z._coord+v0.head._z._coord));
        return addVector;
    }
    public Vector subtract(Vector v0) {
        Vector subtractVector =new Vector(new Point3D(v0.head._x._coord- head._x._coord,v0.head._y._coord- head._y._coord,v0.head._z._coord- head._z._coord));
        return subtractVector;
    }
    public Vector scale(double num) {
        Vector scaleVector =new Vector(new Point3D(head._x._coord*num,head._y._coord*num,head._z._coord*num));
        return scaleVector;
    }
    public Vector crossProduct (Vector v0) {
        Vector crossProductVector =new Vector(new Point3D(head._x._coord*v0.head._z._coord-head._z._coord*v0.head._y._coord,head._z._coord*v0.head._x._coord-head._x._coord*v0.head._z._coord,head._x._coord*v0.head._y._coord-head._y._coord*v0.head._x._coord));
        return crossProductVector;
    }
    public double dotProduct (Vector v0) {
        return (head._x._coord*v0.head._x._coord+head._y._coord*v0.head._y._coord+head._z._coord*v0.head._z._coord);
    }
    public double lengthSquared  () {
return (head._x._coord*head._x._coord+head._y._coord*head._y._coord+head._z._coord*head._z._coord);
    }
    public double length () {
return Math.sqrt(lengthSquared());
    }
    public Vector normalize  () {
        double divided=length();
Point3D newHead=new Point3D(head._x._coord/divided,head._y._coord/divided,head._z._coord/divided);
head=newHead;
return this;
    }
    public Vector normalized   () {
return normalize();
    }

}
