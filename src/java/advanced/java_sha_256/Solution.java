package java.advanced.java_sha_256;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String value = scanner.nextLine();
		System.out.println(sha256Encrypt(value));

		scanner.close();
	}

	public static String sha256Encrypt(String value) {
		try {
			java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");
			byte[] array = messageDigest.digest(value.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				// System.out.println("Integer.toHexString(array[i]) = " +
				// Integer.toHexString(array[i]));
				// System.out.println("Integer.toHexString(0xFF) = " +
				// Integer.toHexString(0xFF));
				// System.out.println("Integer.toHexString(0x100) = " +
				// Integer.toHexString(0x100));
				// System.out.println("Integer.toHexString((array[i] & 0xFF)) =
				// " + Integer.toHexString((array[i] & 0xFF)));
				// System.out.println("Integer.toHexString((array[i] & 0xFF) |
				// 0x100) = " + Integer.toHexString((array[i] & 0xFF) | 0x100));

				// Result is a serialized value of ((array[9] AND FF) OR 100)
				// making hex logical operations.
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
				// sb.append(Integer.toHexString(array[i]));
				// System.out.println("================================================");
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			return null;
		}
	}
}
