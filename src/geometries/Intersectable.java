package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * interface for intersectable
 */
public interface Intersectable {
    List<GeoPoint> findGeoIntersections(Ray ray);

    /**
     * Static Internal Auxiliary Department
     */
    public static class GeoPoint {
        public Geometry _geometry;
        public Point3D _point;

        public GeoPoint(Geometry geometry, Point3D point) {
            _geometry = geometry;
            _point = point;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(_geometry, geoPoint._geometry) && Objects.equals(_point, geoPoint._point);
        }
    }

    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        //we check if there is intersection if yes we return list of the points
        return geoList == null ? null
                : geoList.stream().map(gp -> gp._point).collect(Collectors.toList());
    }


}
