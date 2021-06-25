package com.koreait.member.command;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class Encript {

	public static String sha256(String str) {
		// java : java.security
		// MessageDigest;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// md.digest() : 암호화 된 바이트 배열
		StringBuilder sb = new StringBuilder();
		for (byte b : md.digest()) {
			sb.append(String.format("%02x", b));  // 16진수 표기법 변환
		}
		return sb.toString();
	}
	
	public static String base64(String str) {
		// commons-codec
		// Base64
		// Base64.encodeBase64(pw.getBytes()) : 암호화 된 바이트 배열
		StringBuilder sb = new StringBuilder();
		for (byte b : Base64.encodeBase64(str.getBytes())) {
			sb.append(String.format("%02x", b));  // 16진수 표기법 변환
		}
		return sb.toString();
	}
	
}