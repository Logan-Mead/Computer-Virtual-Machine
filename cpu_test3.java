public class cpu_test3 {
    public static void main(String[] args) throws Exception{
        bit_test.runTests();
        longword_test.runTest();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        alu_test.runTests();
        memory_test.runTests();
        cpu_test1.runTests();
        assembler_test.runTests();
        cpu_test2.runTests();
        runTests();
    }

    static void runTests() throws Exception{
        //running tests for each method
        System.out.println("---------------------------------------");
        System.out.println("Homework 10:");
        System.out.println("Running Tests for stack it up!");
        testNewInstructions();
        testNewAssembler();
    }

    //testing if push, pop, call and return work
    static void testNewInstructions() throws Exception{
        //if memory prints first and then registers call and return works correctly
        System.out.println("Testing call and return, if memory prints first then registers call and return work properly");
        computer cpu3 = new computer();
        String[] stringSet3 = {"call 6", "interrupt 0", "halt", "interrupt 1", "return"};
        String[] instructionsSet3 = assembler.assemble(stringSet3);
        cpu3.preload(instructionsSet3);
        cpu3.run();
        System.out.println("Testing push and pop");

        //testing push and pop
        String[] stringSet4 = {"move R0 10", "move R1 6", "push R0", "push R1", "pop R2", "pop R3", "interrupt 0", "halt"};
        String[] instructionsSet4 = assembler.assemble(stringSet4);
        computer cpu4 = new computer();
        cpu4.preload(instructionsSet4);
        cpu4.run();
        //if registers R2 and R3 values are not mirrored of R0 and R1, push and pop doesn't work properly
        if(cpu4.registers[2].getSigned() != 6) throw new Exception("push and pop not working");
        if(cpu4.registers[3].getSigned() != 10) throw new Exception("push and pop not working");
        System.out.println("new instruction set works for push, pop, call and return");
    }

    //test if new instructions per assignment 10 work using assembler
    static void testNewAssembler() throws Exception{
        String[] stringSet3 = {"push R1", "pop R2", "call 10", "return"};
        String[] instructionsSet3 = assembler.assemble(stringSet3);

        //checks if new instruction for assignment is converted to opcodes correctly
        if(!instructionsSet3[0].equals("0110000000000001")) throw new Exception("converting push not correctly done");
        if(!instructionsSet3[1].equals("0110010000000010")) throw new Exception("converting pop not correctly done");
        if(!instructionsSet3[2].equals("0110100000001010")) throw new Exception("converting call not correctly done");
        if(!instructionsSet3[3].equals("0110110000000000")) throw new Exception("converting return not correctly done");
        System.out.println("Test new instructions for push, pop, call and return converted to assembly passed!");
    }
}
