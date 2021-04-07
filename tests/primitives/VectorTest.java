package primitives;
/*
Nehorai Cohen 325356814
Ariel Benshushan 325455426
Nehorai4775@gmail.com
benshoshan60@gmail.com

* */
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static primitives.Util.isZero;

class VectorTest {

    @Test
    void testAdd() {
// ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);
        assertTrue(v1.add(v2).equals(new Vector(1,5,1)),"add doesn't work");

    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);
        assertTrue(v1.subtract(v2).equals(new Vector(-1,1,-5)),"add doesn't work");
    }

    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        assertTrue(v1.scale(3).equals(new Vector(3,6,9)),"scale doesn't good");
    }

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(
                IllegalArgumentException.class, () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not throw an exception");
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}

    }

    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        // =============== Boundary Values Tests ==================
       assertTrue( isZero(v1.dotProduct(v3))
            ,"ERROR: dotProduct() for orthogonal vectors is not zero");
        // ============ Equivalence Partitions Tests ==============
        assertTrue(isZero(v1.dotProduct(v2) + 28),
            "ERROR: dotProduct() wrong value");
    }

    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
// ============ Equivalence Partitions Tests ==============
        assertTrue(v1.lengthSquared()==14
                ,"ERROR: lengthSquared() wrong value");
    }

    @Test
    void testLength() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5)
            ,"ERROR: length() wrong value");
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        // =============== Boundary Values Tests ==================
        assertTrue(vCopy == vCopyNormalize
            ,"ERROR: normalize() function creates a new vector");
        // ============ Equivalence Partitions Tests ==============
        assertTrue(isZero(vCopyNormalize.length() - 1)
            ,"ERROR: normalize() result is not a unit vector");

    }

    @Test
    void testNormalized() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        // ============ Equivalence Partitions Tests ==============
        assertTrue(u != v
                ,"ERROR: normalizated() function does not create a new vector");
    }
}