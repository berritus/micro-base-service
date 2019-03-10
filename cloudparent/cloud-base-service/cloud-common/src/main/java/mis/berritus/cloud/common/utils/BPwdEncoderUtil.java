package mis.berritus.cloud.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BPwdEncoderUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String BCryptPassword(String password){
        return encoder.encode(password);
    }

    public static boolean matches(CharSequence password, String encodedPassword){
        return encoder.matches(password, encodedPassword);
    }
}
