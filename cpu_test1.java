public class cpu_test1 {
    public static void main(String[] args) throws Exception{
        bit_test.runTests();
        longword_test.runTest();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        alu_test.runTests();
        memory_test.runTests();
        runTests();
    }

    static void runTests() throws Exception{
        //running tests for each method
        System.out.println("---------------------------------------");
        System.out.println("Homework 6/7:");
        System.out.println("Running Tests for computer");
        testPreLoad();
    }

    //test to see if I can preload instructions into memory and execute those instructions
    static void testPreLoad() throws Exception{
        computer cpu1 = new computer();
        //each instruction matches with each string individually
        //mov R0 -10
        //mov R1 10
        //mov R2 10
        //and R0 and R1 store R3 2
        //or R0 or R1 store R4 -2
        //xor R1 xor R2 store R5 0
        //not R1 store R6 -11
        //add R1 + R2 store R7 20
        //sub R1 - R2 store R8 0
        //multiply R1 * R2 store R9 100
        //interrupt 0 print registers
        //interrupt 1 print memory
        //halt
        String[] instructionsSet = {"0001000011110110", "0001000100001010", "0001001000001010", "1000000000010011", "1001000000010100", "1010000100100101", "1011000100000110", "1110000100100111", "1111000100101000", "0111000100101001", "0010000000000000", "0010000000000001", "0000000000000000"};
        cpu1.preload(instructionsSet);
        cpu1.run();

        //error checks all values in registers to see if instructions were done correctly
        if(cpu1.registers[0].getSigned() != -10) throw new Exception("Register 0 is not -10 Actual value: " + cpu1.registers[0].getSigned());
        if(cpu1.registers[1].getSigned() != 10) throw new Exception("Register 1 is not -10 Actual value: " + cpu1.registers[1].getSigned());
        if(cpu1.registers[2].getSigned() != 10) throw new Exception("Register 2 is not -10 Actual value: " + cpu1.registers[2].getSigned());
        if(cpu1.registers[3].getSigned() != 2) throw new Exception("Register 3 is not -10 Actual value: " + cpu1.registers[3].getSigned());
        if(cpu1.registers[4].getSigned() != -2) throw new Exception("Register 4 is not -10 Actual value: " + cpu1.registers[4].getSigned());
        if(cpu1.registers[5].getSigned() != 0) throw new Exception("Register 5 is not -10 Actual value: " + cpu1.registers[5].getSigned());
        if(cpu1.registers[6].getSigned() != -11) throw new Exception("Register 6 is not -10 Actual value: " + cpu1.registers[6].getSigned());
        if(cpu1.registers[7].getSigned() != 20) throw new Exception("Register 7 is not -10 Actual value: " + cpu1.registers[7].getSigned());
        if(cpu1.registers[8].getSigned() != 0) throw new Exception("Register 8 is not -10 Actual value: " + cpu1.registers[8].getSigned());
        if(cpu1.registers[9].getSigned() != 100) throw new Exception("Register 9 is not -10 Actual value: " + cpu1.registers[9].getSigned());
        System.out.println("testPreload() passed");
        System.out.println("testing instructions in memory passed");
    }
}
