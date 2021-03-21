package primitives;

import java.util.Objects;

//Vector class
public class Vector {
   protected Point3D head;

   //a function that returns the head
    public Point3D getHead() {

        return head;
    }
//a contractor that gets Point3D as parameter
    public Vector(Point3D _head) {
        if(_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        this.head = _head;
    }
    //a constructor that gets three coordinates as parameters
    public Vector(Coordinate x, Coordinate y, Coordinate z) {

        Point3D _head=new Point3D(x,y,z);
        if(_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException();

        head=_head;
    }
    //a constructor that gets three parameters of the double type
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }
//a function that adds two vectors
    public Vector add(Vector v0) {
        Vector addVector=new Vector(new Point3D(head._x._coord+v0.head._x._coord,head._y._coord+v0.head._y._coord,head._z._coord+v0.head._z._coord));
        return addVector;
    }
    //a function that subtracts two vectors
    public Vector subtract(Vector v0) {
        Vector subtractVector =new Vector(new Point3D(v0.head._x._coord- head._x._coord,v0.head._y._coord- head._y._coord,v0.head._z._coord- head._z._coord));
        return subtractVector;
    }
    //a function that multiplies a vector by a scalar
    public Vector scale(double num) {
        Vector scaleVector =new Vector(new Point3D(head._x._coord*num,head._y._coord*num,head._z._coord*num));
        return scaleVector;
    }
    //a function that performs scalar multiplication
    public Vector crossProduct (Vector v0) {

        Vector crossProductVector =new Vector(new Point3D(head._y._coord*v0.head._z._coord-head._z._coord*v0.head._y._coord,head._z._coord*v0.head._x._coord-head._x._coord*v0.head._z._coord,head._x._coord*v0.head._y._coord-head._y._coord*v0.head._x._coord));
        if(crossProductVector.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        return crossProductVector;
    }
    //a function that performs scalar multiplication
    public double dotProduct (Vector v0) {
        return (head._x._coord*v0.head._x._coord+head._y._coord*v0.head._y._coord+head._z._coord*v0.head._z._coord);
    }
    //a function that calculates the length of a vector squared
    public double lengthSquared  () {
return (head._x._coord*head._x._coord+head._y._coord*head._y._coord+head._z._coord*head._z._coord);
    }
    ////a function that calculates the length of a vector
    public double length () {
        return Math.sqrt(lengthSquared());
    }
//a function that normalizes the vector
    public Vector normalize  () {
        double divided=length();
        Point3D newHead=new Point3D(head._x._coord/divided,head._y._coord/divided,head._z._coord/divided);
        head=newHead;
        return this;
    }
    /*A normalization function that returns a new normalized vector
     in the same direction as the original vector
     */
    public Vector normalized   () {
        Vector v = normalize();
        return new Vector(v.head);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }


}
