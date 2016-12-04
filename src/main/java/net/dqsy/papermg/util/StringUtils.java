package net.dqsy.papermg.util;

import java.util.Random;

public class StringUtils {
    private static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9'};

    public static String generatePassword(int minLength, int maxLength) {
        StringBuilder sb = new StringBuilder("");

        Random random = new Random();

        int count = random.nextInt(maxLength - minLength + 1) + minLength;

        for (int i = 0; i < count; i++) {
            char c = (char) (random.nextInt(94) + 33);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String generatePwd(int minLength, int maxLength) {
        StringBuilder sb = new StringBuilder("");

        Random random = new Random();

        int count = random.nextInt(maxLength - minLength + 1) + minLength;

        for (int i = 0; i < count; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(generatePwd(6, 6));
    }
}