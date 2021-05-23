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

    public Geometries(){
//empty list
        _list = new ArrayList<Intersectable>(Arrays.asList());
    }
    public void add(Intersectable geometries)
    {
        _list.add(geometries);
    }

    public List<Point3D> findIntsersections(Ray ray){
        if(_list.isEmpty())
            return null;
List<Point3D> points=new ArrayList<Point3D>();//list for the point intersection
List<Point3D> temp=new ArrayList<Point3D>();

        for (int i=0;i<_list.size();++i)
{//if the point intersection the ray we add her to the list
    temp=_list.get(i).findIntersections(ray);
    if(temp!=null)
       points.addAll(temp);
}
        if(points.isEmpty())//if the list empty we return null
            return null;
        return points;
    }

}
