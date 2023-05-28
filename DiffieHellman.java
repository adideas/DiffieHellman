import java.math.BigInteger;
import java.util.Random;

final public class DiffieHellman {
    private final BigInteger generator; // Q

    private final BigInteger primeNumber; // P => F(x) = (Q * 2) + 1

    private final BigInteger privateKey;
    private final BigInteger publicKey;

    public DiffieHellman() {
        generator = GENERATOR();
        // P = (Q * 2) + 1
        primeNumber = generator.multiply(BigInteger.TWO).add(BigInteger.ONE);
        // Simple secret value (NOT SEND)
        privateKey = BigInteger.valueOf(RAND(10, 1000));
        // Public value (SEND)
        publicKey = POW(generator, privateKey, primeNumber);
    }

    public BigInteger getGenerator() {
        return generator;
    }

    public BigInteger getPrimeNumber() {
        return primeNumber;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey(BigInteger publicKey) {
        return POW(publicKey, privateKey, getPrimeNumber());
    }

    @SuppressWarnings("SameParameterValue")
    public static Long RAND(long from, long to) {
        return new Random().nextLong(to - from) + from;
    }

    public static BigInteger RAND(BigInteger from, BigInteger to) {
        BigInteger middle = to.subtract(from);
        BigInteger result = new BigInteger(to.bitLength(), new Random());

        if (result.compareTo(from) < 0) {
            result = result.add(from);
        }
        if (result.compareTo(middle) >= 0) {
            result = result.mod(middle).add(from);
        }

        return result;
    }

    public static BigInteger POW(BigInteger value, BigInteger pow, BigInteger mod) {
        // F(x) = (value ^ pow) % mod
        return value.modPow(pow, mod);
    }

    public static BigInteger GENERATOR() {
        return RAND(
                BigInteger.TWO.pow(64),
                BigInteger.TWO.pow(66)
        ).nextProbablePrime();
    }
}
