package elements;
import geometries.Plane;
import geometries.Polygon;
import org.junit.jupiter.api.Test;


import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;


public class MyTest {
//    @Test
//    public void mytest(){
//         Scene scene = new Scene("Test scene");
//        Camera camera = new Camera(new Point3D(8, 0, 2), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //(7, 0, 1)
//                .setViewPlaneSize(1, 1).setDistance(1);
//        scene.geometries.add(
//                new Sphere(1,new Point3D(-1,0,1))
//                        .setEmission(new Color(0,0,200)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40)),
//                new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,0))
//                        .setEmission(new Color(40,40,40)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)),
//                new Triangle(new Point3D(-1,0,3),new Point3D(1,-2,0), new Point3D(-3,-2,0))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
//                new Triangle(new Point3D(-1,0,3),new Point3D(1,2,0), new Point3D(-3,2,0))
//                       .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
//                new Triangle(new Point3D(-1,0,3),new Point3D(-3,-2,0), new Point3D(-3,2,0))
//                      .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
//                new Triangle(new Point3D(-1,0,3),new Point3D(1,-2,0), new Point3D(1,2,0))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
//
//
//                new Sphere(0.5,new Point3D(0,-1,0.5))
//                        .setEmission(new Color(0,200,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.5)),
//
//                new Sphere(0.5,new Point3D(0,1,0.5))
//                        .setEmission(new Color(200,0,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.3)),
//
//
//                new Polygon(new Point3D(-2.5,2.5,0),new Point3D(-2.5,3.5,0),new Point3D(-1.5,3.5,0),new Point3D(-1.5,2.5,0))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
//
//                new Polygon(new Point3D(-1.5,2.5,0),new Point3D(-1.5,3.5,0),new Point3D(-1.5,3.5,4),new Point3D(-1.5,2.5,4))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//
//                new Polygon(new Point3D(-2.5,2.5,0),new Point3D(-1.5,2.5,0),new Point3D(-1.5,2.5,4),new Point3D(-2.5,2.5,4))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//
//               new Polygon(new Point3D(-1.5,3.5,0),new Point3D(-2.5,3.5,0),new Point3D(-2.5,3.5,4),new Point3D(-1.5,3.5,4))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//
//                new Polygon(new Point3D(-2.5,3.5,0),new Point3D(-2.5,2.5,0),new Point3D(-2.5,2.5,4),new Point3D(-2.5,3.5,4))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),// .setEmission(new Color(40,40,40)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)
//                new Polygon(new Point3D(-2.5,2.5,4),new Point3D(-1.5,2.5,4),new Point3D(-1.5,3.5,4),new Point3D(-2.5,3.5,4))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//                new Sphere(1,new Point3D(-2,3,5))
//                        .setEmission(new Color(0,200,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.5))
//
//
//
//
//
//
//
//
//
//                );
//
//
//
//
//
//
//        scene.lights.add(new PointLight(new Color(255,255,255),new Point3D(-1,-2,2),1.4,0.005,0.0005));
//        scene.lights.add(new DirectionalLight(new Color(200,0,0),new Vector(-1,0,3)));
//        scene.lights.add(new SpotLight(new Color(500,500,0), new Point3D(-2,3,5), new Vector(1,-10,0)) //
//              .setKl(0.00001).setKq(0.00000001));
//
//        Render render = new Render() //
//                .setImageWriter(new ImageWriter("MyTestsWithSoftShadow", 500, 500)) //
//                .setCamera(camera) //
//                .setRayTracer(new RayTracerBasic(scene));
//        render.renderImage(false);
//        render.writeToImage();
//
//    }
//    @Test
//    public void mytest2(){
//        Scene scene = new Scene("Test scene");
//        Camera camera = new Camera(new Point3D(7, 0, 1), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
//                .setViewPlaneSize(1, 1).setDistance(1);
//        scene.geometries.add(
//                new Sphere(1,new Point3D(-1,0,1))
//                        .setEmission(new Color(0,0,200)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40)),
//                new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,0))
//                        .setEmission(new Color(40,40,40)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)),
//                new Triangle(new Point3D(-1,0,3),new Point3D(1,-2,0), new Point3D(-3,-2,0))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
//                new Triangle(new Point3D(-1,0,3),new Point3D(1,2,0), new Point3D(-3,2,0))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
//                new Triangle(new Point3D(-1,0,3),new Point3D(-3,-2,0), new Point3D(-3,2,0))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
//                new Triangle(new Point3D(-1,0,3),new Point3D(1,-2,0), new Point3D(1,2,0))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKt(0.5)),
//
//
//                new Sphere(0.5,new Point3D(0,-1,0.5))
//                        .setEmission(new Color(0,200,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.5)),
//
//                new Sphere(0.5,new Point3D(0,1,0.5))
//                        .setEmission(new Color(200,0,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.3)),
////        new Polygon(new Point3D(-2.5,2.5,0),new Point3D(-2.5,3.5,0),new Point3D(-1.5,3.5,0),new Point3D(-1.5,2.5,0))
////                .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//                new Polygon(new Point3D(-1.5,2.5,0),new Point3D(-1.5,3.5,0),new Point3D(-1.5,3.5,3),new Point3D(-1.5,2.5,3))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//
//                new Polygon(new Point3D(-2.5,2.5,0),new Point3D(-1.5,2.5,0),new Point3D(-1.5,2.5,3),new Point3D(-2.5,2.5,3))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//
//                new Polygon(new Point3D(-1.5,3.5,0),new Point3D(-2.5,3.5,0),new Point3D(-2.5,3.5,3),new Point3D(-1.5,3.5,3))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//
//                new Polygon(new Point3D(-2.5,3.5,0),new Point3D(-2.5,2.5,0),new Point3D(-2.5,2.5,3),new Point3D(-2.5,3.5,3))
//                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),// .setEmission(new Color(40,40,40)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)
////                new Polygon(new Point3D(-2.5,2.5,3),new Point3D(-1.5,2.5,3),new Point3D(-1.5,3.5,3),new Point3D(-2.5,3.5,3))
////                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)).setEmission(new Color(java.awt.Color.red)),
//
//                new Sphere(1,new Point3D(-2,3,4))
//                        .setEmission(new Color(0,200,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.5))
//
//        );
//
//        scene.lights.add(new PointLight(new Color(255,255,255),new Point3D(3,3,3),1.4,0.005,0.0005));
//        scene.lights.add(new DirectionalLight(new Color(200,0,0),new Vector(-1,0,3)));
//        scene.lights.add(new SpotLight(new Color(500,500,0), new Point3D(-2,3,5), new Vector(1,-10,0)) //
//                .setKl(0.00001).setKq(0.00000001));
//        Render render = new Render() //
//                .setImageWriter(new ImageWriter("MyTestsWithoutSoftShadow", 500, 500)) //
//                .setCamera(camera) //
//                .setRayTracer(new RayTracerBasic(scene));
//        render.renderImage(false);
//
//        render.writeToImage();
//    }
@Test
public void mytest(){
    Scene scene = new Scene("Test scene");
    Camera camera = new Camera(new Point3D(7, 0, 1), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
            .setViewPlaneSize(1, 1).setDistance(1);
    scene.geometries.add(
            new Sphere(1,new Point3D(-1,0,1))
                    .setEmission(new Color(0,0,200)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40)),
            new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,0))
                    .setEmission(new Color(40,40,40)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)),
            new Triangle(new Point3D(-1,0,3),new Point3D(1,-2,0), new Point3D(-3,-2,0))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
            new Triangle(new Point3D(-1,0,3),new Point3D(1,2,0), new Point3D(-3,2,0))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
            new Triangle(new Point3D(-1,0,3),new Point3D(-3,-2,0), new Point3D(-3,2,0))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
            new Triangle(new Point3D(-1,0,3),new Point3D(1,-2,0), new Point3D(1,2,0))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKt(0.5)),


            new Sphere(0.5,new Point3D(0,-1,0.5))
                    .setEmission(new Color(0,200,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.5)),

            new Sphere(0.5,new Point3D(0,1,0.5))
                    .setEmission(new Color(200,0,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.3))


    );
    scene.lights.add(new PointLight(new Color(255,255,255),new Point3D(3,3,3),1.4,0.005,0.0005));

    Render render = new Render() //
            .setImageWriter(new ImageWriter("MyTestsWithSoftShadow", 500, 500)) //
            .setCamera(camera) //
            .setRayTracer(new RayTracerBasic(scene));
    render.renderImage(true);

    render.writeToImage();

}
    @Test
    public void mytest2(){
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point3D(7, 0, 1), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(1, 1).setDistance(1);
        scene.geometries.add(
                new Sphere(1,new Point3D(-1,0,1))
                        .setEmission(new Color(0,0,200)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40)),
                new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,0))
                        .setEmission(new Color(40,40,40)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)),
                new Triangle(new Point3D(-1,0,3),new Point3D(1,-2,0), new Point3D(-3,-2,0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point3D(-1,0,3),new Point3D(1,2,0), new Point3D(-3,2,0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point3D(-1,0,3),new Point3D(-3,-2,0), new Point3D(-3,2,0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point3D(-1,0,3),new Point3D(1,-2,0), new Point3D(1,2,0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKt(0.5)),


                new Sphere(0.5,new Point3D(0,-1,0.5))
                        .setEmission(new Color(0,200,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.5)),

                new Sphere(0.5,new Point3D(0,1,0.5))
                        .setEmission(new Color(200,0,0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(0.3))


        );
        scene.lights.add(new PointLight(new Color(255,255,255),new Point3D(3,3,3),1.4,0.005,0.0005));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("MyTestsWithoutSoftShadow", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage(false);

        render.writeToImage();
    }
}
