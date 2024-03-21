package core.security.chapter03.config;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String salt1 = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrpt = "HELLO";

        BytesEncryptor e = Encryptors.standard(password, salt1);
        byte[] encrypted = e.encrypt(valueToEncrpt.getBytes());
        byte[] decrypted = e.decrypt(encrypted);
        System.out.println("encrypted = " + Arrays.toString(encrypted));
        System.out.println("decrypted = " + Arrays.toString(decrypted));
        List<String> a = Arrays.asList(Arrays.toString(decrypted));
        for (String str : a) {
            System.out.println("str = " + str);
        }
    }
    public static void main2(String[] args) {
        StringKeyGenerator keyGenerator = KeyGenerators.string();
        String salt1 = keyGenerator.generateKey();
        System.out.println("salt1 = " + salt1);

        System.out.println("========================");
        BytesKeyGenerator keyGenerator1 = KeyGenerators.secureRandom();
        byte[] key = keyGenerator1.generateKey();
        int keyLength = keyGenerator1.getKeyLength();
        System.out.println("key = " + key);
        for (var e : key) {
            System.out.println("e = " + e);
        }
        System.out.println("keyLength = " + keyLength);
        BytesKeyGenerator keyGenerator2 = KeyGenerators.secureRandom(16);
        byte[] key2 = keyGenerator2.generateKey();
        int keyLength2 = keyGenerator2.getKeyLength();
        for (var e : key2) {
            System.out.println("e = " + e);
        }
        System.out.println("keyLength2 = " + keyLength2);
        BytesKeyGenerator keyGenerator3 = KeyGenerators.shared(16);
        byte[] key3 = keyGenerator3.generateKey();
        byte[] key4 = keyGenerator3.generateKey();
        System.out.println("key3 = " + key3);
        for (var e : key3) {
            System.out.println("e = " + e);
        }
        System.out.println("key4 = " + key4);
        for (var e : key4) {
            System.out.println("e = " + e);
        }
        System.out.println("========================");


        String salt = KeyGenerators.string().generateKey();
        System.out.println("salt = " + salt);
        String password = "secret";
        String valueToEncrypt = "HELLO";
        TextEncryptor e = Encryptors.text(password, salt);
        String encrypt1 = e.encrypt(valueToEncrypt);
        String encrypt2 = e.encrypt(valueToEncrypt);
        System.out.println("encrypt1 = " + encrypt1);
        System.out.println("encrypt2 = " + encrypt2);
    }

}
