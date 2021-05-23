package elements;

import primitives.Color;

public class AmbientLight {
//intensity=Fill lighting intensity field
    private Color _intensity;
//constructor
    public AmbientLight(Color Ia, double Ka){
    _intensity=Ia.scale(Ka);
    }
    //default constructor, black is the default color
    AmbientLight(){
        _intensity=Color.BLACK;
    }

    public Color getIntensity() {
        return _intensity;
    }
}
