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
        if(_list.isEmpty())
            return null;
List<Point3D> points=new ArrayList<Point3D>();
List<Point3D> temp=new ArrayList<Point3D>();

        for (int i=0;i<_list.size();++i)
{
    temp=_list.get(i).findIntersections(ray);
    if(!temp.isEmpty())
       points.addAll(temp);
}
        if(points.isEmpty())
            return null;
        return points;
    }

}
