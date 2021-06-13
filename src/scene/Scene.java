package scene;

import elements.*;
import geometries.*;
import primitives.*;

import java.util.*;

/**
 * a class for scene
 */
public class Scene {
    private final String _name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<LightSource>();

    /**
     * setter
     *
     * @param lights - list of light
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * constructor
     *
     * @param name-name
     */
    public Scene(String name) {
        _name = name;
    }

    /**
     * setter
     *
     * @param background- color of background
     * @return this
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter
     *
     * @param ambientLight-ambient light
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter
     *
     * @param geometries -geometries
     * @return this
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
