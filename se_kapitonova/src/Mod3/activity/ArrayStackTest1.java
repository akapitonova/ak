package Mod3.activity;

import junit.framework.TestCase;

public class ArrayStackTest1 extends TestCase {

    public void testArrayStackCreateNormal(){
        int max = 5;
        ArrayStack arrayStack = new ArrayStack(max);
        assertEquals(max, arrayStack.getMaximum());
        assertEquals(0, arrayStack.getCount());
    }

    public void testArrayStackCreateException() {
        int max = -5;
        ArrayStack arrayStack = new ArrayStack(max);
        assertEquals(1, arrayStack.getMaximum());
        assertEquals(0, arrayStack.getCount());
    }

    public void testPush() {
        int max = 5;
        ArrayStack arrayStack = new ArrayStack(max);
        assertTrue(arrayStack.push(112));
    }

    public void testPeek(){
        int max = 5;
        ArrayStack arrayStack = new ArrayStack(max);
        arrayStack.push(112);
        assertEquals(112, arrayStack.peek());
    }

    public void testPop(){
        int max = 5;
        ArrayStack arrayStack = new ArrayStack(max);
        arrayStack.push(112);
        assertEquals(112, arrayStack.pop());
    }

    public void testFindElement(){
        int max = 5;
        ArrayStack arrayStack = new ArrayStack(max);
        arrayStack.push(112);
        arrayStack.push(113);
        arrayStack.push(114);
        assertEquals(0, arrayStack.findElement(114));
    }
}
