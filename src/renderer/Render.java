package renderer;

import elements.*;
import primitives.*;

import java.util.List;
import java.util.MissingResourceException;

/**
 * a class for render
 */
public class Render {
    ImageWriter _imageWriter;
    Camera _camera;
    RayTracerBase _rayTracer;

//setters

    /**
     * setter
     *
     * @param imageWriter- the image writer
     * @return the rander by model builder
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    /**
     * setter
     *
     * @param camera-the camera
     * @return  the rander by model builder
     */
    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;

    }

    /**
     * setter
     *
     * @param rayTracer -the ray tracer
     * @return  the rander by model builder
     */
    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracer = rayTracer;
        return this;
    }

    /**
     * Performs a loop that goes through all the pixels of the ViewPlane,
     * for each pixel that builds a beam and for each beam we get a color from the horn comb.
     * Name the color in the appropriate pixel of the image maker (writePixel)
     */
    public void renderImage(boolean softShadow) {
        if (_camera == null)//Failure to enter data throws error
            throw new MissingResourceException("there isn't have a value in camera", "camera", null);
        if (_imageWriter == null)
            throw new MissingResourceException("there isn't have a value in imageWriter", "imageWriter", null);
        for (int i = 0; i < _imageWriter.getNx(); ++i)//For each pixel we calculate the color it should be painted
            for (int j = 0; j < _imageWriter.getNy(); ++j) {
                Ray ray = _camera.constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), i, j);
                Color color = _rayTracer.traceRay(ray, softShadow);
                _imageWriter.writePixel(i, j, color);
            }
    }


    /**
     * Creates a grid of lines, initially the
     * bodhithe method that in the field of the image manufacturer
     * entered a non-empty value
     * (and in case of lack of throwing an appropriate exception,
     * for example MissingResourcesException)
     *
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color) {
        if (_imageWriter == null)//Failure to enter data throws error
            throw new MissingResourceException("there isn't have a value in imageWriter", "imageWriter", null);

        for (int i = 0; i < _imageWriter.getNx(); ++i)//Paint the grid
            for (int j = 0; j < _imageWriter.getNy(); ++j) {

                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(i, j, color);
            }
        _imageWriter.writeToImage();
    }

    public Camera getCamera() {
        return _camera;
    }

    /**
     * The method first checks that in the field of the image maker a
     * non-empty value was entered
     * (and in case of lack throws an appropriate exception,
     * for example MissingResourcesException) and then maps (delegation!)
     * The appropriate method of the image maker.
     */
    public void writeToImage() {
        if (_imageWriter == null)//Failure to enter data throws error
            throw new MissingResourceException("there isn't have a value in imageWriter", "imageWriter", null);
        _imageWriter.writeToImage();
    }
    private int _threads = 3;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;

    /**
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     * @author Dan
     *
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.println(_percents);
        }

        /**
         *  Default constructor for secondary Pixel objects
         */
        public Pixel() {}

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {

            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                //if the counter is equals to the nect counter we add 1 to the percent
                if (_counter == _nextCounter) {
                    ++_percents;
                    //we promote the nect counter
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    //we return the percent
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                if (_counter == _nextCounter) {
                    ++_percents;
                    // we promote the next counter
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    //we return the percent
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            //we calculate the percent that finish
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this._print) System.out.println( _percents);//we print the percent
            if (percents >= 0)
                return true;
            if (Render.this._print) System.out.println(100);
            return false;
        }
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void renderImage2(boolean softShadow) {
        final int nX = _imageWriter.getNx();
        final int nY = _imageWriter.getNy();
        final double dist = _camera.getDistance();
        //final double width = _imageWriter.getWidth();
      //  final double height = _imageWriter.getHeight();
        final Camera camera = getCamera();

        final Pixel thePixel = new Pixel(nY, nX);

        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {//As long as there are still pixels

                    Ray ray =  camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row); //dist, width, height);
                    _imageWriter.writePixel(pixel.col, pixel.row, _rayTracer.traceRay(ray,softShadow));
                }
            });
        }

        // Start threads
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
        if (_print) System.out.println(100);
    }

    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
        if (threads != 0)
            _threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            if (cores <= 2)
                _threads = 1;
            else
                _threads = cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true;
        return this;
    }
}
