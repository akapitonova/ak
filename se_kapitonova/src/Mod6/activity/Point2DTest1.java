package Mod6.activity;

import junit.framework.TestCase;

public class Point2DTest1 extends TestCase {

    public void testPoint2dDefaultCreate(){
        Point2D p = new Point2DImpl();
        assertEquals(0.0, p.getX());
        assertEquals(0.0, p.getY());
    }

    public void testPoint2dCreate(){
        Point2D p = new Point2DImpl(0.1, 1.1);
        assertEquals(0.1, p.getX());
        assertEquals(1.1, p.getY());
    }

    public void testMove(){
        Point2D p = new Point2DImpl(0.1, 1.1);
        p.move(1, 2.3);
        assertEquals(1.0, p.getX());
        assertEquals(2.3, p.getY());
    }

    public void testTranslate(){
        Point2D p = new Point2DImpl(0.1, 1.1);
        p.translate(1, 2);
        assertEquals(1.1, p.getX());
        assertEquals(3.1, p.getY());
    }

    public void testEquals(){
        Point2D p = new Point2DImpl(1.0, 1.1);
        assertTrue(p.equals(1.0, 1.1));
        assertFalse(p.equals(1.0, 1.0));
    }

    public void testGetDistance(){
        Point2D p = new Point2DImpl(1, 2);
        assertEquals(1.0, p.getDistance(new Point2DImpl(0, 2)));
    }
}
