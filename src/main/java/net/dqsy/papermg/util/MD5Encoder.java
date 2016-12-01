package net.dqsy.papermg.util;

import java.io.PrintStream;
import java.security.MessageDigest;

public class MD5Encoder
{
  public static String encode(String sourceString)
  {
    String resultString = null;
    try {
      resultString = new String(sourceString);
      MessageDigest md = MessageDigest.getInstance("MD5");

      byte[] b = md.digest(resultString.getBytes());
      resultString = byte2hexString(b);
    } catch (Exception localException) {
    }
    return resultString;
  }

  private static final String byte2hexString(byte[] bytes)
  {
    StringBuilder buf = new StringBuilder(bytes.length * 2);
    for (int i = 0; i < bytes.length; i++) {
      if ((bytes[i] & 0xFF) < 16) {
        buf.append("0");
      }
      buf.append(Long.toString(bytes[i] & 0xFF, 16));
    }
    return buf.toString();
  }

  public static void main(String[] args) {
    System.out
      .print("123456字符串经过加密后成了:" + encode("123456"));
  }
}