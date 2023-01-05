public class bit_test{
    public static void main(String[] args) throws Exception {
        runTests();
    }

    public static void runTests()throws Exception{
        //runs test for each method
        System.out.println("---------------------------------------");
        System.out.println("Homework 1:");
        System.out.println("Running tests for bit");
        testGetValue();
        testSetValue();
        testToggle();
        testSet();
        testClear();
        testAnd();
        testOr();
        testXor();
        testNot();
        testToString();
    }

    //test if getValue() method is working
    static void testGetValue() throws Exception{
        bit bit = new bit();
        bit.set(false);
        if(bit.getValue() != false) throw new Exception("Bit value not got at 0");

        bit bit2 = new bit();
        bit2.set(true);
        if(bit2.getValue() != true) throw new Exception("Bit value not got at 1");
        System.out.println("test getValue() passed");
    }

    //test if set(boolean value) method is working
    static void testSetValue() throws Exception{
        bit bit = new bit();
        bit.set(false);
        if(bit.getValue() != false) throw new Exception("Bit not set to 0");

        bit bit2 = new bit();
        bit2.set(true);
        if(bit2.getValue() != true) throw new Exception("Bit not set to 1");
        System.out.println("test set(boolean value) passed");
    }

    //test if toggle() method is working
    static void testToggle() throws Exception{
        bit bit = new bit(false);
        bit.toggle();
        if(bit.getValue() != true) throw new Exception(("toggle 0 is not 1"));

        bit bit2 = new bit(true);
        bit2.toggle();
        if(bit2.getValue() != false) throw new Exception(("toggle 1 is not 0"));
        System.out.println("test toggle() passed");
    }

    //test if set() method is working
    static void testSet() throws Exception{
        bit bit = new bit(false);
        bit.set();
        if(bit.getValue() != true) throw new Exception("Bit not set to 1");
        System.out.println("test set() passed");

    }

    //test if clear() method is working
    static void testClear() throws Exception{
        bit bit = new bit(true);
        bit.clear();
        if(bit.getValue() != false) throw new Exception("Bit not set to 0");
        System.out.println("test clear() passed");
    }

    //test if and() method is working
    static void testAnd() throws Exception {
        if (new bit(false).and(new bit(false)).getValue() != false) throw new Exception("0 AND 0 failed");
        if (new bit(false).and(new bit(true)).getValue() != false) throw new Exception("0 AND 1 failed");
        if (new bit(true).and(new bit(false)).getValue() != false) throw new Exception("1 AND 0 failed");
        if (new bit(true).and(new bit(true)).getValue() != true) throw new Exception("1 AND 1 failed");
        System.out.println("test and() passed");

    }

    //test if or() method is working
    static void testOr() throws Exception{
        if(new bit(false).or(new bit(false)).getValue() != false) throw new Exception(("0 or 0 failed"));
        if(new bit(false).or(new bit(true)).getValue() != true) throw new Exception(("0 or 1 failed"));
        if(new bit(true).or(new bit(false)).getValue() != true) throw new Exception(("1 or 0 failed"));
        if(new bit(true).or(new bit(true)).getValue() != true) throw new Exception(("1 or 1 failed"));
        System.out.println("test or() passed");
    }

    //test if xor() method is working
    static void testXor() throws Exception{
        if(new bit(false).xor(new bit(false)).getValue() != false) throw new Exception("0 xor 0 failed");
        if(new bit(false).xor(new bit(true)).getValue() != true) throw new Exception("0 xor 1 failed");
        if(new bit(true).xor(new bit(false)).getValue() != true) throw new Exception("1 xor 0 failed");
        if(new bit(true).xor(new bit(true)).getValue() != false) throw new Exception("1 xor 1 failed");
        System.out.println("test xor() passed");
    }

    //test if not() method is working
    static void testNot() throws Exception{
        if(new bit(false).not().getValue() != true) throw new Exception("0 not 1 failed");
        if(new bit(true).not().getValue() != false) throw new Exception("1 not 0 failed");
        System.out.println("test not() passed");
    }

    //test if toString() method is working
    static void testToString() throws Exception{
        if(new bit(false).toString() != "f") throw new Exception("toString not f");
        if(new bit(true).toString() != "t") throw new Exception("toString not t");
        System.out.println("test toString() passed");
    }
}
