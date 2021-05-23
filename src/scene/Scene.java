package scene;

import elements.*;
import geometries.*;
import primitives.*;

public class Scene {
    public String _name;
    public Color _background;
    public AmbientLight _ambientLight;
    public Geometries _geometries;
//constructor
    public Scene(String name) {
        _name=name;
        _background=Color.BLACK;
        _geometries= new Geometries();
    }
//setters
    public Scene setBackground(Color background) {
        this._background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this._ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this._geometries = geometries;
        return this;
    }
}
