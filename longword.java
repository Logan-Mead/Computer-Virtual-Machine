interface ILongword {
    bit getBit(int i);                          // Get bit i
    void setBit(int i, bit value);              // set bit i's value
    longword and(longword other);               // and two longwords, returning a third
    longword or(longword other);                // or two longwords, returning a third
    longword xor(longword other);               // xor two longwords, returning a third
    longword not();                             // negate this longword, creating another
    longword rightShift(int amount) throws Exception;            // rightshift this longword by amount bits, creating a new longword
    longword leftShift(int amount) throws Exception;             // leftshift this longword by amount bits, creating a new longword
    @Override
    String toString();                          // returns a comma separated string of 0's and 1's: "0,0,0,0,0 (etcetera)" for example
    long getUnsigned();                         // returns the value of this longword as a long
    int getSigned();                            // returns the value of this longword as an int
    void copy(longword other);                  // copies the values of the bits from another longword into this one
    void set(int value);                        // set the value of the bits of this longword (used for tests)
}

public class longword implements ILongword{
    private bit[] longWord;

    public longword(){                              //default constructor to set all bits false
       this.longWord = new bit[32];
       for(int i = 0; i < 32; i++){
           this.setBit(i,  new bit(false));
       }
    }

    public longword(boolean value){                 //constructor to set all bits to value brought in
        this.longWord = new bit[32];
        for(int i = 0; i < 32; i++){
            this.setBit(i,  new bit(value));
        }
    }

    public longword(int value){                 //constructor to set as value
        this.longWord = new bit[32];
        this.set(value);
    }

    //Methods
    public bit getBit(int i){                           //get bit i
        return this.longWord[i];
    }

    public void setBit(int i, bit value){               //set bit i's value
        this.longWord[i] = value;
    }

    public longword and(longword other){                //and two longwords, returning a third
        longword andLong = new longword();
        for(int i = 0; i < 32; i++){
            andLong.setBit(i, this.getBit(i).and(other.getBit(i)));

        }
        return andLong;
    }

    public longword or(longword other){                 //or two longwords, returning a third
        longword orLong = new longword();
        for(int i = 0; i < 32; i++) {
            orLong.setBit(i, this.getBit(i).or(other.getBit(i)));

        }
        return orLong;
    }

    public longword xor(longword other){                // xor two longwords, returning a third
        longword xorLong = new longword();
        for(int i = 0; i < 32; i++){
            xorLong.setBit(i, this.getBit(i).xor(other.getBit(i)));

        }
        return xorLong;
    }

    public longword not(){                             //negate this longword, creating another
        longword notLong = new longword();
        for(int i = 0; i < 32; i++){
            notLong.setBit(i, this.getBit(i).not());
        }
        return notLong;
    }

    public longword rightShift(int amount) throws Exception {                           //shifts longword to the right by amount
        //error checks to see if amount is within array
        if(amount > 31){
            throw new Exception("Shift amount greater then " + this.longWord.length);
        }

        else if(amount < 0){
            throw new Exception("Shift amount less then 0!");
        }
        longword longRight = new longword();
        int leng = this.longWord.length;

        //sets longRight to longwords value shifted amount from the right
        for(int i = 0; i < leng - amount; i++){
            longRight.setBit(i, this.getBit(i + amount));
        }
        return longRight;
    }

    public longword leftShift(int amount) throws Exception {                            //shifts longword to the left by amount
        //error checks to see if amount is within array
        if(amount > 31){
            throw new Exception("Shift amount greater then " + this.longWord.length);
        }

        else if(amount < 0){
            throw new Exception("Shift amount less then 0!");
        }
        longword longLeft = new longword();
        int leng = this.longWord.length;

        //sets longLeft to longwords value shifted amount from the left
        for(int i = leng - 1; i >= amount; i--){
            longLeft.setBit(i, this.getBit(i - amount));
        }

        return longLeft;
    }

    @Override
    public String toString(){                           //prints string for longword based on using bit toString()
        String longString = "";
      for(int i = 31; i >= 0; i = i - 1){
          longString = longString + this.getBit(i).toString() + ",";
      }
        return longString;
    }

    public long getUnsigned() {                         // returns the value of this longword as a long
        long longUnsigned = 0;
        int counter = this.longWord.length - 1;
        for(int i = this.longWord.length - 1; i >= 0; i--){
            if(this.getBit(i).getValue() == true) {
                longUnsigned = (long) (longUnsigned + Math.pow(2, counter));
            }
            counter = counter - 1;
        }
        return longUnsigned;
    }

    public int getSigned() {                            // returns the value of this longword as an int
        int longSigned = 0;
        int counter = this.longWord.length - 1;
        longword saveLong = new longword();
        saveLong.copy(this);

        //checks if the two's complement long to see if its positive and calls to find the unsigned value
        if(this.getBit(31).getValue() == false){
            return (int) this.getUnsigned();
        }

        else {

            //negates all values and add one to value and set longword as value
            for (int i = 0; i < this.longWord.length; i++) {
                this.setBit(i, this.getBit(i).not());
            }
            long value = this.getUnsigned();
            value++;
            this.set((int) value);

            //finds the integer value of longword
            for (int i = this.longWord.length - 1; i >= 0; i--) {
                if (this.getBit(i).getValue() == true) {
                    longSigned = (int) (longSigned + Math.pow(2, counter));
                }
                counter = counter - 1;
            }

            //negates longSigned to be a negative number
            longSigned = longSigned / -1;

            //gives orignal longword back
            this.copy(saveLong);
            return longSigned;
        }
    }

    public void copy(longword other) {                  // copies the values of the bits from another longword into this one
        for(int i = 0; i < 32; i++){
            this.setBit(i, other.getBit(i));
        }

    }

    public void set(int value){                       // set the value of the bits of this longword (used for tests)
        //positive values
        if(value >= 0) {
            for (int i = 31; i >= 0; i = i - 1) {
                if(value >= Math.pow(2,i)) {
                    this.setBit(i, new bit(true));
                    value = (int) (value - Math.pow(2,i));
                }

                else{
                    this.setBit(i, new bit(false));
                }
            }
        }

        //negative values
        else if (value < 0){
            value = value + 1;
            for(int i = 31; i >= 0; i = i - 1){
                if(Math.abs(value) >= Math.pow(2,i) ) {
                    this.setBit(i, new bit(false));
                    value = (int) (Math.abs(value) - Math.pow(2,i));
                }

                else{
                    this.setBit(i, new bit(true));
                }
            }
        }
    }

    public String booleanTobinary(){
        String binaryOutput = "";
        for(int i = this.longWord.length - 1; i >= 0; i--) {
            if(this.longWord[i].getValue()) {
                binaryOutput = binaryOutput + "1";
            }
            else {
                binaryOutput = binaryOutput + "0";
            }
        }

        return binaryOutput;
    }
}
