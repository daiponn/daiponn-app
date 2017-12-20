package com.kasahara.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 文字列操作を行うためのクラス
 * Created by H.Yasunaga on 2017/08/07.
 */
public class StringUtils {

    /**
     * 16進数 02桁
     */
    public static final String H02 = "%02x";

    /**
     * 16進数 04桁
     */
    public static final String H04 = "%04x";

    /**
     * 16進数 06桁
     */
    public static final String H06 = "%06x";

    /**
     * 16進数 08桁
     */
    public static final String H08 = "%08x";

    /**
     * 指定したフォーマットで文字列を返却する
     *
     * @param format 指定フォーマット
     * @param src    入力文字列(int)
     * @return フォーマット後文字列
     */
    public static String getSpecifiedString(String format, int src) {
        return String.format(format, src);
    }

    /**
     * 16進数 02桁を返却する
     *
     * @param src 入力文字列(int)
     * @return フォーマット後文字列
     */
    public static String getH02xString(int src) {
        return String.format(H02, src);
    }

    /**
     * 16進数 02桁を返却する
     *
     * @param src 入力文字列(byte)
     * @return フォーマット後文字列
     */
    public static String getH02xString(byte src) {
        return String.format(H02, src);
    }

    /**
     * 16進数 02桁を返却する
     *
     * @param src 入力文字列(long)
     * @return フォーマット後文字列
     */
    public static String getH02xString(long src) {
        return String.format(H02, src);
    }

    /**
     * 16進数 04桁を返却する
     *
     * @param src 入力文字列(int)
     * @return フォーマット後文字列
     */
    public static String getH04xString(int src) {
        return String.format(H04, src);
    }

    /**
     * 16進数 06桁を返却する
     *
     * @param src 入力文字列(int)
     * @return フォーマット後文字列
     */
    public static String getH06xString(int src) {
        return String.format(H06, src);
    }

    /**
     * 16進数 06桁を返却する
     *
     * @param src 入力文字列(long)
     * @return フォーマット後文字列
     */
    public static String getH06xString(long src) {
        return String.format(H06, src);
    }

    /**
     * 16進数 08桁を返却する
     *
     * @param src 入力文字列(int)
     * @return フォーマット後文字列
     */
    public static String getH08xString(int src) {
        return String.format(H08, src);
    }

    /**
     * 16進数 08桁を返却する
     *
     * @param src 入力文字列(long)
     * @return フォーマット後文字列
     */
    public static String getH08xString(long src) {
        return String.format(H08, src);
    }

    /**
     * 入力文字列を16進ascii文字列に変換して返却する
     *
     * @param src 入力文字列
     * @return 16進ascii文字列
     */
    public static String getAsciiFromString(String src) {
        byte[] ascii;
        try {
            ascii = src.getBytes("US-ASCII");
        } catch (Exception e) {
            ascii = null;
        }
        return ByteUtils.convertToString(ascii);
    }

    /**
     * Listの中身をデリミターで繋げて返却する
     *
     * @param src       リスト
     * @param delimiter デリミター
     * @return デリミター付き文字列
     */
    public static String toCsv(List<String> src, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String data : src) {
            sb.append(data).append(delimiter);
        }
        return sb.toString();
    }

    /**
     * 文字列をデリミターで分割した結果をListに詰めて返却する
     *
     * @param src       デリミター付き文字列
     * @param delimiter デリミター
     * @return リスト
     */
    public static List<String> toList(String src, String delimiter) {
        return Arrays.asList(src.split(delimiter));
    }

    /**
     * 文字列がNUllもしくは空文字かを確認し結果を返却する
     *
     * @param value 確認する文字列
     * @return boolean
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

}
