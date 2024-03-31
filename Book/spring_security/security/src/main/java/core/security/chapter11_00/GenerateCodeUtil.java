package core.security.chapter11_00;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {

  private GenerateCodeUtil() {}

  public static String generateCode() {
    String code;

    try {
      SecureRandom random = SecureRandom.getInstanceStrong();
      int c = random.nextInt(9000) + 1000;
      code = String.valueOf(c);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    return code;
  }
}
