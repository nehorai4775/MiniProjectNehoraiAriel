package primitives;

public class Ray {
    protected Point3D p0;
    protected Vector dir;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    public Point3D getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    // a constructor
    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        dir.normalize();
        this.dir = dir;
    }
}
