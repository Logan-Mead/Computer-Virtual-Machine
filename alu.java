
public class alu {

    public static longword doOp(bit[] operation, longword a, longword b) throws Exception {
        //operation array reads left to right, so at 0 is left most bit
        if(operation[0].getValue() && !operation[1].getValue() && !operation[2].getValue() && !operation[3].getValue()){                 //and function, operation = 1000, a and b
            return a.and(b);
        }

        else if(operation[0].getValue() && !operation[1].getValue() && !operation[2].getValue() && operation[3].getValue()){              //or function, operation = 1001, a or b
            return a.or(b);
        }

        else if(operation[0].getValue() && !operation[1].getValue() && operation[2].getValue() && !operation[3].getValue()){              //xor function, operation = 1010, a xor b
            return a.xor(b);
        }

        else if(operation[0].getValue() && !operation[1].getValue() && operation[2].getValue() && operation[3].getValue()){              //not function, operation = 1011, a not
            return a.not();
        }

        else if(operation[0].getValue() && operation[1].getValue() && !operation[2].getValue() && !operation[3].getValue()){              //left shift function, operation = 1100, a left-shifted by b amount
            return a.leftShift((int) b.getUnsigned());
        }

        else if(operation[0].getValue() && operation[1].getValue() && !operation[2].getValue() && operation[3].getValue()){              //right shift function, operation = 1101. a right-shifted by b amount
            return a.rightShift((int) b.getUnsigned());
        }

        else if(operation[0].getValue() && operation[1].getValue() && operation[2].getValue() && !operation[3].getValue()){              //add function, operation = 1110, a + b
            return rippleAdder.add(a, b);
        }

        else if(operation[0].getValue() && operation[1].getValue() && operation[2].getValue() && operation[3].getValue()){              //subtract function, operation = 1111, a - b
            return rippleAdder.subtract(a, b);
        }

        else if(!operation[0].getValue() && operation[1].getValue() && operation[2].getValue() && operation[3].getValue()){              //multiply function, operation = 0111, a x b
            return multiplier.multiply(a, b);
        }
        return null;
    }
}
