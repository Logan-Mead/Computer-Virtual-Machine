interface bitInterface{
    void set(boolean value);
    void toggle();
    void set();
    void clear();
    boolean getValue();
    bit and(bit other);
    bit or(bit other);
    bit xor(bit other);
    bit not();
    String toString();
}

public class bit implements bitInterface{
    private boolean bit;
public bit(){                                   //default constructor to set false
    bit = false;
}

public bit(boolean value){                      //constructor to set bit to what value is brought in
    this.bit = value;
}

//Methods

    public void set(boolean value){             //sets bit as either true or false
        if(value == false){
            this.bit = false;
        }
        else if(value == true){
            this.bit = true;
        }
    }

    public void toggle(){                      //toggles the bit to opposite of what the bit is set to
        if(this.bit == false){
            this.bit = true;
        }
        else if(this.bit == true){
            this.bit = false;
        }
    }

    public void set(){                          //sets bit to true
        set(true);
    }

    public void clear(){                        //sets bit to false
        set(false);
    }

    public boolean getValue(){                  //returns current value of bit
        return bit;
    }

    public bit and(bit value){                  //if both bit and value is true return true, else return false
        bit andBit = new bit();
        if(this.bit == true){
            if(value.getValue() == true){
                andBit.set(true);
            }
        }
        else{
            andBit.set(false);
        }
        return andBit;
    }

    public bit or(bit value){                 //if bit or value is true return true, else return false
        bit orBit = new bit();
        if(this.bit == true){
            orBit.set(true);
        }

        else if(value.getValue() == true){
            orBit.set(true);
        }

        else {
            orBit.set(false);
        }
        return orBit;
    }

    public bit xor(bit value){                      //if bit is opposite of value return true else return false
        bit xorBit = new bit();
        if(this.bit == value.bit){
            xorBit.set(false);
            return xorBit;
        }
        xorBit.set(true);
        return xorBit;
    }

    public bit not(){                                      //inverts current bit
        if(this.bit == true){
            bit notBit = new bit();
            notBit.set(false);
            return notBit;
        }
        else{
            bit notBit = new bit();
            notBit.set(true);
            return notBit;
        }
    }
    @Override                                       //if bit is true return t, if bit is false return f
    public String toString(){
        if(this.bit == true){
            return "t";
        }
        else{
            return "f";
        }

    }
}