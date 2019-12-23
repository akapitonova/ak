package Mod6.activity;

import junit.framework.TestCase;

public class Point3DTest1 extends TestCase  {

    public void testPoint3DDefaultCreate(){
        Point3D p = new Point3DImpl();
        assertEquals(0.0, p.getX());
        assertEquals(0.0, p.getY());
        assertEquals(0.0, p.getZ());
    }

    public void testPoint3DCreate(){
        Point3D p = new Point3DImpl(0.0, 0.1, 0.2);
        assertEquals(0.0, p.getX());
        assertEquals(0.1, p.getY());
        assertEquals(0.2, p.getZ());
    }

    public void testMove(){
        Point3D p = new Point3DImpl(0.0, 0.1, 0.2);
        p.move(1, 1, 1);
        assertEquals(1.0, p.getX());
        assertEquals(1.0, p.getY());
        assertEquals(1.0, p.getZ());
    }


    public void testTranslate(){
        Point3D p = new Point3DImpl(0.0, 0.1, 0.2);
        p.translate(1, 1, 1);
        assertEquals(1.0, p.getX());
        assertEquals(1.1, p.getY());
        assertEquals(1.2, p.getZ());
    }

    public void testEquals(){
        Point3D p = new Point3DImpl(0.0, 0.1, 0.2);
        assertTrue(p.equals(0.0, 0.1, 0.2));
        assertFalse(p.equals(new Point3DImpl(1.0, 8.1, 0.0)));
    }

    public void testGetDistance(){
        Point3D p = new Point3DImpl(1, 2, 0);
        assertEquals(1.0, p.getDistance(new Point3DImpl(1, 2, 1)));
    }
}
