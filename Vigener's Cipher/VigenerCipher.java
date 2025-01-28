public class VigenereCipher 
{
    private static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plaintext, String key) 
    {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();
        int keyLength = key.length();

        for (int i = 0, j = 0; i < plaintext.length(); i++) 
        {
            char c = plaintext.charAt(i);
            if (Character.isLetter(c)) 
            {
                int x = c - 'A';
                int k = key.charAt(j % keyLength) - 'A';
                int y = (x + k) % ALPHABET_SIZE;
                ciphertext.append((char) (y + 'A'));
                j++;
            } 
            else 
                ciphertext.append(c);
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) 
    {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();
        int keyLength = key.length();

        for (int i = 0, j = 0; i < ciphertext.length(); i++) 
        {
            char c = ciphertext.charAt(i);
            if (Character.isLetter(c)) 
            {
                int y = c - 'A';
                int k = key.charAt(j % keyLength) - 'A';
                int x = (y - k + ALPHABET_SIZE) % ALPHABET_SIZE;
                plaintext.append((char) (x + 'A'));
                j++;
            } 
            else 
                plaintext.append(c);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) 
    {
        String plaintext = "HELLO";
        String key = "KEY";

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted: " + decryptedText);
    }
}