import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA
{

    private BigInteger p, q, n, phi, e, d;
    private SecureRandom random = new SecureRandom();

    public RSA(int bitLength)
    {
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);
        while (p.equals(q))
            q = BigInteger.probablePrime(bitLength / 2, random);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        do
            e = new BigInteger(bitLength / 2, random);
        while (!e.gcd(phi).equals(BigInteger.ONE) || e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0);
        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message)
    {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger ciphertext)
    {
        return ciphertext.modPow(d, n);
    }

    public BigInteger getPublicKeyN()
    {
        return n;
    }

    public BigInteger getPublicKeyE()
    {
        return e;
    }

    public BigInteger getPrivateKeyD()
    {
        return d;
    }

    public static void main(String[] args)
    {
        RSA rsa = new RSA(1024);
        System.out.println("Public Key (n, e): (" + rsa.getPublicKeyN() + ", " + rsa.getPublicKeyE() + ")");
        System.out.println("Private Key (d): " + rsa.getPrivateKeyD());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message to encrypt: ");
        String message = scanner.nextLine();
        BigInteger messageAsInt = new BigInteger(message.getBytes());

        BigInteger encrypted = rsa.encrypt(messageAsInt);
        System.out.println("Encrypted Message: " + encrypted);

        BigInteger decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted Message: " + new String(decrypted.toByteArray()));
    }
}