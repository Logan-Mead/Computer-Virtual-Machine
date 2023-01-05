public class memory {
    private bit[] memory;
    public memory(){
        this.memory = new bit[8192];                                    //create an array of bits for memory
        for(int i = 0; i < this.memory.length; i++){                    //set all bits to false
            memory[i] = new bit();
        }
    }

    public longword read(longword address) throws Exception {
        int count = 0;
        int addressLocation = address.getSigned() * 8;                         //each address can contain a byte
        //checks if the address is out of bounds, 8192/8 is 256 bytes
        if(address.getSigned() > 1020){
            throw new Exception("Address is too large");
        }

        else if(address.getSigned() < 0){
            throw new Exception("Address is too small");
        }

        longword readLongword = new longword();
        for(int i = addressLocation; i < addressLocation + 32; i++){            //sets readLongword to what longword is in memory
            readLongword.setBit(count, this.memory[i]);
            count++;
        }
        return readLongword;
    }

    public void write(longword address, longword value) throws Exception {
        int count = 0;
        int addressLocation = address.getSigned() * 8;                         //each address can contain a byte
        //checks if the address is out of bounds, 8192/8 is 256 bytes
        if(address.getSigned() > 1020){
            throw new Exception("Address is too large");
        }

        else if(address.getSigned() < 0){
            throw new Exception("Address is too small");
        }

        for(int i = addressLocation; i < addressLocation + 32; i++){            //sets in memory the brought in longword
            this.memory[i].set(value.getBit(count).getValue());
            //memory[i] = value.getBit(count);
            count++;
        }
    }
}
