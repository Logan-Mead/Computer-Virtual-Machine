public class alu_test {
    public static void main(String[] args) throws Exception{
        bit_test.runTests();
        longword_test.runTest();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        runTests();
    }

    static void runTests() throws Exception{
        //run tests for each method
        System.out.println("---------------------------------------");
        System.out.println("Running tests for alu");
        testDoOp();
    }

    //tests alu using different operation
    static void testDoOp() throws Exception{
        longword test1 = new longword(3);
        longword test2 = new longword(3);
        longword test3 = new longword(-5);
        longword test4 = new longword(-2);
        bit trueBit = new bit(true);
        bit falseBit = new bit();
        //creates different bit arrays for each operation
        bit[] and = {trueBit,falseBit,falseBit,falseBit};
        bit[] or = {trueBit,falseBit,falseBit,trueBit};
        bit[] xor = {trueBit,falseBit,trueBit,falseBit};
        bit[] not = {trueBit,falseBit,trueBit,trueBit};
        bit[] leftShift = {trueBit,trueBit,falseBit,falseBit};
        bit[] rightShift = {trueBit,trueBit,falseBit,trueBit};
        bit[] add = {trueBit,trueBit,trueBit,falseBit};
        bit[] subtract = {trueBit,trueBit,trueBit,trueBit};
        bit[] multiply = {falseBit,trueBit,trueBit,trueBit};

        //run tests on and operation using multiple numbers
        if(alu.doOp(and, test1, test2).getSigned() != 3) throw new Exception("3 and 3 is not 3");
        if(alu.doOp(and, test1, test3).getSigned() != 3) throw new Exception("3 and -5 is not 3");
        if(alu.doOp(and, test4, test1).getSigned() != 2) throw new Exception("-2 and 3 is not 2");
        if(alu.doOp(and, test3, test4).getSigned() != -6) throw new Exception("-5 and -2 is not -6");

        //run tests on or operation using multiple numbers
        if(alu.doOp(or, test1, test2).getSigned() != 3) throw new Exception("3 or 3 is not 3");
        if(alu.doOp(or, test1, test3).getSigned() != -5) throw new Exception("3 or -5 is not -5");
        if(alu.doOp(or, test4, test1).getSigned() != -1) throw new Exception("-2 or 3 is not -1");
        if(alu.doOp(or, test3, test4).getSigned() != -1) throw new Exception("-5 or -2 is not -1");

        //run tests xor and operation using multiple numbers
        if(alu.doOp(xor, test1, test2).getSigned() != 0) throw new Exception("3 xor 3 is not 0");
        if(alu.doOp(xor, test1, test3).getSigned() != -8) throw new Exception("3 xor -5 is not -8");
        if(alu.doOp(xor, test4, test1).getSigned() != -3) throw new Exception("-2 xor 3 is not -3");
        if(alu.doOp(xor, test3, test4).getSigned() != 5) throw new Exception("-5 xor -2 is not 5");

        //run tests on not operation using multiple numbers
        if(alu.doOp(not, test1, test2).getSigned() != -4) throw new Exception("3 not is not -4");
        if(alu.doOp(not, test2, test3).getSigned() != -4) throw new Exception("3 not is not -4");
        if(alu.doOp(not, test3, test4).getSigned() != 4) throw new Exception("-5 not is not 4");
        if(alu.doOp(not, test4, test1).getSigned() != 1) throw new Exception("-2 not is not 1");

        //run tests on left shift operation using multiple numbers
        if(alu.doOp(leftShift, test1, test2).getSigned() != 24) throw new Exception("3 left shifted 3x is not 24");
        if(alu.doOp(leftShift, test4, test1).getSigned() != -16) throw new Exception("-2 left shifted 3x is not -16");

        //run tests on right shift operation using multiple numbers
        if(alu.doOp(rightShift, test1, test2).getSigned() != 0) throw new Exception("3 right shifted 3x is not 0");
        if(alu.doOp(rightShift, test4, test1).getSigned() != 536870911) throw new Exception("-2 right shifted 3x is not 536870911 ");

        //run tests on add operation using multiple numbers
        if(alu.doOp(add, test1, test2).getSigned() != 6) throw new Exception("3 + 3 not 6");
        if(alu.doOp(add, test1, test3).getSigned() != -2) throw new Exception("3 + (-5) is not -2");
        if(alu.doOp(add, test4, test1).getSigned() != 1) throw new Exception("-2 + 3 is not 1");
        if(alu.doOp(add, test3, test4).getSigned() != -7) throw new Exception("-5 + -2 is not -7");

        //run tests on subtract operation using multiple numbers
        if(alu.doOp(subtract, test1, test2).getSigned() != 0) throw new Exception("3 - 3 not 0");
        if(alu.doOp(subtract, test1, test3).getSigned() != 8) throw new Exception("3 - (-5) is not 8");
        if(alu.doOp(subtract, test4, test1).getSigned() != -5) throw new Exception("-2 - 3 is not -5");
        if(alu.doOp(subtract, test3, test4).getSigned() != -3) throw new Exception("-5 - (-2) is not -3");

        //run tests on multiply operation using multiple numbers
        if(alu.doOp(multiply, test1, test2). getSigned() != 9) throw new Exception("3 x 3 is not 9");
        if(alu.doOp(multiply, test1, test3).getSigned() != -15) throw new Exception("3 x -5 is not -15");
        if(alu.doOp(multiply, test4, test1).getSigned() != -6) throw new Exception("-2 x 3 is not -6");
        if(alu.doOp(multiply, test3, test4).getSigned() != 10) throw new Exception("-5 x -2 is not 10");
        System.out.println("Test alu passed");
    }

}
