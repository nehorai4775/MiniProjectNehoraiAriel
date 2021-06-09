package renderer;

import elements.*;
import primitives.*;

import java.util.MissingResourceException;

public class Render {
    ImageWriter _imageWriter;
    Camera _camera;
    RayTracerBase _rayTracer;
//setters

    /**
     * setter
     * @param imageWriter
     * @return this
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    /**
     * setter
     * @param camera
     * @return this
     */
    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;

    }

    /**
     * setter
     * @param rayTracer
     * @return this
     */
    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracer = rayTracer;
        return this;
    }

    /**
     * Performs a loop that goes through all the pixels of the ViewPlane,
      for each pixel that builds a beam and for each beam we get a color from the horn comb.
      Name the color in the appropriate pixel of the image maker (writePixel)
     */
    public void renderImage(boolean softShadow){
        if(_camera==null)//Failure to enter data throws error
            throw new MissingResourceException("there isn't have a value in camera","camera",null);
        if(_imageWriter==null)
            throw new MissingResourceException("there isn't have a value in imageWriter","imageWriter",null);
        for(int i=0;i<_imageWriter.getNx();++i)//For each pixel we calculate the color it should be painted
            for (int j=0;j<_imageWriter.getNy();++j){
                Ray ray=_camera.constructRayThroughPixel(_imageWriter.getNx(),_imageWriter.getNy(),i,j);
                Color color= _rayTracer.traceRay(ray,softShadow);
                _imageWriter.writePixel(i,j,color);
            }
    }

    /**
     *Creates a grid of lines, initially the
      bodhithe method that in the field of the image manufacturer
      entered a non-empty value
      (and in case of lack of throwing an appropriate exception,
      for example MissingResourcesException)
     * @param interval
     * @param color
     */
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

    /**
     * The method first checks that in the field of the image maker a
       non-empty value was entered
      (and in case of lack throws an appropriate exception,
      for example MissingResourcesException) and then maps (delegation!)
      The appropriate method of the image maker.
     */
    public void writeToImage(){
        if(_imageWriter==null)//Failure to enter data throws error
            throw new MissingResourceException("there isn't have a value in imageWriter","imageWriter",null);
        _imageWriter.writeToImage();
    }
}
