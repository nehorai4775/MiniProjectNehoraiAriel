package elements;

import primitives.Color;

/**
 * an abstract class for light
 */
public abstract class Light {
    private Color intensity;

    /**
     * constructor
     * @param intensity-intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
