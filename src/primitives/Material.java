package primitives;

public class Material {
    public double _Kd,_Ks;
    public int _nshininess;
    /**
     * kt-Promotes transparency
     * kr-Coefficient of reflection
     */
    public double _kt,_kr;

    public Material() {
        _Kd=0;
        _Ks=0;
        _nshininess=0;
        _kr=0.0;
        _kt=0.0;
    }

    /**
     * setter
     * @param kt-Promotes transparency
     * @return this
     */
    public Material setKt(double kt) {
        _kt = kt;
        return this;
    }

    /**
     * setter
     * @param kr-Coefficient of reflection
     * @return this
     */
    public Material setKr(double kr) {
        _kr = kr;
        return this;
    }

    /**
     * setter
     * @param kd
     * @return this
     */
    public Material setKd(double kd) {
        _Kd = kd;
        return this;
    }

    /**
     * setter
     * @param ks
     * @return this
     */
    public Material setKs(double ks) {
        _Ks = ks;
        return this;

    }

    /**
     * setter
     * @param nshininess
     * @return this
     */
    public Material setNshininess(int nshininess) {
        _nshininess = nshininess;
        return this;

    }

    /**
     * getter
     * @return kd
     */
    public double getKd() {
        return _Kd;
    }

    /**
     * getter
     * @return ks
     */
    public double getKs() {
        return _Ks;
    }

    /**
     * getter
     * @return nshininess
     */
    public int getNshininess() {
        return _nshininess;
    }
}
