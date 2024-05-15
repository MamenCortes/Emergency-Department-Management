package sample.db.jpa;


import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class MD5Cypher {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String password = "123456789";
		String passwordCodificada = MD5Cypher.encrypt(password);
		System.out.println(passwordCodificada);
	}
	
	public static String encrypt(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
	    md.update("texto a cifrar".getBytes());
	    byte[] digest = md.digest();
	    byte[] encoded = Base64.encodeBase64(digest);
	    
		return new String(encoded);
		
	}

}
