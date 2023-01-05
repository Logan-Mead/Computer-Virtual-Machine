public class rippleAdder_test {
    public static void main(String[] args) throws Exception{
        bit_test.runTests();
        longword_test.runTest();
        runTests();
    }

    static void runTests() throws Exception{
        //run tests for each method
        System.out.println("---------------------------------------");
        System.out.println("Homework 3: ");
        System.out.println("Running tests for ripple adder");
        testAdd();
        testSubtract();
    }

    //tests for ripple adder using different numbers
    static void testAdd() throws Exception{
        longword test1 = new longword(-10);
        longword test2 = new longword(-5);
        longword test3 = new longword(10);
        longword test4 = new longword(28);
        longword test5 = new longword(5);
        longword test6 = new longword(-7);
        longword test7 = new longword(-22);
        longword test8 = new longword(6);

        if(rippleAdder.add(test1, test2).getSigned() != -15) throw new Exception("-10 + -5 is not -15");
        if(rippleAdder.add(test3, test4).getSigned() != 38) throw new Exception("10 + 28 is not 38");
        if(rippleAdder.add(test5, test6).getSigned() != -2) throw new Exception("5 + (-7) is not -2");
        if(rippleAdder.add(test7, test8).getSigned() != -16) throw new Exception("-22 + 6 is not 16");
        System.out.println("Test rippleAdder passed");
    }

    //tests for ripple subtract using different numbers
    static void testSubtract() throws Exception{
        longword test1 = new longword(-10);
        longword test2 = new longword(-5);
        longword test3 = new longword(28);
        longword test4 = new longword(10);
        longword test5 = new longword(5);
        longword test6 = new longword(-7);
        longword test7 = new longword(-22);
        longword test8 = new longword(6);

        if(rippleAdder.subtract(test1, test2).getSigned() != -5) throw new Exception("-10 - (-5) is not -5");
        if(rippleAdder.subtract(test3, test4).getSigned() != 18) throw new Exception("28 - 10 is not -8");
        if(rippleAdder.subtract(test5, test6).getSigned() != 12) throw new Exception("5 - (-7) is not -12");
        if(rippleAdder.subtract(test7, test8).getSigned() != -28) throw new Exception("-22 - 6 is not 28");
        System.out.println("Test rippleSubtractor passed");

    }
}
