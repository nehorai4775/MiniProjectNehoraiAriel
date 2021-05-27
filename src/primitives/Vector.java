package primitives;

import java.util.Objects;

//Vector class
public class Vector {
   protected Point3D head;

    /**
     * getter
     * @return head
     */
   //a function that returns the head
    public Point3D getHead() {
        return head;
    }

    /**
     * contractor
     * @param _head
     */
    public Vector(Point3D _head) {
        if(_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        this.head = _head;
    }

    /**
     * contractor
     * @param x
     * @param y
     * @param z
     */
    //a constructor that gets three coordinates as parameters
    public Vector(Coordinate x, Coordinate y, Coordinate z) {

        Point3D _head=new Point3D(x,y,z);
        if(_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException();

        head=_head;
    }

    /**
     * constructor
     * @param x
     * @param y
     * @param z
     */
    //a constructor that gets three parameters of the double type
    public Vector(double x, double y, double z) {
        this(new Point3D(x, y, z));
    }

    /**
     * add two vectors
     * @param v0
     * @return addVector (the result)
     */
//a function that adds two vectors
    public Vector add(Vector v0) {
        Vector addVector=new Vector(new Point3D(head._x._coord+v0.head._x._coord,head._y._coord+v0.head._y._coord,head._z._coord+v0.head._z._coord));
        return addVector;
    }

    /**
     * subtract between to vectors
     * @param v0
     * @return subtractVector(the result)
     */
    //a function that subtracts two vectors
    public Vector subtract(Vector v0) {
        Vector subtractVector =new Vector(new Point3D(v0.head._x._coord- head._x._coord,v0.head._y._coord- head._y._coord,v0.head._z._coord- head._z._coord));
        return subtractVector;
    }

    /**
     * multiplies a vector by a scalar
     * @param num
     * @return scaleVector(the result)
     */
    //a function that multiplies a vector by a scalar
    public Vector scale(double num) {
        Vector scaleVector =new Vector(new Point3D(head._x._coord*num,head._y._coord*num,head._z._coord*num));
        return scaleVector;
    }

    /**
     *Vector product
     * @param v0
     * @return crossProductVector
     */
    //a function that performs scalar multiplication
    public Vector crossProduct (Vector v0) {

        Vector crossProductVector =new Vector(new Point3D(head._y._coord*v0.head._z._coord-head._z._coord*v0.head._y._coord,head._z._coord*v0.head._x._coord-head._x._coord*v0.head._z._coord,head._x._coord*v0.head._y._coord-head._y._coord*v0.head._x._coord));
        if(crossProductVector.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        return crossProductVector;
    }

    /**
     * a function that performs scalar multiplication
     * @param v0
     * @return (head*v0)
     */

    public double dotProduct (Vector v0) {
        return (head._x._coord*v0.head._x._coord+head._y._coord*v0.head._y._coord+head._z._coord*v0.head._z._coord);
    }
    //a function that calculates the length of a vector squared
    public double lengthSquared  () {
return (head._x._coord*head._x._coord+head._y._coord*head._y._coord+head._z._coord*head._z._coord);
    }

    /**
     * a function that calculates the length of a vector
     * @return the length of a vector
     */
    ////
    public double length () {
        return Math.sqrt(lengthSquared());
    }

    /**
     * a function that normalizes the vector
     * @return  the normalizes vector
     */

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
