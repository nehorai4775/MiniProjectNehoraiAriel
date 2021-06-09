package elements;

import geometries.Plane;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;
import java.util.Random;

public class PointLight extends Light implements LightSource{
    private Point3D _position;
    private double _kc, _kl, _kq;
    private int LengthOfTheSide;
    /**
     * rays that get out from a point
     */
    private static final int rays = 16;


    /**
     * constructor
     * @param intensity -intensity of the light
     * @param position-position
     * @param kc -Discount coefficients
     * @param kl -Discount coefficients
     * @param kq-Discount coefficients
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
     * @param p-point
     * @return intensity
     */
    @Override
    public Color getIntensity(Point3D p) {

        double distance=_position.distance(p);
        return super.getIntensity().scale(1d/(_kc+_kl*distance+_kq*distance*distance));
    }

    /**
     * acoording to the presentation
     * @param p-point
     * @return vector L
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalize();
    }

    /**
     * setter
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
     * @param point
     * @return distance
     */
    public double getDistance(Point3D point){
       return _position.distance(point);
    }

    @Override
    public List<Vector> getL2(Point3D p) {
List<Vector> vectors =null;

double divided=Math.sqrt(rays);
Point3D StartPoint=_position.add(new Vector(-divided/2,-divided/2,-divided/2));
Random random = new Random();
double rand;
int min = 1;
int max = 2;
Plane plane=new Plane(_position,getL(p));
for (int i=0;i<divided;++i){
    rand = min + (max-min)*random.nextInt();
    Vector l1=p.subtract(StartPoint.add(new Vector(0,0,(LengthOfTheSide/divided)*i)));
    Vector l2=p.subtract(StartPoint.add(new Vector(0,(LengthOfTheSide/divided)*i,0)));
    Vector l3=p.subtract(StartPoint.add(new Vector((LengthOfTheSide/divided)*i,0,0)));

    vectors.addAll(List.of(l1,l2,l3));
}
for(int i=0;i<16;++i){
    for(int j=0;j<16;++j){
        Vector l1=p.subtract(plane.)
    }

}
return vectors;
    }
}
