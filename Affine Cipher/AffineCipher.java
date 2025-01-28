import java.util.Scanner;

public class AffineCipher {
    private static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plaintext, int a, int b) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray())
            if (Character.isLetter(c)) {
                int x = Character.toUpperCase(c) - 'A';
                int y = (a * x + b) % ALPHABET_SIZE;
                ciphertext.append((char) (y + 'A'));
            } else
                ciphertext.append(c);
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int a, int b) {
        StringBuilder plaintext = new StringBuilder();
        int aInverse = modInverse(a, ALPHABET_SIZE);
        for (char c : ciphertext.toCharArray())
            if (Character.isLetter(c)) {
                int y = Character.toUpperCase(c) - 'A';
                int x = (aInverse * (y - b + ALPHABET_SIZE)) % ALPHABET_SIZE;
                plaintext.append((char) (x + 'A'));
            } else
                plaintext.append(c);
        return plaintext.toString();
    }

    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        throw new IllegalArgumentException("Modular inverse does not exist for the given key 'a'.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the key 'a' (must be coprime with 26): ");
        int a = scanner.nextInt();

        System.out.print("Enter the key 'b': ");
        int b = scanner.nextInt();

        String ciphertext = encrypt(plaintext, a, b);
        System.out.println("Encrypted: " + ciphertext);

        String decryptedText = decrypt(ciphertext, a, b);
        System.out.println("Decrypted: " + decryptedText);
    }
}
