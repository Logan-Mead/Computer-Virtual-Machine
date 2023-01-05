public class computer {
    private memory computerMemory = new memory();                              //create a memory just for the computer
    public bit computerRun = new bit(true);                              //allows the computer to know if it's running or not
    public bit movInstruction = new bit();                                     //allows store function to know if it needs to store for a mov instruction
    public bit aluInstruction = new bit();                                     //allows store function to know if it needs to store for a alu instruction
    public bit jumpInstruction = new bit();                                    //allows store function to know if it needs to change PC based on a jump
    public bit addToPc = new bit();                                            //allows store function to know if it needs to add to PC based on a BranchIf statement
    public bit CC0 = new bit();                                                //used for conditional jumps being Bit 0
    public bit CC1 = new bit();                                                //used for conditional jumps being Bit 1
    public int movRegister = 0;                                                //which register needs to be written into array
    public int jumpValue = 0;                                                  //holds value to set PC for a jump instruction
    public longword movValue = new longword(0);                          //grabs value for mov instruction
    public longword movResult = new longword(0);                         //grabs value for negative mov instruction
    public longword jumpAddress = new longword(0);                       //used to address of jump
    public longword branchAddress = new longword();                           //address to add to PC if branch condition is true
    public longword branchSign = new longword();                              //allows computer to know if address is negative when adding to PC in branch conditions
    public longword branchCC = new longword();                                //holds conditional code for branch conditions
    public longword PC = new longword();                                       //Create longword pc
    public longword SP = new longword(1020);                             //Stack Pointer start at memory address 1020
    public longword longwordMask = new longword(15);                     //create universal mask being 15 or 0000 0000 0000 0000 0000 0000 0000 1111
    public longword movLongwordMask = new longword(255);                 //mask for mov instruction to grab last 8 bits
    public longword jumpMask = new longword(4095);                       //mask for finding what address to jump to
    public longword branchAddressMask = new longword(511);              //maks for finding address to add to PC in branch conditions
    public longword branchSignAddressMask = new longword(1);            //maks for finding sign of address to add to PC in branch conditions
    public longword branchCCMask = new longword(3);                     //mask to find conditional code for branch conditions
    public longword currentInstruction = new longword(0);                //create a longword to hold the current instruction
    public longword registers[] = new longword[16];                            //create a 16 size longword for holding the registers
    public longword op1 = new longword(0);                               //op1 or value in R1
    public longword op2 = new longword(0);                               //op2 or value in R2
    public longword op3 = new longword(0);                               //op3 or value in R3
    public longword result = new longword(0);                            //value in R3 or result

    public computer() throws Exception{                                       //constructor to set all values to a new longword of all zeros
        for(int i = 0; i < registers.length; i++){
            registers[i] = new longword();
        }
    }
    void run() throws Exception {                                       //runs until computer is not running based on bit
        while(computerRun.getValue() == true){
            fetch();
            decode();
            execute();
            store();
        }
    }

    //gets opCode that contains operation, R1, R2, R3
    void fetch() throws Exception {
        longword tempInstruction = new longword();
        bit tempBit = new bit();

        currentInstruction.set(computerMemory.read(PC).getSigned());                            //sets currentInstruction to value of what is in computer memory at PC
        tempBit.set(currentInstruction.getBit(31).getValue());
        tempInstruction.copy(currentInstruction);

        //flip order of current instruction to print 0->31 instead of 31->0
        int count = 31;
        for(int i = 0; i < 31; i++){
            currentInstruction.setBit(count, tempInstruction.getBit(i));
            count--;
        }

        currentInstruction.setBit(0, tempBit);
        PC = rippleAdder.add(PC, new longword(2));                                       //adds two bytes to PC or 16 bits
    }

    //gets values from registers
    void decode() throws Exception {
        op1 = registers[currentInstruction.rightShift(24).and(longwordMask).getSigned()];        //sets op1 to the value stored in R1
        op2 = registers[currentInstruction.rightShift(20).and(longwordMask).getSigned()];        //sets op2 to the value stored in R2
        op3 = registers[currentInstruction.rightShift(16).and(longwordMask).getSigned()];        //sets op3 to the value stored in R3
        movRegister = currentInstruction.rightShift(24).and(longwordMask).getSigned();           //sets movRegister to which register needs to be set i.e. R0 or R1
        movValue = currentInstruction.rightShift(16).and(movLongwordMask);                       //gets a longword of just the value of the mov instruction
        branchAddress = currentInstruction.rightShift(16).and(branchAddressMask);               //gets branch address that will be added to PC
        branchSign = currentInstruction.rightShift(25).and(branchSignAddressMask);              //gets branch sign of address that will be added to PC
        branchCC = currentInstruction.rightShift(26).and(branchCCMask);                         //gets conditional code of branch instruction to find what condition it is
    }

    //executes alu operation
    void execute() throws Exception {
        bit operation[] = new bit[4];                                                                       //create bit array for operation
        longword longwordOperation = currentInstruction.rightShift(28).and(longwordMask);          //right shift and mask bits to isolate the operation

        //right side of longwords start at 0
        //gets the first 4 bits in longword and puts it in the operation array
        int count = 3;
        for(int i = 0; i < 4; i++){
            operation[count] = longwordOperation.getBit(i);
            count--;
        }

        //checking if halt instruction
        if (!operation[0].getValue() && !operation[1].getValue() && !operation[2].getValue() && !operation[3].getValue()) {            //0000  halt 0-3 bit print
                computerRun.set(false);
        }
        //mov instruction
        else if(!operation[0].getValue() && !operation[1].getValue() && !operation[2].getValue() && operation[3].getValue()){     //0001 mov
            movInstruction.set(true);
            if(movValue.getBit(7).getValue() == true){                                                                          //checks to see if movValue is a negative and calculate the negative value
                bit[] add = {new bit(true),new bit(true),new bit(true),new bit()};
                longword negative8thbit = new longword(-256);
                movResult= alu.doOp(add, negative8thbit, movValue);
            }
        }
        else if(!operation[0].getValue() && !operation[1].getValue() && operation[2].getValue() && !operation[3].getValue()){      //0010 interrupts
            if(currentInstruction.getBit(16).getValue() == true){
                //print all 1024 bytes of memory to the screen.
                //prints 0->31 bits or backwards due to prints of longword
                for(int i = 0; i <= 1020; i+= 4){
                    System.out.println("Memory at Address [" + i + "]: " + computerMemory.read(new longword(i)));
                }
            }
            else{
                //print all the registers to the screen
                for(int i = 0; i < registers.length; i++){
                    System.out.print("R" + i + ": ");
                    System.out.println(registers[i].getSigned());
                }
            }
        }

        else if(!operation[0].getValue() && !operation[1].getValue() && operation[2].getValue() && operation[3].getValue()){          //0011 jump
               jumpAddress = currentInstruction.rightShift(16).and(jumpMask);
               jumpValue = jumpAddress.getSigned();
               jumpInstruction.set(true);
        }

        else if(!operation[0].getValue() && operation[1].getValue() && !operation[2].getValue() && !operation[3].getValue()){       //0100 comparing
            longword comparedValue = rippleAdder.subtract(op2, op3);
            if(comparedValue.getSigned() > 0){                          //greater then
                CC0.set(true);
                //CC1.set(false);
            }

            else if(comparedValue.getSigned() < 0){                     //less than
                CC0.set(false);
                //CC1.set(false);
            }

            else if(comparedValue.getSigned() == 0){                 //equal
                //CC0.set(false);
                CC1.set(true);
            }
        }

        else if(!operation[0].getValue() && operation[1].getValue() && !operation[2].getValue() && operation[3].getValue()){        //0101 branchIf
            if(branchSign.getBit(0).getValue() == true){
                bit[] add = {new bit(true),new bit(true),new bit(true),new bit()};
                longword negative10thbit = new longword(-512);
                branchAddress = alu.doOp(add, negative10thbit, branchAddress);
            }

            if(branchCC.getBit(0).getValue() == CC0.getValue() && branchCC.getBit(1).getValue() == CC1.getValue()){             //catches all conditions other than not equal and less than
                addToPc.set(true);
            }

            else if(!branchCC.getBit(0).getValue()  && !branchCC.getBit(1).getValue()){        //not equal and less than
                addToPc.set(true);
            }

            else{
                //do nothing error if we here
                throw new Exception("Somehow we are not less than, Equal to, Not Equal to, or Greater than. How is this possible");
            }
        }

        else if(!operation[0].getValue() && operation[1].getValue() && operation[2].getValue() && !operation[3].getValue()) {    //0110 push, pop, call, return instructions
            //reuse branchCC as it gets same 2 bits needed to differentiate push, pop, call, return
            if (!branchCC.getBit(1).getValue() && !branchCC.getBit(0).getValue()){   //push  00
                longword pushRegister = new longword();
                pushRegister = registers[currentInstruction.rightShift(16).and(longwordMask).getSigned()];               //gets value of the register and sets it to pushRegister
                computerMemory.write(SP, pushRegister);                                                                         //writes to the stack memory using pushRegister
                SP = rippleAdder.subtract(SP, new longword(4));                                                           //adds 4 to SP
            }

            else if (!branchCC.getBit(1).getValue() && branchCC.getBit(0).getValue()){   //pop 01
                SP =  rippleAdder.add(SP, new longword(4));                                                              //adds 4 to SP
                longword popRegisterValue = new longword();
                popRegisterValue.set(computerMemory.read(SP).getSigned());                                                    //gets the value from memory on the stack at SP
                longword popRegister = new longword();
                popRegister = currentInstruction.rightShift(16).and(longwordMask);                                    //gets what register number i.e. R0 or R1 etc.
                registers[popRegister.getSigned()] = popRegisterValue;                                                       //sets register value (popRegisterValue) at register number (popRegister)
                computerMemory.write(SP, new longword(0));                                                             //pops the register value off the stack
            }

            else if (branchCC.getBit(1).getValue() && !branchCC.getBit(0).getValue()){   //call 10
                longword callAddress = new longword();
                callAddress = currentInstruction.rightShift(16).and(new longword(1023));                       //gets the address value by taking currentInstruction, right-shifting 16 and masking it with 1023
                computerMemory.write(SP, PC);                                                                               //writes to the stack memory the PC counter for jumping back i.e. return
                PC.set(callAddress.getSigned());                                                                            //sets PC to the callAddress to jump to that address
                SP = rippleAdder.subtract(SP, new longword(4));                                                       //pushes stack memory by subtracting SP by 4
            }

            else if (branchCC.getBit(1).getValue() && branchCC.getBit(0).getValue()){    //return 11
                SP = rippleAdder.add(SP, new longword(4));                                                            //pops the stack by adding 4 to SP
                PC.set(computerMemory.read(SP).getSigned());                                                                //returns to address at SP and sets it to PC, therefor jumping to the address in the stack memory
            }

            else{                                                                                                           //throws error if computer did not read a push, pop, call or return instruction
                throw new Exception("Neither push, pop, call, or return occurring");
            }
        }

        else {
            aluInstruction.set(true);
            result = alu.doOp(operation, op1, op2);                                                             //result is set by the operation and values in op1 and op2
        }
    }

    //stores alu results in register array
    void store() throws Exception {
        //storing if instruction is mov 0001
        if(movInstruction.getValue() == true){
            if(movValue.getBit(7).getValue() == true){
                registers[movRegister].copy(movResult);
            }
            else{
                registers[movRegister].copy(movValue);
            }
            movInstruction.clear();
        }
        //storing if instruction is an alu operation
        else if(aluInstruction.getValue() == true){
            aluInstruction.clear();
            int regResult = currentInstruction.rightShift(16).and(longwordMask).getSigned();             //create what register number is at the end of current instruction
            registers[regResult].copy(result);                                                                  //stores the result done by alu into the array location of the register number based on regResult
        }
        //set PC if a jump instruction occurs
        else if(jumpInstruction.getValue() == true){
            PC.set(jumpValue);
            jumpInstruction.clear();
        }
        //adds to PC is a branch instruction occurs
        else if(addToPc.getValue() == true){
            PC = rippleAdder.add(PC, branchAddress);
            addToPc.clear();
        }
        else{
            //do nothing
        }
    }

    //loads instructions into memory to hold two instructions per longword
    void preload(String[] instruction) throws Exception{
        for(int i = 0; i < instruction.length; i++){
            longword memoryString = new longword();
            for(int j = 15; j >= 0; j--){
                if(instruction[i].charAt(j) == '1'){
                    memoryString.setBit(j, new bit(true));
                }
            }
            computerMemory.write(new longword(i * 2), memoryString);
        }
    }
}
