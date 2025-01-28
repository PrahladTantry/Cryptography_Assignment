import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class ElGamal
{

    private BigInteger p, e1, e2, d, r;
    private SecureRandom random = new SecureRandom();

    public ElGamal(int bitLength)
    {
        p = BigInteger.probablePrime(bitLength, random);
        e1 = new BigInteger(bitLength - 1, random).mod(p);
        d = new BigInteger(bitLength - 1, random).mod(p.subtract(BigInteger.ONE));
        e2 = e1.modPow(d, p);
    }

    public BigInteger[] encrypt(BigInteger message)
    {
        r = new BigInteger(p.bitLength() - 1, random).mod(p.subtract(BigInteger.ONE));
        BigInteger c1 = e1.modPow(r, p);
        BigInteger c2 = message.multiply(e2.modPow(r, p)).mod(p);
        return new BigInteger[]{c1, c2};
    }

    public BigInteger decrypt(BigInteger c1, BigInteger c2)
    {
        BigInteger temp = c1.modPow(d, p).modInverse(p);
        return c2.multiply(temp).mod(p);
    }

    public BigInteger getP()
    {
        return p;
    }

    public BigInteger getE1()
    {
        return e1;
    }

    public BigInteger getE2()
    {
        return e2;
    }

    public BigInteger getD()
    {
        return d;
    }

    public static void main(String[] args)
    {
        ElGamal elGamal = new ElGamal(512);
        System.out.println("Public Key (p, e1, e2): (" + elGamal.getP() + ", " + elGamal.getE1() + ", " + elGamal.getE2() + ")");
        System.out.println("Private Key (d): " + elGamal.getD());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message to encrypt: ");
        String message = scanner.nextLine();
        BigInteger messageAsInt = new BigInteger(message.getBytes());

        BigInteger[] ciphertext = elGamal.encrypt(messageAsInt);
        System.out.println("Encrypted Message: c1 = " + ciphertext[0] + ", c2 = " + ciphertext[1]);

        BigInteger decrypted = elGamal.decrypt(ciphertext[0], ciphertext[1]);
        System.out.println("Decrypted Message: " + new String(decrypted.toByteArray()));
    }
}
