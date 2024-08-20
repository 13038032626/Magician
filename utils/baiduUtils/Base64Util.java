package com.example.meitu2.utils.baiduUtils;

/**
 * Base64 工具类
 */
public class Base64Util {
    private static final char last2byte = (char) Integer.parseInt("00000011", 2);
    private static final char last4byte = (char) Integer.parseInt("00001111", 2);
    private static final char last6byte = (char) Integer.parseInt("00111111", 2);
    private static final char lead6byte = (char) Integer.parseInt("11111100", 2);
    private static final char lead4byte = (char) Integer.parseInt("11110000", 2);
    private static final char lead2byte = (char) Integer.parseInt("11000000", 2);
    private static final char[] encodeTable = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    public Base64Util() {
    }

    public static String encode(byte[] from) {
        StringBuilder to = new StringBuilder((int) ((double) from.length * 1.34D) + 3);
        int num = 0;
        char currentByte = 0;

        int i;
        for (i = 0; i < from.length; ++i) {
            for (num %= 8; num < 8; num += 6) {
                switch (num) {
                    case 0:
                        currentByte = (char) (from[i] & lead6byte);
                        currentByte = (char) (currentByte >>> 2);
                    case 1:
                    case 3:
                    case 5:
                    default:
                        break;
                    case 2:
                        currentByte = (char) (from[i] & last6byte);
                        break;
                    case 4:
                        currentByte = (char) (from[i] & last4byte);
                        currentByte = (char) (currentByte << 2);
                        if (i + 1 < from.length) {
                            currentByte = (char) (currentByte | (from[i + 1] & lead2byte) >>> 6);
                        }
                        break;
                    case 6:
                        currentByte = (char) (from[i] & last2byte);
                        currentByte = (char) (currentByte << 4);
                        if (i + 1 < from.length) {
                            currentByte = (char) (currentByte | (from[i + 1] & lead4byte) >>> 4);
                        }
                }

                to.append(encodeTable[currentByte]);
            }
        }

        if (to.length() % 4 != 0) {
            for (i = 4 - to.length() % 4; i > 0; --i) {
                to.append("=");
            }
        }

        return to.toString();
    }
    public static byte[] decode(String encoded) {
        // 删除任何等号填充
        int paddingCount = 0;
        for (int i = encoded.length() - 1; i >= 0 && encoded.charAt(i) == '='; i--) {
            paddingCount++;
        }

        // 计算解码后的字节数
        int byteLength = encoded.length() * 6 / 8 - paddingCount;
        byte[] decodedBytes = new byte[byteLength];

        int byteIndex = 0;
        int bitIndex = 0;
        int currentByte = 0;

        for (int i = 0; i < encoded.length(); i++) {
            char currentChar = encoded.charAt(i);

            if (currentChar == '=') {
                break; // 忽略填充
            }

            int currentCharValue = findCharIndex(currentChar);

            if (currentCharValue == -1) {
                throw new IllegalArgumentException("Invalid Base64 character: " + currentChar);
            }

            currentByte = (currentByte << 6) | currentCharValue;
            bitIndex += 6;

            if (bitIndex >= 8) {
                bitIndex -= 8;
                decodedBytes[byteIndex++] = (byte) ((currentByte >>> bitIndex) & 0xFF);
            }
        }

        return decodedBytes;
    }

    private static int findCharIndex(char c) {
        for (int i = 0; i < encodeTable.length; i++) {
            if (encodeTable[i] == c) {
                return i;
            }
        }
        return -1; // 如果找不到字符返回-1
    }
}

