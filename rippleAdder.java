public class rippleAdder{

    public static longword add(longword a, longword b) {            //method for ripple adder
        //creating new longwords needed for ripple adder
        longword carryIn = new longword();
        longword carryOut = new longword();
        longword sum = new longword();

        //equation used from professor Phipps slides
        for(int i = 0; i < 32; i++){
            sum.setBit(i, a.getBit(i).xor(b.getBit(i).xor(carryIn.getBit(0))));
            carryOut.setBit(0,(a.getBit(i).and(b.getBit(i))).or((a.getBit(i).xor(b.getBit(i))).and(carryIn.getBit(0))));
            carryIn.setBit(0, carryOut.getBit(0));
        }
        return sum;
    }

    public static longword subtract(longword a, longword b) {       //method for ripple subtractor
        longword temp = b.not();                                    //creates temp as a longword and the negated value of longword b
        longword add1 = new longword(1);                      //creates add1 as a longword and sets it to value of 1
        longword subtract = rippleAdder.add(a, temp);               //adds longword a and longword temp
        return rippleAdder.add(subtract, add1);                     //adds 1 to subtracted value using add method and returns it as a longword
    }
}
