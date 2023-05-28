import java.math.BigInteger;

import static DiffieHellman.*;

public class Main {


    public static void main(String[] args) {
        var DH = new DiffieHellman();

        // NOT SEND
        BigInteger bobSimpleNumber = BigInteger.valueOf(RAND(10, 1000)); // rand bob
        
        // send alice getGenerator and getPrimeNumber
        BigInteger bobPublicKey = POW(DH.getGenerator(), bobSimpleNumber, DH.getPrimeNumber()); 
        
        // NOT SEND - alicePrivateKey
        // send bob bobPublicKey
        BigInteger alicePrivateKey = DH.getPrivateKey(bobPublicKey); 
      
        // NOT SEND - bobPrivateKey
        BigInteger bobPrivateKey = POW(DH.getPublicKey(), bobPrivateNumber, DH.getPrimeNumber()); // not send


        // CHECK
        System.out.println("alicePrivateKey = " + alicePrivateKey + " bobPrivateKey = " + bobPrivateKey);
        
        if (!(alicePrivateKey.equals(bobPrivateKey))) {
            System.err.println("INVALID");
        }
    }
}
