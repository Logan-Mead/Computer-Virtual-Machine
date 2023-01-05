public class multiplier {

    public static longword multiply(longword a, longword b) throws Exception {                  //method for multiply
        longword finalResult = new longword();
        longword currentResult = new longword();
        int count = 0;
        for(int i = 0; i < 32; i++){
            for(int j = 0; j < 32; j++){                                                        //uses b bit at count ands them with each bit in a and sets it to current bit
              currentResult.setBit(j, a.getBit(j).and(b.getBit(count)));
            }
            finalResult = rippleAdder.add(finalResult, currentResult.leftShift(count));         //sets final result to adding previous final result and current result that is left shifted by count
            count++;
        }
        return finalResult;
    }
}
