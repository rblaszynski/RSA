import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class RSA {
    public static void main(String[] args) {
        int keyLength = 1024;
        Random random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(keyLength / 2, random);
        BigInteger q = BigInteger.probablePrime(keyLength / 2, random);
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger e;
        do e = new BigInteger(phi.bitLength(), random);
        while (e.compareTo(BigInteger.ONE) <= 0
                || e.compareTo(phi) >= 0
                || !e.gcd(phi).equals(BigInteger.ONE));
        BigInteger d = e.modInverse(phi);

        String message = "Lorem ipsum dolor sit amet, consectetuer adipiscin";
        System.out.println("Message: " + message);

        ArrayList<BigInteger> arrayList = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            BigInteger temp = new BigInteger(String.valueOf((int) message.charAt(i)));
            arrayList.add(temp.modPow(e, n));
        }
        System.out.println(arrayList);

        StringBuilder encryptedMessage = new StringBuilder();
        for (BigInteger anArrayList : arrayList) {
            encryptedMessage.append(Character.toString((char) anArrayList.intValue()));
        }
        System.out.println("Encrypted: " + encryptedMessage);

        ArrayList<BigInteger> decryptedList = new ArrayList<>();
        for (BigInteger anArrayList : arrayList) {
            decryptedList.add(anArrayList.modPow(d, n));
        }
        System.out.println(decryptedList);

        StringBuilder decryptedMessage = new StringBuilder();
        for (BigInteger aDecryptedList : decryptedList) {
            decryptedMessage.append(Character.toString((char) aDecryptedList.intValue()));
        }
        System.out.println("Decrypted: " + decryptedMessage);
    }
}
