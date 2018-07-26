package com.test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.RSAKeyParameters;

public class Encryptor {
	
	byte[] HEADER1_DATA = "VERSION_1.1".getBytes();
	byte[] HEADER_DATA = "VERSION_1.0".getBytes();
	private static final String RSA_NOPADDING_TRANSFORMATION = "RSA/ECB/OAEPPADDING";
	private static final BigInteger EXPONENT = new BigInteger("1", 16);
	
	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	
	/**
	 * The private key exponent
	 */

	private static final Map<String, String> privateKeyMap = new HashMap<String, String>();
	/**
	 * size of encrypted AES secret key
	 */
	int LEGACY_SECRET_KEY_SIZE = 128;
	/**
	 * size of X509 RSA public key in bytes
	 */
	int LEGACY_PUBLIC_KEY_SIZE = 162;
	
	int PUBLIC_KEY_SIZE = 294;

	/**
	 * size of randomly generated enrollment id at client side
	 */
	int EID_SIZE = 32;
	
	/**
	 * size of encrypted AES secret key
	 */
	int SECRET_KEY_SIZE = 256;
	/**
	 * Default Size of the HMAC/Hash Value in bytes
	 */
	int HMAC_SIZE = 32;
	
	private MessageDigest digest;
	
	public static void main(String[] args) {
//		encrypt();
		Encryptor encryptor = new Encryptor();
		
		byte [] plainText = "Pankaj".getBytes();
//		byte[] publicKey = Base64.decodeBase64(
//				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqzrQssGCUuM6p/3FFf2ILXPF0QYrbQACdkGIKFMasAkyMvjZEq2/PqlBW9qsUgstCJvk1dwgC8SjLFkqjy7Yt/7vghZyBJXC4bQ6Oxiz5mPJCjBMRucHfCPbbbnacBfeDfN6tXiBXxqE9gUHxvDuL5TM/dmocaqyL+iSCwJVp5ywTvQ5Hps5Q+x6V1/TPe7wyKniYQfP4ooMYu0wov5R0OKjMFXw5jhWZUBUwt6MBIYWiTQQjzE2r73xOdczeNThR7Iohq+qiMNgPHQx9gRUCZLCHgijnUapdBHytZ5OJGhjHdq6zWEHcWaRj7WrYG2R8oW71IN2tTJBbm6sfei8dQIDAQAB");
        String publickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoKJ9a6w9Ib+YsBL1Mi0A/UO9T9ZigVOY"
                + "o4TKBa5eOWeOpH2EDwWNZCnG18q6Oq97jxYrN5iGoAH7rTKR1pI0fIJeid/19wNYgo/h3IdKs1wR"
                + "AvDRhzOjEu7Se9J+73HAKQfF2wpO8X/sCh0tSvNAv7E7FPrSYua0VvkbuYikPYb7dWDBLZ1f+KYs"
                + "1QTdcDiEHKKVjRlrApIi+Z/nlFJl8hknnu7byAu3AvYVp5T3VSodC+vTse9Cj28nxbV2KdOII/8d"
                + "4qogJQ1bci1zzmnpEJbuQy/gfwrenAwNa852z7CXdLwDffTGvQL0dmh5vHV+Ne/vqkjKVDl/U63t" + "FWisJwIDAQAB";
		byte [] encryptedData = encryptor.encrypt(plainText, Base64.decodeBase64(publickey),  false);
		
		byte [] decryptedData = encryptor.decrypt(encryptedData);
		System.out.println(new String(decryptedData));
		
	}


	public static void encrypt() {
		int i = 0;
		Cipher rsa = null;
		String oldValue = null;

		try {
			byte[] rsaData;
			byte[] eid = ("SVGLixMP+kv37CNaFkHDH5BN09Z4MhY34xrUSTahH+E=").getBytes();
			byte[] secretKey = ("CRDg332M3KhBHGqK0g+PeMlRchgCM0A6EbxiUL9BD0k=").getBytes();

			byte[] publicKeyNew = Base64.decodeBase64(
					"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqzrQssGCUuM6p/3FFf2ILXPF0QYrbQACdkGIKFMasAkyMvjZEq2/PqlBW9qsUgstCJvk1dwgC8SjLFkqjy7Yt/7vghZyBJXC4bQ6Oxiz5mPJCjBMRucHfCPbbbnacBfeDfN6tXiBXxqE9gUHxvDuL5TM/dmocaqyL+iSCwJVp5ywTvQ5Hps5Q+x6V1/TPe7wyKniYQfP4ooMYu0wov5R0OKjMFXw5jhWZUBUwt6MBIYWiTQQjzE2r73xOdczeNThR7Iohq+qiMNgPHQx9gRUCZLCHgijnUapdBHytZ5OJGhjHdq6zWEHcWaRj7WrYG2R8oW71IN2tTJBbm6sfei8dQIDAQAB");
			if (rsa == null) {
				KeyFactory kf = KeyFactory.getInstance("RSA");

				X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(publicKeyNew);
				RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(pubSpec);

				String convertedModulus = pubKey.getModulus().toString(16);
				System.out.println("convertedModulus == " + convertedModulus);

				byte[] modulusBytes = hexStringToByteArray(convertedModulus);
				
				System.out.println("ModulusBytes = " + Base64.encodeBase64String(modulusBytes));

				
				byte[] modulusByteArr = modulusBytes;

				byte[] exponentBytes = Base64.decodeBase64("AQAB");
				
				System.out.println("ExponeneBytes = " + Base64.encodeBase64String(exponentBytes));

				BigInteger e = new BigInteger(1, exponentBytes);
				BigInteger m = new BigInteger(1, modulusByteArr);
				System.out.println("m = "+ m);
				System.out.println("e = "+ e);
				RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
				KeyFactory fact = KeyFactory.getInstance("RSA");
				PublicKey pubKeyn = fact.generatePublic(keySpec);
				System.out.println("PUblic Key = "+Base64.encodeBase64String(pubKeyn.getEncoded()));
				rsa = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
				//rsa = Cipher.getInstance("RSA/ECB/NOPADDING");

				PSource pSrc = (new PSource.PSpecified(eid));
				System.out.println("pSrc.getAlgorithm() = " + pSrc.getAlgorithm());

				
				rsa.init(Cipher.ENCRYPT_MODE, pubKeyn);
				
			}

			// Encrypting 16 byte AES secret key using 128 byte RSA public key
			rsaData = rsa.doFinal(secretKey);
			String value = Base64.encodeBase64String(rsaData);
			System.out.println("Value = " + value);			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static byte[] hexStringToByteArray(String data) {
		int k = 0;
		byte[] results = new byte[data.length() / 2];
		for (int i = 0; i < data.length();) {
			results[k] = (byte) (Character.digit(data.charAt(i++), 16) << 4);
			results[k] += (byte) (Character.digit(data.charAt(i++), 16));
			k++;
		}
		return results;
	}
	
	//Used One
    public byte[] encrypt(byte[] plainText, byte[] publicKey,
                          boolean isRegistrar) {
        //String dataToWrite = "";
//        hashGenerator = new HashGeneratorImpl();
//        if (Utility.length(plainText) == 0) {
//            return null;
//        }
        SecureRandom random = generateRandom();

        // Generating a 32 byte random padding
        byte[] eid = generateHash(random.toString().getBytes()); 
        		

        int secretKeySize;
        byte[] headerArray = HEADER1_DATA;
//        if (publicKey.length == PUBLIC_KEY_SIZE) {
//            secretKeySize = SECRET_KEY_SIZE;
//            headerArray = new byte[0];
//        } else {
//            secretKeySize = NEW_SECRET_KEY_SIZE;
//            headerArray = HEADER1_DATA;
//            if (isRegistrar)
//                headerArray = HEADER_DATA;
//        }

        secretKeySize = 256;
        
        // generate the secret key.
        byte[] secretKey = generateSecretKey(secretKeySize);
        // byte[] publicKey = getPublicKey();
        byte[] byteCipherText = encryptAES(plainText, eid, secretKey);

        byte[] rsaData;
        try {
            // rsaData = ecnryptRSA_Android(modulus,"AQAB",eid, secretKey);

            KeyFactory kf = KeyFactory.getInstance("RSA", "BC");

            // decode public key using X509 certificate
            X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(pubSpec);


            //org.spongycastle.asn1.eac.RSAPublicKey rsaPublicKey = (org.spongycastle.asn1.eac.RSAPublicKey) pubKey;

            String convertedModulus = pubKey.getModulus().toString(16);
            //String convertedExponent = pubKey.getPublicExponent().toString(16);

            byte[] modulusBytes = hexStringToByteArray(convertedModulus);

            byte[] exponentBytes = Base64.decodeBase64("AQAB".getBytes("UTF-8"));

            //byte[] exponentBytes = CryptoUtility.hexStringToByteArray(convertedExponent);


            rsaData = ecnryptRSA_Android(Base64.encodeBase64String(modulusBytes), "AQAB", eid, secretKey);
            System.out.println(Base64.encodeBase64String(rsaData));

            //	rsaData = encryptRSA(secretKey, eid, publicKey);
            //dataToWrite += "RSA:" + Utility.byteArrayToHexString(rsaData)	+ "__________";

            // Merging the RSA encrypted secret key with AES encrypted data
            byte[] mergedData = mergeStream(byteCipherText, rsaData);

            // Appending the random EID (padding)
            byte[] mergedDataEid = mergeStream(mergedData, eid);


            BigInteger e = new BigInteger(1, exponentBytes);
            BigInteger m = new BigInteger(1, modulusBytes);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey pubKeyn = fact.generatePublic(keySpec);

            // Appending the RSA public key in the beginning

            byte[] encryptedData = mergeStream(mergedDataEid,
                    pubKeyn.getEncoded());

            // Appending the hash of encrypted content in the beginning
            byte[] mergedHeader;
            if (!isRegistrar && publicKey.length != 162) {
                byte[] hashEncryptedData = generateHash(encryptedData);
                byte[] mergedHEncryptedHash = mergeStream(
                        encryptedData, hashEncryptedData);

                // Appending the header in the beginning
                mergedHeader = mergeStream(mergedHEncryptedHash,
                        headerArray);
            } else {
                // Appending the header in the beginning
                mergedHeader = mergeStream(encryptedData, headerArray);
            }
            return mergedHeader;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
    
    private byte[] generateSecretKey(int keySize) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES", "BC");
            kgen.init(keySize);
            SecretKey aeskey = kgen.generateKey();
            byte[] key = aeskey.getEncoded();
            //setSessionKey((!isAuthRequest) ? null : key);
            return key;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static SecureRandom generateRandom() {
        SecureRandom sr = null;
        try {
            // Create a secure random number generator instance
            sr = SecureRandom.getInstance("SHA1PRNG");
            // Get 1024 random bits
            byte[] bytes = new byte[1024 / 8];
            sr.nextBytes(bytes);
            int seedByteCount = 10;
            byte[] seed = sr.generateSeed(seedByteCount);
            sr = SecureRandom.getInstance("SHA1PRNG");
            // Setting the seed value
            sr.setSeed(seed);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Random Number Generation Error.");
        }
        return sr;
    }
    
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            result.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return result.toString();
    }
    
    /**
     * Returns the 256 bit hash value of the message
     *
     * @param message full plain text
     * @return hash value
     */
    public byte[] generateHash(byte[] message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256", "BC");
            digest.reset();
            //hmacSize = digest.getDigestLength();
            return digest.digest(message);
        } catch (GeneralSecurityException e) {

        }
        return null;
    }
    
    private byte[] encryptAES(byte[] plainText, byte[] eid, byte[] rawSecretKey) {
        try {
            byte[][] eidSplit = split(eid, 16);

            byte[] iv = eidSplit[0];

            BufferedBlockCipher cipher = new BufferedBlockCipher(new CFBBlockCipher(new AESEngine(), 128));

            KeyParameter key = new KeyParameter(rawSecretKey);

            cipher.init(true, new ParametersWithIV(key, iv));

            byte[] mergedData = mergeStream(plainText,
                    generateHash(plainText));
            int outputSize = cipher.getOutputSize(mergedData.length);

            byte[] result = new byte[outputSize];
            int processLen = cipher.processBytes(mergedData, 0,
                    mergedData.length, result, 0);
            cipher.doFinal(result, processLen);
            return result;

        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static byte[] mergeStream(byte[] byte2, byte[] byte1) {
        byte[] message = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, message, 0, byte1.length);
        System.arraycopy(byte2, 0, message, byte1.length, byte2.length);
        return message;
    }
    

    /**
     * split a byte array in two
     *
     * @param src byte array to be split
     * @param n   element at which to split the byte array
     * @return byte[][] two byte arrays that have been split
     */
    public static byte[][] split(byte[] src, int n) {
        byte[] l, r;
        if (src == null || src.length <= n) {
            l = src;
            r = new byte[0];
        } else {
            l = new byte[n];
            r = new byte[src.length - n];
            System.arraycopy(src, 0, l, 0, n);
            System.arraycopy(src, n, r, 0, r.length);
        }
        return new byte[][]{l, r};
    }
    
    
    private byte[] ecnryptRSA_Android(String Modoutput, String Expoutput,
            byte[] eid, byte[] byteData) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException, InvalidAlgorithmParameterException {
			byte[] modulusBytes = Base64.decodeBase64(Modoutput.getBytes("UTF-8"));
			byte[] exponentBytes = Base64.decodeBase64(Expoutput.getBytes("UTF-8"));
			BigInteger e = new BigInteger(1, exponentBytes);
			BigInteger m = new BigInteger(1, modulusBytes);
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
			KeyFactory fact = KeyFactory.getInstance("RSA");
			PublicKey pubKeyn = fact.generatePublic(keySpec);
			
			Cipher rsa = Cipher.getInstance(RSA_NOPADDING_TRANSFORMATION, "BC");
			
			PSource pSrc = (new PSource.PSpecified(eid));
			
			rsa.init(Cipher.ENCRYPT_MODE, pubKeyn, new OAEPParameterSpec(
			"SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
			pSrc));
			
			// Encrypting 16 byte AES secret key using 128 byte RSA public key
			return rsa.doFinal(byteData);
	
    }
    
    /**
	 * Decrypts a given string using AES and RSA keys.AES for text decryption
	 * and RSA for key decryption.
	 * 
	 * @param encrypted
	 *            full encrypted text
	 * @return decrypted text
	 * @throws DecryptionException
	 *             if the AES or RSA algorithm not supported or decrypt
	 *             operation failed
	 */
	public byte[] decrypt(byte[] encryptedData) throws RuntimeException {
		// signal performance metrics capture. actual capture will happen only if it has been enabled via #togglePerformanceMetricsLogging(). Default is off
//		this.performanceMetricsLogger.startPerformanceMetricsCapture();
		
		if(length(encryptedData) == 0)
			throw new RuntimeException("Valid Data for decryption is not available");
		
		ByteArrayParser parser = parseByteArray(encryptedData);
		
		if(   (new String(parser.headerVersion)).equals(new String(HEADER1_DATA)))
		{
			try{
				String generatedHash = byteArrayToHexString(generateHash(parser.getEncryptedPacket()));
				String hashInPacket = byteArrayToHexString(parser.hashOfEncrypted);
//				System.out.println("generatedHash :" + generatedHash);
//				System.out.println("hashInPacket :" + hashInPacket);
				if(!generatedHash.equals(hashInPacket))
				{
					throw new RuntimeException("Integrity Validation Failed : The original exported data at client side and the packet " +
						"at server side is not identical");
					
				}
			}
			catch(Exception e)
			{
				throw new RuntimeException("Integrity Validation Failed : The original exported data at client side and the packet " +
						"at server side is not identical");
			}
			System.out.println("The integirty of the enrollment packet has been preserved");
		}
		
		PrivateKey privateKey = getPrivateKeyObject();
		byte[] decryptedSecretKey = decryptSessionKey(parser.getEncryptedSecretKey(), parser.getIv(), privateKey);
		byte[] decryptedText = decryptPacketData(parser.getEncryptedData(), decryptedSecretKey, parser.getIv());
		
		// Make the object to null so all bytes array in the class will get cleaned. 
		parser = null;
		
		boolean result = validateHash(decryptedText);
		if(!result)
		{
			throw new RuntimeException("Integrity Validation Failed : The original data at client side and the decrypted " +
					"data at server side is not identical");
		}
		System.out.println("The integirty of the enrollment packet has been preserved");

		byte[] data = trimHMAC(decryptedText);
		// log performance metrics captured. actual capture will happen only if it has been enabled via #togglePerformanceMetricsLogging(). Default is off
		return data;
	}
	
	private byte[] trimHMAC(byte[] decryptedText) {
		byte[] actualText;
		if (decryptedText == null || decryptedText.length <= HMAC_SIZE) {
			actualText = new byte[0];
		} else {
			actualText = new byte[decryptedText.length - HMAC_SIZE];
			System.arraycopy(decryptedText, HMAC_SIZE, actualText, 0,
					actualText.length);
		}
		return actualText;
	}
	/**
	 * Decrypts the cipher text using 128 bit AES secret key.
	 * 
	 * @param cipherText
	 *            encrypted text
	 * @param decryptedSecretKey
	 *            decrypted 128 bit AES secret key
	 * @return decrypted text
	 * @throws DecryptionException
	 *             if the AES algorithm not supported or encrypt operation
	 *             failed
	 */
	private byte[] decryptPacketData(byte[] cipherText, byte[] decryptedSecretKey, byte[] eid) throws RuntimeException {
		try {
			byte[][] eidSplit = split(eid, 16);

			byte[] iv = eidSplit[0];
			
			BufferedBlockCipher cipher = new BufferedBlockCipher(new CFBBlockCipher(new AESEngine(), 128));
			KeyParameter key = new KeyParameter(decryptedSecretKey);
			
			cipher.init(false, new ParametersWithIV(key, iv));
			
			int outputSize = cipher.getOutputSize(cipherText.length);
			
			byte[] result = new byte[outputSize];
			int processLen = cipher.processBytes(cipherText, 0, cipherText.length, result, 0);
			cipher.doFinal(result, processLen);
			return result;
			
//			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
//
//			Cipher aesCipher = Cipher.getInstance(DECRYPT_ALGORITHM, SECURITY_PROVIDER);
//
//			SecretKeySpec decryptedKeySpec = new SecretKeySpec(decryptedSecretKey, DECRYPT_ALGORITHM);
//
//			aesCipher.init(Cipher.DECRYPT_MODE, decryptedKeySpec, paramSpec);
//
//			return aesCipher.doFinal(cipherText, 0, cipherText.length);
		} catch (InvalidCipherTextException e) {
			throw new RuntimeException("Decrypting data using AES failed", e);
		}
	}
	
	/**
	 * Decrypts AES secret key using RSA private key.
	 * 
	 * @param encrytedSecretKey
	 *            encrypted AES secret key
	 * @param eid
	 *            Enrollment ID
	 * @return 128 byte decrypted secret key
	 * @throws DecryptionException
	 *             if the AES algorithm not supported or decrypt operation
	 *             failed
	 */
	private byte[] decryptSessionKey(byte[] encryptedSecretKey, byte[] eid, PrivateKey privateKey) throws RuntimeException {
		
		System.out.println(Base64.encodeBase64String(encryptedSecretKey));
		try {
			Cipher rsaCipher = Cipher.getInstance(RSA_NOPADDING_TRANSFORMATION, "BC");
			rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
			// decrypting the session key with rsa no padding.
			// The reason is RSA OAEP SHA256 is not supported in HSM. 
			byte[] decKey = rsaCipher.doFinal(encryptedSecretKey);
			
			// Applying the OAEP padding to get the actual session key. 
//			OAEPEncoding encode = new OAEPEncoding(new RSAEngine(), new SHA256Digest(), eid);
//			
//			RSAKeyParameters keyParams = new RSAKeyParameters(false, ((RSAPrivateKey)privateKey).getModulus(), EXPONENT);
//			encode.init(false, keyParams);
//			
//			return encode.processBlock(decKey, 0, decKey.length);
			return decKey;
		} 
//			catch(InvalidCipherTextException txtExp) {
//			txtExp.printStackTrace();
//			throw new RuntimeException(txtExp.getMessage());
//		} 
		catch (GeneralSecurityException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to decrypt AES secret key using RSA.");
		}
	}
	
	/**
	 * Generates a private key object from string privateKeydata.
	 * 
	 * @param String
	 *            private key data
	 * @return private key Object
	 * @throws DecryptionException
	 *             Key Generation problems.
	 */
	
	private PrivateKey getPrivateKeyObject() throws RuntimeException {

		PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(hexStringToByteArray("308204bd020100300d06092a864886f70d0101010500048204a7308204a30201000282010100a0a27d6bac3d21bf98b012f5322d00fd43bd4fd662815398a384ca05ae5e39678ea47d840f058d6429c6d7caba3aaf7b8f162b379886a001fbad3291d692347c825e89dff5f70358828fe1dc874ab35c1102f0d18733a312eed27bd27eef71c02907c5db0a4ef17fec0a1d2d4af340bfb13b14fad262e6b456f91bb988a43d86fb7560c12d9d5ff8a62cd504dd7038841ca2958d196b029222f99fe7945265f219279eeedbc80bb702f615a794f7552a1d0bebd3b1ef428f6f27c5b57629d38823ff1de2aa20250d5b722d73ce69e91096ee432fe07f0ade9c0c0d6bce76cfb09774bc037df4c6bd02f4766879bc757e35efefaa48ca54397f53aded1568ac270203010001028201004869f7f05a2d14d1b29b6d706bb62e03fe4d91782bdff137963f7e73213008e0ce4508f0477551501e252d928e71763a553ec12cc6eda9a43b38d0db3dc79b31a5f3c863d6160cc411d79b12374b4d9feb6b4ff4a4e67a5546a44c7d900b9153f72b31de94bf11cf8130ee06450b73dcf6a093cc9e9b4735751fc787d439762274c3892b63f41e3e9d1ce0828ccba48f011489012f5b8b53c56ecaa36832f4d661e4e2b4229e720845bf682fd37212e04a901181c34d3b35e6f7dbb2fee13f757072d14ba5770d9f6934f3e4e17eed7c40cbac5016b47d03afc2ee65b870055f1e531d848b0ef192c071795314cb75982d13284622927c9c805d50d6cd320f0102818100d02fac7aa9def96f52e472a717b83c8b24899b59c1390df26237f122513f977fc706df988b8a021fc6c3b2721d097d2f383aed488c986c010812b5e63f11881d2085ec769427c132f638ae4d5fa775dab77ffb93667cfb778e089be091cfac493e0b5f6a8e1d6c8001bbd6887934ef89619287b58783854a07c901a96b7ddbb702818100c5870598f620668343eec60dccce15f1d97f23304a913f6d363b7a58678cd5fdaa69e8eba7140ef0858a5f78e2bc39900628067fdfd1d34563b479b4c825459b42e638055d238cc3f21d7272d8a5e61db1e3514892a57c3be567599f6b5335d9b21f4b56f05d95fa4d1720a1fa06a2d4d988f6542f36982e5de09ea45035931102818000c3fc1d635c878c4a40becf307e23f2db0a8a80b4fe31b7ef7687337a1b02c29c87c0f0bdaa9f4160a8bd923fdb1613c3602c193533264a86ae12ef2ad234aeeebcaf56ec4a8d2253d777849f526d53859339e89bdadfcddbbeda5be40d6073d07f98353b1a8e9895e90bebea8e3047500d8ae437005bd01ee135595b24d657028180540d0b5db645bd3957fbcf7ce9cc3bdc48af67dad1b012ac372a81fa023e1484b7320ff1ce8ba37bc767450edc74398e46805b9ba832d1d2b20586d05d6636c345694f274125b26d62a61320397e0678564ca336dd67db80653260198d02c0645cfe2201e19001149c1f1e3cae768a6d460f98b9e3c938626a81fd1f701e0bf102818100ca2b6c33af3123c8940fe8961c8a58ecf377fdf73b78f21bb2d4aab4639d2cd8a82886f135ccaa1e2f92ca4ba5d5963ab228325b2ef6fff67317265271618bcc8ef3a4d5a93605634b4c48545e05b3d49a4c250dfa130ca03f16a76f481b51561578584bf8e5738b5520f48beb89bc6d1e0520a9c170974e35e21040758cf3ba"));
		try {

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			return (RSAPrivateKey) keyFactory
					.generatePrivate(privateSpec);

		} catch (Exception e) {
			throw new RuntimeException(
					"RSA Private key cannot be constructed.");
		}
	}
	
	
	
	public static int length(byte[] byteArr) {
		return byteArr == null ? 0 : byteArr.length;
	}
	
	private boolean validateHash(byte[] decryptedText)  {
		byte[][] hs = split(decryptedText, 32);
			byte[] actualHash = generateHash(hs[1]);
			try {
				if (new String(hs[0], "UTF-8").equals(new String(actualHash, "UTF-8"))) {
					return true;
				} else {
					return false;
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}
	public static int length(String anyStr) {
		return anyStr == null ? 0 : anyStr.trim().length();
	}
	
private class ByteArrayParser {
		
		private final byte[] headerVersion;
		private final byte[] iv; 
		private final byte[] encryptedSecretKey;
		private final byte[] encryptedData;
		private final byte[] publicKeyData;
		private final byte[] hashOfEncrypted;
		private final byte[] encryptedPacket;

		public ByteArrayParser(byte[] data, int versionLen, int publicKeyLen, int ivLen, int secretKeyLen) throws RuntimeException {
			int offset = 0;
			headerVersion = new byte[versionLen];
			copyByteArray(data, 0, headerVersion.length, headerVersion);
			offset = offset + versionLen;
			
			hashOfEncrypted = new byte[32];
			
			
			if((new String(headerVersion)).equals(new String(HEADER1_DATA)))
			{
				copyByteArray(data, offset, 32, hashOfEncrypted);
				offset = offset + 32;
			}
			//After removing the header and hash of encrypted.
			encryptedPacket = new byte[data.length - offset];
			copyByteArray(data,offset,encryptedPacket.length,encryptedPacket);
			
			publicKeyData = new byte[publicKeyLen];
			copyByteArray(data, offset, publicKeyData.length, publicKeyData);
			offset = offset + publicKeyLen;
			iv = new byte[ivLen];
			copyByteArray(data, offset, iv.length, iv);
			offset = offset + ivLen;
			encryptedSecretKey = new byte[secretKeyLen];
			copyByteArray(data, offset, encryptedSecretKey.length, encryptedSecretKey);
			offset = offset + secretKeyLen;
			encryptedData = new byte[data.length - offset];
			copyByteArray(data, offset, encryptedData.length, encryptedData);
		}
		
//		public byte[] getHeaderVersion() {
//			return headerVersion;
//		}
		
		public byte[] getPublicKeyData() {
			return publicKeyData;
		}

		public byte[] getIv() {
			return iv;
		}

		public byte[] getEncryptedSecretKey() {
			return encryptedSecretKey;
		}

		public byte[] getEncryptedData() {
			return encryptedData;
		}
		
		public byte[] getEncryptedPacket() {
			return encryptedPacket;
		}
		public byte[] getHashOfEncrypted(){
			return hashOfEncrypted;
		}

		private void copyByteArray(byte[] src, int offset, int length, byte[] dest) throws RuntimeException {
			try {
				System.arraycopy(src, offset, dest, 0,length);
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Decryption failed, Corrupted packet ", e);
			}
		}
	}


private ByteArrayParser parseByteArray(byte[] encryptedData) throws RuntimeException {
	byte[] versionBytes = new byte[HEADER_DATA.length];
	System.arraycopy(encryptedData, 0, versionBytes, 0, versionBytes.length);
	if(new String(versionBytes).equals(new String(HEADER_DATA))) 
		return new ByteArrayParser(encryptedData, HEADER_DATA.length, PUBLIC_KEY_SIZE, EID_SIZE, SECRET_KEY_SIZE);
	if(new String(versionBytes).equals(new String(HEADER1_DATA))) 
		return new ByteArrayParser(encryptedData, HEADER1_DATA.length, PUBLIC_KEY_SIZE, EID_SIZE, SECRET_KEY_SIZE);
	return new ByteArrayParser(encryptedData,0, LEGACY_PUBLIC_KEY_SIZE, EID_SIZE, LEGACY_SECRET_KEY_SIZE);
}
}
