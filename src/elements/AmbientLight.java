package elements;

import primitives.Color;

/**
 * a class for ambient light
 */
public class AmbientLight extends Light {

    /**
     * constructor
     *
     * @param Ia-Light       intensity according to RGB components
     * @param Ka-Coefficient of attenuation of filler light
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * default constructor, black is the default color
     */
    public AmbientLight() {
        super(Color.BLACK);

    }


}
