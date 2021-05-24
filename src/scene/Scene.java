package scene;

import elements.*;
import geometries.*;
import primitives.*;

import java.util.*;

public class Scene {
    public String _name;
    public Color _background;
    public AmbientLight _ambientLight;
    public Geometries _geometries;
    public List<LightSource> lights=new LinkedList<LightSource>();

    /**
     * setter
     * @param lights - list of light
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * constructor
     * @param name
     */
    public Scene(String name) {
        _name=name;
        _background=Color.BLACK;
        _geometries= new Geometries();
        _ambientLight=new AmbientLight();
        lights=new LinkedList<LightSource>();

    }

    /**
     * setter
     * @param background- color of background
     * @return this
     */
    public Scene setBackground(Color background) {
        this._background = background;
        return this;
    }

    /**
     * setter
     * @param ambientLight
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this._ambientLight = ambientLight;
        return this;
    }

    /**
     * setter
     * @param geometries
     * @return this
     */
    public Scene setGeometries(Geometries geometries) {
        this._geometries = geometries;
        return this;
    }
}
