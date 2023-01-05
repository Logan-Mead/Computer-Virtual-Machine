
public class memory_test {
     static memory testMemory = new memory();

    public static void main(String[] args) throws Exception{
        bit_test.runTests();
        longword_test.runTest();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        alu_test.runTests();
        runTests();
    }

    static void runTests() throws Exception{
        //run tests for each method
        System.out.println("---------------------------------------");
        System.out.println("Homework 5: ");
        System.out.println("Running tests for memory");
        testRead();
        testWrite();
    }

    //test to see if reading at an address find the correct value at the address
    static void testRead() throws Exception{
        testMemory = new memory();                                            //ensure starting memory with clean state
        longword test1 = new longword(10);
        longword test2 = new longword(30);
        longword address =  new longword(2);
        longword address2 = new longword(3);

        testMemory.write(address, test1);
        if(testMemory.read(address).getSigned() != 10) throw new Exception("value is not 10 at address 2");

        testMemory.write(address2, test2);
        if(testMemory.read(address2).getSigned() != 30) throw new Exception("value is not 30 at address 3");
        if(testMemory.read(address).getSigned() != 7690) throw new Exception("value is not overriding at address 2 with value 7690");                   //testing if data is overlapping in memory
        System.out.println("Test read memory passed");
    }

    //test to see if writing at an address to write the correct value at the address
    static void testWrite() throws Exception{
        testMemory = new memory();                                          //ensure starting memory with clean state
        longword test1 = new longword(27);
        longword test2 = new longword(14);
        longword address =  new longword(3);
        longword address2 = new longword(6);

        testMemory.write(address, test1);
        if(testMemory.read(address).getSigned() != 27) throw new Exception("value is not 27 at address 3");

        testMemory.write(address2, test2);
        if(testMemory.read(address2).getSigned() != 14) throw new Exception("value is not 14 at address 6");
        if(testMemory.read(address).getSigned() != 234881051) throw new Exception("value is not 234881051 at address 3 and not having data overlapping");           //test for data overlapping
        System.out.println("Test write memory passed");
    }
}
