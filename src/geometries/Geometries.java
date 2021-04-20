package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geometries {
    List<Intersectable> _list;


    Geometries(Intersectable... geometries){

        _list = new ArrayList<Intersectable>(Arrays.asList(geometries));
    }

    Geometries(){

        _list = new ArrayList<Intersectable>(Arrays.asList());
    }
    public void add(Intersectable geometries)
    {
        _list.add(geometries);
    }
    List<Point3D> findIntsersections(Ray ray){
        return null;
    }

}
