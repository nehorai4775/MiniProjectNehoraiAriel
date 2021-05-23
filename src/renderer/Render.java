package renderer;

import elements.*;
import primitives.*;

import java.util.MissingResourceException;

public class Render {
    ImageWriter _imageWriter;
    Camera _camera;
    RayTracerBasic _rayTracerBasic;
//setters
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;

    }

    public Render setRayTracer(RayTracerBasic rayTracerBasic) {
        _rayTracerBasic = rayTracerBasic;
        return this;
    }
    void renderImage(){
        if(_camera==null)//Failure to enter data throws error
            throw new MissingResourceException("there isn't have a value in camera","camera",null);
        if(_imageWriter==null)
            throw new MissingResourceException("there isn't have a value in imageWriter","imageWriter",null);
        for(int i=0;i<_imageWriter.getNx();++i)//For each pixel we calculate the color it should be painted
            for (int j=0;j<_imageWriter.getNy();++j){
                Ray ray=_camera.constructRayThroughPixel(_imageWriter.getNx(),_imageWriter.getNy(),i,j);
                Color color=_rayTracerBasic.traceRay(ray);
                _imageWriter.writePixel(i,j,color);
            }
    }
    public void printGrid(int interval, Color color){
        if(_imageWriter==null)//Failure to enter data throws error
            throw new MissingResourceException("there isn't have a value in imageWriter","imageWriter",null);
        for(int i=0;i<_imageWriter.getNx();++i)//Paint the grid
            for (int j=0;j<_imageWriter.getNy();++j){

                if(i%interval==0||j%interval==0)
                    _imageWriter.writePixel(i,j,color);
            }
        _imageWriter.writeToImage();
    }

    public void writeToImage(){
        if(_imageWriter==null)//Failure to enter data throws error
            throw new MissingResourceException("there isn't have a value in imageWriter","imageWriter",null);
        _imageWriter.writeToImage();
    }
}
