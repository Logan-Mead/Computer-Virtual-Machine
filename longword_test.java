public class longword_test {
    public static void main(String[] args) throws Exception{
        bit_test.runTests();
        runTest();
    }

    static void runTest() throws Exception{
        //run tests for each method
        System.out.println("---------------------------------------");
        System.out.println("Homework 2: ");
        System.out.println("Running tests for longword");
        testGetBit();
        testSetBit();
        testLongAnd();
        testLongOr();
        testLongXor();
        testLongNot();
        testRightShift();
        testLeftShift();
        testToString();
        testUnsigned();
        testSigned();
        testCopy();
        testLongSet();
    }

    //tests getBit(i) for longwords
    static void testGetBit() throws Exception{
        longword longword = new longword();
        longword.setBit(3, new bit(true));
        if(longword.getBit(3).getValue() != true) throw new Exception(("Bit is not true at [3]"));
        System.out.println("Test getBit() passed");
    }

    //tests setBit(i) for longwords
    static void testSetBit() throws Exception{
        longword longword = new longword();
        longword.setBit(7, new bit(true));
        if(longword.getBit(7).getValue() != true) throw new Exception("Bit is not true at [7]");
        System.out.println("Test setBit() passed");
    }

    //tests and(long) for longwords
    static void testLongAnd() throws Exception{
        longword longFalse = new longword(false);
        longword longFalse2 = new longword(false);
        longword longTrue = new longword(true);
        longword longTrue2 = new longword(true);

        //test cases for all and
        for(int i = 0; i < 31; i++){
            if(longFalse.getBit(i).and(longFalse2.getBit(i)).getValue() != false) throw new Exception("Long and output is not all 0's");
            if(longFalse.getBit(i).and(longTrue.getBit(i)).getValue() != false) throw new Exception("Long and output is not all 0's");
            if(longTrue.getBit(i).and(longFalse.getBit(i)).getValue() != false) throw new Exception("Long and output is not all 0's");
            if(longTrue.getBit(i).and(longTrue2.getBit(i)).getValue() != true) throw new Exception("Long and output is not all 1's");
        }

        System.out.println("Test long and passed");
    }

    //tests or(long) for longwords
    static void testLongOr() throws Exception{
        longword longFalse = new longword(false);
        longword longFalse2 = new longword(false);
        longword longTrue = new longword(true);
        longword longTrue2 = new longword(true);

        //test cases for all or
        for(int i = 0; i < 31; i++){
            if(longFalse.getBit(i).or(longFalse2.getBit(i)).getValue() != false) throw new Exception("Long or output is not all 0's");
            if(longFalse.getBit(i).or(longTrue.getBit(i)).getValue() != true) throw new Exception("Long or output is not all 0's");
            if(longTrue.getBit(i).or(longFalse.getBit(i)).getValue() != true) throw new Exception("Long or output is not all 0's");
            if(longTrue.getBit(i).or(longTrue2.getBit(i)).getValue() != true) throw new Exception("Long or output is not all 1's");
        }

        System.out.println("Test long or passed");
    }

    //tests xor(long) for longwords
    static void testLongXor() throws Exception{
        longword longFalse = new longword(false);
        longword longFalse2 = new longword(false);
        longword longTrue = new longword(true);
        longword longTrue2 = new longword(true);

        //test cases for all xor
        for(int i = 0; i < 31; i++){
            if(longFalse.getBit(i).xor(longFalse2.getBit(i)).getValue() != false) throw new Exception("Long xor output is not all 0's");
            if(longFalse.getBit(i).xor(longTrue.getBit(i)).getValue() != true) throw new Exception("Long xor output is not all 0's");
            if(longTrue.getBit(i).xor(longFalse.getBit(i)).getValue() != true) throw new Exception("Long xor output is not all 0's");
            if(longTrue.getBit(i).xor(longTrue2.getBit(i)).getValue() != false) throw new Exception("Long xor output is not all 1's");
        }

        System.out.println("Test long xor passed");
    }

    //tests not() for longwords
    static void testLongNot() throws Exception{
        longword longFalse = new longword();
        longword longTrue = new longword(true);

        //test not for both all 0's and all 1's
        for(int i = 0; i < 32; i++){
            if(longFalse.getBit(i).not().getValue() != true) throw new Exception("All 0's not 1's");
            if(longTrue.getBit(i).not().getValue() != false) throw new Exception("All 1's not 0's");
        }

        System.out.println("Test not() long passed");
    }

    //tests rightShift() for longword
    static void testRightShift() throws Exception{
        longword test1 = new longword();
        test1.set(10);
        longword test2 = new longword();
        test2.set(4096);
        longword test3 = new longword();
        test3.set(26738688);
        if(test1.rightShift(2).getUnsigned() != 2) throw new Exception("10 right shifted 2x is not 2");
        if(test2.rightShift(10).getUnsigned() != 4) throw new Exception("4,096 right shifted 10x is not 4");
        if(test3.rightShift(6).getUnsigned() != 417792) throw new Exception("26,738,688 right shifted 6x is not 417,792");
        System.out.println("Test rightShift() passed");

    }

    //tests leftShift() on longword
    static void testLeftShift() throws Exception{
        longword test1 = new longword();
        test1.set(10);
        longword test2 = new longword();
        test2.set(27);
        longword test3 = new longword();
        test3.set(127);
        if(test1.leftShift(2).getUnsigned() != 40) throw new Exception("10 left shifted 2x is not 40");
        if(test2.leftShift(4).getUnsigned() != 432) throw new Exception("27 left shifted 4x is not 432");
        if(test3.leftShift(6).getUnsigned() != 8128) throw new Exception("127 left shifted 6x is not 8128");
        System.out.println("Test leftShift() passed");
    }

    //tests toString() for longword
    static void testToString() throws Exception{
        longword test1 = new longword();
        test1.set(56);
        longword test2 = new longword();
        test2.set(10);
        longword test3 = new longword();
        test3.set(14);
        if(!(test1.toString().equals("f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,t,t,t,f,f,f,"))) throw new Exception("toString() for 56 not correct");
        if(!(test2.toString().equals("f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,t,f,t,f,"))) throw new Exception("toString() for 10 not correct");
        if(!(test3.toString().equals("f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,t,t,t,f,"))) throw new Exception("toString() for 14 not correct");
        System.out.println("Test toString() passed");
    }

    //tests getUnsigned() for longword
    static void testUnsigned() throws Exception{
        longword test1 = new longword();
        test1.set(17430);
        longword test2 = new longword();
        test2.set(10);
        if(test1.getUnsigned() != 17430) throw new Exception("Unsigned value of 17430 is not 17430");
        if(test2.getUnsigned() != 10) throw new Exception("Unsigned value of 10 is not 10");
        System.out.println("Test getUnsigned() passed");
    }

    //tests getSigend() for longword
    static void testSigned() throws Exception{
        longword test1 = new longword();
        test1.set(-10);
        longword test2 = new longword();
        test2.set(-50267);
        longword test3 = new longword();
        test3.set(10);
        longword test4 = new longword();
        test4.set(52925);
        if(test1.getSigned() != -10) throw new Exception("Signed value of -10 is not -10");
        if(test2.getSigned() != -50267) throw new Exception(("Signed value of -50267 is not -50267"));
        if(test3.getSigned() != 10) throw new Exception("Signed value of 10 is not 10");
        if(test4.getSigned() != 52925) throw new Exception("Signed value of 52925 is not 52925");
        System.out.println("Test getSigned() passed");
    }

    //tests copy() for longword
    static void testCopy() throws Exception{
        longword test1 = new longword();
        longword copy1 = new longword();
        copy1.set(37);
        test1.copy(copy1);
        longword test2 = new longword();
        test2.set(294);
        longword copy2 = new longword();
        copy2.copy(test2);
        if(!(test1.toString().equals(copy1.toString()))) throw new Exception("copy not copied into test1");
        if(!(copy2.toString().equals(test2.toString()))) throw new Exception("test2 not copied into copy2");
        System.out.println("Test copy() passed");
    }

    //tests set(value) for longword
    static void testLongSet() throws Exception{
        longword test1 = new longword();
        test1.set(-1067);
        longword test2 = new longword();
        test2.set(10);
        longword test3 = new longword();
        test3.set(365);
        if(test1.getSigned() != -1067) throw new Exception("test1 not set to -1067");
        if(test2.getUnsigned() != 10) throw new Exception("test2 not set to 10");
        if(test3.getUnsigned() != 365) throw new Exception("test3 not set to 365");
        System.out.println("Test set(int i) passed");
    }
}
