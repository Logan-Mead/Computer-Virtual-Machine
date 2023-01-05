public class assembler {

    public static String[] assemble(String[] instructions) throws Exception {
        String[] binaryInstructions = new String[instructions.length];  //string to hold current instruction to be converted
        String[] fullInstruction = new String[instructions.length];              //string array to hold all strings of instructions

        for(int i = 0; i < instructions.length; i++) {
            binaryInstructions = instructions[i].split(" ");     //gets rid of spacing in string

            //checks which operation is taking place
            switch (binaryInstructions[0]) {

                case "halt":    //0000
                    fullInstruction[i] = "0000" + "000000000000";
                    break;

                case "move":   //0001
                    int movValue = Integer.parseInt(binaryInstructions[2]);
                    //error checking to see if value is within the 8-bits signed values for highest and smalled value
                    if (movValue > 127) {
                        throw new Exception("move value is too large");
                    } else if (movValue < -128) {
                        throw new Exception("move value is too small");
                    } else {
                        //converts string to int that longword than concats value to add to full instruction
                        longword longwordMovValue = new longword(movValue);
                        String stringMovValue = longwordMovValue.booleanTobinary();
                        stringMovValue = stringMovValue.substring(24);
                        fullInstruction[i] = "0001" + getRegister(binaryInstructions[1]) + stringMovValue;
                    }
                    break;

                case "interrupt":  //0010
                    if (binaryInstructions[1].equals("1")) {
                        fullInstruction[i] = "0010" + "000000000001";
                    } else {
                        fullInstruction[i] = "0010" + "000000000000";
                    }
                    break;

                case "jump":        //0011 jump to a certain address in memory
                    //error checking to see if value is within the 12-bits signed values for highest and smalled value
                    int jumpValue = Integer.parseInt(binaryInstructions[1]);
                    if (jumpValue > 4095) {
                        throw new Exception("Jump value too large");
                    } else if (jumpValue < 0) {
                        throw new Exception("jump value can not be negative");
                    } else {
                        //converts string to int that longword than concats value to add to full instruction
                        longword jumpLongword = new longword(jumpValue);
                        String stringJumpValue = jumpLongword.booleanTobinary();
                        stringJumpValue = stringJumpValue.substring(20);    //change possibly
                        fullInstruction[i] = "0011" + stringJumpValue;
                    }
                    break;

                case "compare":     //0100 compare two registers
                    fullInstruction[i] = "0100" + "0000" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]);
                    break;

                case "branchIfLessThan":    //0101 with 00
                    int branchValue = Integer.parseInt(binaryInstructions[1]);
                    //error checking to see if value is within the 10-bits signed values for highest and smalled value
                    if (branchValue > 511) {
                        throw new Exception("branch value is too large");
                    } else if (branchValue < -512) {
                        throw new Exception("branch value is too small");
                    } else {
                        //converts string to int that longword than concats value to add to full instruction
                        longword longwordBranchValue = new longword(branchValue);
                        String stringBranchValue = longwordBranchValue.booleanTobinary();
                        stringBranchValue = stringBranchValue.substring(22);
                        fullInstruction[i] = "0101" + "00" + stringBranchValue;
                    }
                    break;

                case "branchIfGreaterThan":     //0101 with 01
                    branchValue = Integer.parseInt(binaryInstructions[1]);
                    //error checking to see if value is within the 10-bits signed values for highest and smalled value
                    if (branchValue > 511) {
                        throw new Exception("branch value is too large");
                    } else if (branchValue < -512) {
                        throw new Exception("branch value is too small");
                    } else {
                        //converts string to int that longword than concats value to add to full instruction
                        longword longwordBranchValue = new longword(branchValue);
                        String stringBranchValue = longwordBranchValue.booleanTobinary();
                        stringBranchValue = stringBranchValue.substring(22);
                        fullInstruction[i] = "0101" + "01" + stringBranchValue;
                    }
                    break;

                case "branchIfEqual":           //0101 with 10
                    branchValue = Integer.parseInt(binaryInstructions[1]);
                    //error checking to see if value is within the 10-bits signed values for highest and smalled value
                    if (branchValue > 511) {
                        throw new Exception("branch value is too large");
                    } else if (branchValue < -512) {
                        throw new Exception("branch value is too small");
                    } else {
                        //converts string to int that longword than concats value to add to full instruction
                        longword longwordBranchValue = new longword(branchValue);
                        String stringBranchValue = longwordBranchValue.booleanTobinary();
                        stringBranchValue = stringBranchValue.substring(22);
                        fullInstruction[i] = "0101" + "10" + stringBranchValue;
                    }
                    break;

                case "branchIfNotEqual":           //0101 with 00
                    branchValue = Integer.parseInt(binaryInstructions[1]);
                    //error checking to see if value is within the 10-bits signed values for highest and smalled value
                    if (branchValue > 511) {
                        throw new Exception("branch value is too large");
                    } else if (branchValue < -512) {
                        throw new Exception("branch value is too small");
                    } else {
                        //converts string to int that longword than concats value to add to full instruction
                        longword longwordBranchValue = new longword(branchValue);
                        String stringBranchValue = longwordBranchValue.booleanTobinary();
                        stringBranchValue = stringBranchValue.substring(22);
                        fullInstruction[i] = "0101" + "00" + stringBranchValue;
                    }
                    break;

                case "push":    //0110 sets fullInstruction to push instruction with register
                    fullInstruction[i] = "0110" + "00" + "000000" + getRegister(binaryInstructions[1]);
                    break;

                case "pop":     //0110 sets fullInstruction to pop instruction with the register
                    fullInstruction[i] = "0110" + "01" + "000000" + getRegister(binaryInstructions[1]);
                    break;

                case "call":    //0110 sets fullInstruction to call instruction
                    int callAddress = Integer.parseInt(binaryInstructions[1]);
                    //checks if address is too large for calling for jump
                    if (callAddress > 1023) {
                        throw new Exception("call address is too large");
                    }
                    else if (callAddress < 0) {
                        throw new Exception("call address is too small");
                    }
                    else {
                        longword longwordCallAddress = new longword(callAddress);
                        String stringCallAddress = longwordCallAddress.booleanTobinary();
                        stringCallAddress = stringCallAddress.substring(22);
                        fullInstruction[i] = "0110" + "10" + stringCallAddress;
                    }
                    break;

                case "return":  //0110 sets fullInstruction to return instruction
                    fullInstruction[i] = "0110" + "11" + "0000000000";
                    break;

                case "and":     //1000
                    fullInstruction[i] = "1000" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]) + getRegister(binaryInstructions[3]);
                    break;

                case "or":      //1001
                    fullInstruction[i] = "1001" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]) + getRegister(binaryInstructions[3]);
                    break;

                case "xor":     //1010
                    fullInstruction[i] = "1010" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]) + getRegister(binaryInstructions[3]);
                    break;

                case "not":     //1011
                    fullInstruction[i] = "1011" + getRegister(binaryInstructions[1]) + "0000" + getRegister(binaryInstructions[2]);
                    break;

                case "leftshift":   //1100
                    fullInstruction[i] = "1100" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]) + getRegister(binaryInstructions[3]);
                    break;

                case "rightshift":  //1101
                    fullInstruction[i] = "1101" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]) + getRegister(binaryInstructions[3]);
                    break;

                case "add":     //1110
                    fullInstruction[i] = "1110" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]) + getRegister(binaryInstructions[3]);
                    break;

                case "sub":     //1111
                    fullInstruction[i] = "1111" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]) + getRegister(binaryInstructions[3]);
                    break;

                case "multiply":  //0111
                    fullInstruction[i] = "0111" + getRegister(binaryInstructions[1]) + getRegister(binaryInstructions[2]) + getRegister(binaryInstructions[3]);
                    break;

                default:        //error checking if user inputs a non-real instruction
                    throw new Exception("Invalid instruction!");
            }
        }
        return fullInstruction;
    }

    //finds the register and outputs as a string
    public static String getRegister(String register) throws Exception {
        String output;
        switch(register){
            case "R0":
                output = "0000";
                break;

            case "R1":
                output = "0001";
                break;

            case "R2":
                output = "0010";
                break;

            case "R3":
                output = "0011";
                break;

            case "R4":
                output = "0100";
                break;

            case "R5":
                output = "0101";
                break;

            case "R6":
                output = "0110";
                break;

            case "R7":
                output = "0111";
                break;

            case "R8":
                output = "1000";
                break;
            case "R9":
                output = "1001";
                break;

            case "R10":
                output = "1010";
                break;

            case "R11":
                output = "1011";
                break;

            case "R12":
                output = "1100";
                break;

            case "R13":
                output = "1101";
                break;

            case "R14":
                output = "1110";
                break;

            case "R15":
                output = "1111";
                break;

            default:        //error checking to see if register input is in range of 0 and 15
                throw new Exception("Register number is not between 0 and 15!");
        }

        return output;
    }

}


