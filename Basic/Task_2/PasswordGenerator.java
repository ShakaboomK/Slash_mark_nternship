import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static class Password {
        private final String plaintext;
        private final String encrypted;

        public Password(String plaintext, String encrypted) {
            this.plaintext = plaintext;
            this.encrypted = encrypted;
        }

        public String getPlaintext() {
            return plaintext;
        }

        public String getEncrypted() {
            return encrypted;
        }
    }

    public static Password generatePassword(int length, boolean includeLowercase,
                                            boolean includeUppercase, boolean includeNumbers,
                                            boolean includeSpecial) throws Exception {
        // Validate parameters
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters");
        }
        if (!(includeLowercase || includeUppercase || includeNumbers || includeSpecial)) {
            throw new IllegalArgumentException("At least one character type must be selected");
        }

        StringBuilder characters = new StringBuilder();
        StringBuilder password = new StringBuilder();

        // Build character pool based on selected options
        if (includeLowercase) characters.append(LOWERCASE);
        if (includeUppercase) characters.append(UPPERCASE);
        if (includeNumbers) characters.append(NUMBERS);
        if (includeSpecial) characters.append(SPECIAL);

        // Ensure at least one character from each selected type
        if (includeLowercase) password.append(LOWERCASE.charAt(secureRandom.nextInt(LOWERCASE.length())));
        if (includeUppercase) password.append(UPPERCASE.charAt(secureRandom.nextInt(UPPERCASE.length())));
        if (includeNumbers) password.append(NUMBERS.charAt(secureRandom.nextInt(NUMBERS.length())));
        if (includeSpecial) password.append(SPECIAL.charAt(secureRandom.nextInt(SPECIAL.length())));

        // Fill the remaining length with random characters
        String allCharacters = characters.toString();
        for (int i = password.length(); i < length; i++) {
            password.append(allCharacters.charAt(secureRandom.nextInt(allCharacters.length())));
        }

        // Shuffle the password
        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int j = secureRandom.nextInt(i + 1);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }

        String plaintext = new String(passwordArray);
        String encrypted = encryptPassword(plaintext);

        return new Password(plaintext, encrypted);
    }

    private static String encryptPassword(String password) throws Exception {
        // Generate a secret key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        // Generate IV
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Initialize cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Encrypt
        byte[] encrypted = cipher.doFinal(password.getBytes());

        // Combine IV and encrypted part
        byte[] combined = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    public static void main(String[] args) {
        try {
            // Example usage
            Password password = generatePassword(16, true, true, true, true);
            System.out.println("Generated Password: " + password.getPlaintext());
            System.out.println("Encrypted Form: " + password.getEncrypted());
        } catch (Exception e) {
            System.err.println("Error generating password: " + e.getMessage());
        }
    }
}