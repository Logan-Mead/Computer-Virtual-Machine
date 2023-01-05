public class multiplier_test {
    public static void main(String[] args) throws Exception{
        bit_test.runTests();
        longword_test.runTest();
        rippleAdder_test.runTests();
        runTests();
    }

    static void runTests() throws Exception{
        //run tests for each method
        System.out.println("---------------------------------------");
        System.out.println("Homework 4: ");
        System.out.println("Running tests for multiplier");
        testMultiply();
    }

    //tests multiply
    static void testMultiply() throws Exception{
        longword test1 = new longword(3);
        longword test2 = new longword(3);
        longword test3 = new longword(-3);
        longword test4 = new longword(-5);
        longword test5 = new longword(-4);
        if(multiplier.multiply(test1, test2).getSigned() != 9) throw new Exception("3 x 3 is not 9");
        if(multiplier.multiply(test2, test3).getSigned() != -9) throw new Exception("3 x -3 is not 9");
        if(multiplier.multiply(test3, test2).getSigned() != -9) throw new Exception("-3 x 3 is not 9");
        if(multiplier.multiply(test4, test5).getSigned() != 20) throw new Exception("-5 x -4 is not 20");
        System.out.println("Test multiply passed");
    }

}
