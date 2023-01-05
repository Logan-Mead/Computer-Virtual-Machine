public class assembler_test {
   public static void main(String[] args) throws Exception{
       bit_test.runTests();
       longword_test.runTest();
       rippleAdder_test.runTests();
       multiplier_test.runTests();
       alu_test.runTests();
       memory_test.runTests();
       cpu_test1.runTests();
       runTests();
   }

   static void runTests() throws Exception{
       //running tests for each method
       System.out.println("---------------------------------------");
       System.out.println("Homework 8:");
       System.out.println("Running Tests for assembler");
       testAssembler();
   }

   //test to see if inputting a string can be converted to binary instructions
   static void testAssembler() throws Exception{
       //same instructions as cpu_test1 file to test consistency
       String[] instructions = {"move R0 -10", "move R1 10", "move R2 10", "and R0 R1 R3", "or R0 R1 R4", "xor R1 R2 R5", "not R1 R6", "add R1 R2 R7" , "sub R1 R2 R8", "multiply R1 R2 R9", "interrupt 0", "interrupt 1", "halt"};
       String[] instructionSet = assembler.assemble(instructions);
       for(int i = 0; i < instructions.length; i++){
           System.out.println(instructionSet[i]);
       }

       if(!instructionSet[0].equals("0001000011110110")) throw new Exception("mov R0 -10 Not Correct");
       if(!instructionSet[1].equals("0001000100001010")) throw new Exception("mov R1 10 Not Correct");
       if(!instructionSet[2].equals("0001001000001010")) throw new Exception("mov R2 10 Not Correct");
       if(!instructionSet[3].equals("1000000000010011")) throw new Exception("and R0 R1 R3 Not Correct");
       if(!instructionSet[4].equals("1001000000010100")) throw new Exception("or R0 R1 R4 Not Correct");
       if(!instructionSet[5].equals("1010000100100101")) throw new Exception("xor R1 R2 R5 Not Correct");
       if(!instructionSet[6].equals("1011000100000110")) throw new Exception("not R1 R6 Not Correct");
       if(!instructionSet[7].equals("1110000100100111")) throw new Exception("add R1 R2 R7 Not Correct");
       if(!instructionSet[8].equals("1111000100101000")) throw new Exception("sub R1 R2 R8 Not Correct");
       if(!instructionSet[9].equals("0111000100101001")) throw new Exception("multiply R1 R2 R9 Not Correct");
       if(!instructionSet[10].equals("0010000000000000")) throw new Exception("interrupt 0 Not Correct");
       if(!instructionSet[11].equals("0010000000000001")) throw new Exception("interrupt 1 Not Correct");
       if(!instructionSet[12].equals("0000000000000000")) throw new Exception("halt Not Correct");
       System.out.println("testAssembler() passed");
   }
}
