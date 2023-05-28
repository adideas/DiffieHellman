import java.math.BigInteger;

import static DiffieHellman.*;

public class Main {


    public static void main(String[] args) {
        var DH = new DiffieHellman();

        BigInteger bobPrivateNumber = BigInteger.valueOf(RAND(10, 1000)); // rand bob
        BigInteger bobKey = POW(DH.getGenerator(), bobPrivateNumber, DH.getPrimeNumber()); // send alice - get bob

        BigInteger alicePublicKey = DH.getPrivateKey(bobKey); // not send
      
        BigInteger bobPublicKey = POW(DH.getPublicKey(), bobPrivateNumber, DH.getPrimeNumber());


        System.out.println("alicePublicKey = " + alicePublicKey + " bobPublicKey = " + bobPublicKey);
        
        if (!(alicePublicKey.equals(bobPublicKey))) {
            System.err.println("INVALID");
        }
    }
}
