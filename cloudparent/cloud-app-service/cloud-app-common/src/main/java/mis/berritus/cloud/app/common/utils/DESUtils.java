package mis.berritus.cloud.app.common.utils;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtils {
	private static Key key;
	private static String KEY_STR = "mis";
	
	static{
		try{
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("restriction")
	public static String getEncryptString(String str) throws Exception{
		@SuppressWarnings("restriction")
		BASE64Encoder base64en = new BASE64Encoder();
		byte[] strBytes = str.getBytes("UTF-8");
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryBytes = cipher.doFinal(strBytes);
		return base64en.encode(encryBytes);
	}

	public static String tranToBase64(String data){
		BASE64Encoder base64en = new BASE64Encoder();
		String base64Str = base64en.encode(data.getBytes());
		return base64Str;
	}

	public static String getDecryptString(String str) throws Exception{
		@SuppressWarnings("restriction")
		BASE64Decoder base64de = new BASE64Decoder();
		byte[] strBytes = base64de.decodeBuffer(str);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryBytes = cipher.doFinal(strBytes);
		return new String(decryBytes, "UTF-8");
	}
	
	
	public static String getRundom(int num){
		StringBuffer id=new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			char s = 0;
			int j=random.nextInt(3) % 4;
			switch (j) {
			case 0:
				//随机生成数字
				s = (char) (random.nextInt(57) % (57 - 48 + 1) + 48);
				break;
			case 1:
				//随机生成大写字母
				s = (char) (random.nextInt(90) % (90 - 65 + 1) + 65);            
				break;
			case 2:
				//随机生成小写字母
				s = (char) (random.nextInt(122) % (122 - 97 + 1) + 97);
				break;
			}
			id.append(s);
		}
		return System.currentTimeMillis() + "" + id.toString() ;
	}
	
//	public static byte[] decode(byte[] bytes){
//		return Base64.decodeBase64(bytes);
//	}
//	
//	public static String encode(byte[] bytes){
//		return new String(Base64.encodeBase64(bytes));
//	}
}
