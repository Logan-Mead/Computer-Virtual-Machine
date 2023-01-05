public class cpu_test2 {
    public static void main(String[] args) throws Exception{
        bit_test.runTests();
        longword_test.runTest();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        alu_test.runTests();
        memory_test.runTests();
        cpu_test1.runTests();
        assembler_test.runTests();
        runTests();
    }

    static void runTests() throws Exception{
        //running tests for each method
        System.out.println("---------------------------------------");
        System.out.println("Homework 9:");
        System.out.println("Running Tests for jump jump!");
        testJump();
        testBranchIfEqual();
        testNewAssembler();
    }

    //test if jump to a certain address works
    static void testJump() throws Exception{
        computer cpu2 = new computer();
        String[] stringSet2 = {"jump 10", "move R0 10", "move R1 2", "move R3 40", "move R4 -10", "interrupt 0", "halt"};        //works to test jump, skips 5 instructions or 10 addresses and goes to interrupt 0
        String[] instructionsSet2 = assembler.assemble(stringSet2);
        cpu2.preload(instructionsSet2);
        cpu2.run();

        //checks if jump works, if any register has values if so jump did not work
        if(cpu2.registers[0].getSigned() == 10) throw new Exception("Jump instruction did not work as R0 was set to 10");
        if(cpu2.registers[1].getSigned() == 2) throw new Exception("Jump instruction did not work as R1 was set to 2");
        if(cpu2.registers[2].getSigned() == 40) throw new Exception("Jump instruction did not work as R2 was set to 40");
        if(cpu2.registers[3].getSigned() == -10) throw new Exception("Jump instruction did not work as R3 was set to -10");
        System.out.println("testJump() passed!");
    }

    //tests if all branchIf conditions work
    static void testBranchIfEqual() throws Exception{
        computer cpu2 = new computer();
        String[] stringSet2 = {"move R0 0", "move R1 10", "move R2 10", "compare R0 R1", "branchIfLessThan 2", "move R3 4", "move R4 7", "halt"};
        String[] instructionsSet2 = assembler.assemble(stringSet2);
        cpu2.preload(instructionsSet2);
        cpu2.run();

        computer cpu3 = new computer();
        String[] stringSet3 = {"move R0 0", "move R1 10", "move R2 10", "compare R1 R0", "branchIfGreaterThan 2", "move R3 4", "move R5 7", "halt"};
        String[] instructionsSet3 = assembler.assemble(stringSet3);
        cpu3.preload(instructionsSet3);
        cpu3.run();

        computer cpu4 = new computer();
        String[] stringSet4 = {"move R0 0", "move R1 10", "move R2 10", "compare R1 R2", "branchIfEqual 2", "move R3 4", "move R6 7", "halt"};
        String[] instructionsSet4 = assembler.assemble(stringSet4);
        cpu4.preload(instructionsSet4);
        cpu4.run();

        computer cpu5 = new computer();
        String[] stringSet5 = {"move R0 0", "move R1 10", "move R2 10", "compare R1 R0", "branchIfNotEqual 2", "move R3 4", "move R7 7", "halt"};
        String[] instructionsSet5 = assembler.assemble(stringSet5);
        cpu5.preload(instructionsSet5);
        cpu5.run();

        //testing if branching with negative numbers work
        computer cpu6 = new computer();
        String[] stringSet6 = {"move R0 1", "move R1 2", "compare R0 R1", "branchIfLessThan 8" , "move R2 3", "move R3 4", "move R4 5", "halt", "branchIfLessThan -8", "interrupt 0"};
        String[] instructionsSet6 = assembler.assemble(stringSet6);
        cpu6.preload(instructionsSet6);
        cpu6.run();

        //checks if branch condition works as it skips register setting to setting other register, if both set throw exception
        if(cpu2.registers[3].getSigned() == 4 && cpu2.registers[4].getSigned() != 7) throw new Exception("branchIfLessThan not working");
        if(cpu3.registers[3].getSigned() == 4 && cpu3.registers[5].getSigned() != 7) throw new Exception("branchIfGreaterThan not working");
        if(cpu4.registers[3].getSigned() == 4 && cpu4.registers[6].getSigned() != 7) throw new Exception("branchIfEqual not working");
        if(cpu5.registers[3].getSigned() == 4 && cpu5.registers[7].getSigned() != 7) throw new Exception("branchIfNotEqual not working");
        if(cpu6.registers[2].getSigned() != 0 && cpu6.registers[3].getSigned() == 4 && cpu6.registers[4].getSigned() == 5) throw new Exception("branching negative numbers not working correctly");
        System.out.println("Test compare passed!");
        System.out.println("TestBranchIf passed!");
    }

    //test if new instructions per assignment 9 work using assembler
    static void testNewAssembler() throws Exception{
        String[] stringSet2 = {"jump 10", "compare R1 R2", "branchIfLessThan -4", "branchIfGreaterThan 4", "branchIfEqual 4", "branchIfNotEqual 4", "interrupt 0", "interrupt 1", "halt"};
        String[] instructionsSet2 = assembler.assemble(stringSet2);

        //checks if new instruction for assignment is converted to opcodes through assembly
        if(!instructionsSet2[0].equals("0011000000001010")) throw new Exception("converting jump instruction not correct");
        if(!instructionsSet2[1].equals("0100000000010010")) throw new Exception("converting compare instruction not correct");
        if(!instructionsSet2[2].equals("0101001111111100")) throw new Exception("converting branchIfLessThan not correct");
        if(!instructionsSet2[3].equals("0101010000000100")) throw new Exception("converting branchIfGreaterThan not correct");
        if(!instructionsSet2[4].equals("0101100000000100")) throw new Exception("converting branchIfEqual not correct");
        if(!instructionsSet2[5].equals("0101000000000100")) throw new Exception("converting branchIfNotEqual not correct");
        System.out.println("Test new instructions in assemble passed!");
    }
}
