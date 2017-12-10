package encdec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import javax.crypto.SecretKeyFactory;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.IvParameterSpec;
import sun.misc.*;
public class EnDe_crypter {
    Cipher dcipher;

    byte[] salt = new String("12345678").getBytes();
    int iterationCount = 1024;
    int keyStrength = 128;
    SecretKey key;
    byte[] iv;
    public  byte[] getIv() {
    	return iv;
    }
    public EnDe_crypter(String passPhrase)  {
        SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
	        KeySpec spec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount, keyStrength);
	        SecretKey tmp = factory.generateSecret(spec);
	        key = new SecretKeySpec(tmp.getEncoded(), "AES");
	        //System.out.println(tmp.getAlgorithm());
	        dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("error on algorithm selection");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			System.out.println("invalid key");
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			System.out.println("No such padding");
			e.printStackTrace();
		}
    }

    public String encrypt(String data) throws Exception {
        dcipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters params = dcipher.getParameters();
        iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        
//        System.out.println("enc: "+java.util.Arrays.toString(iv));
        byte[] utf8EncryptedData = dcipher.doFinal(data.getBytes());
        String base64EncryptedData = new BASE64Encoder().encode(utf8EncryptedData);
        return base64EncryptedData;
    }

    public String decrypt(String base64EncryptedData, byte iv[]) {
        try {
	        dcipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
	        byte[] decryptedData = new BASE64Decoder().decodeBuffer(base64EncryptedData);
	        byte[] utf8 = dcipher.doFinal(decryptedData);
	        return new String(utf8, "UTF8");
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			System.out.println("error on key");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("decrypte: io ");
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			System.out.println("illegal block size");
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			System.out.println("Bad Padding");
			e.printStackTrace();
		}
        System.out.println("return null");
        return null;
    }
    
    public static void main(String args[]) throws Exception {
        EnDe_crypter decrypter = new EnDe_crypter("1");
        String encrypted = decrypter.encrypt("the quick brown fox jumps over the lazy dog");
        System.out.println(encrypted);
        byte[] iv = decrypter.iv;
        String s = new BASE64Encoder().encodeBuffer(iv);
        byte[] v = new BASE64Decoder().decodeBuffer(s);
        decrypter = new EnDe_crypter("1");

        String decrypted = decrypter.decrypt(encrypted, v);
        System.out.println(decrypted);
    }
}