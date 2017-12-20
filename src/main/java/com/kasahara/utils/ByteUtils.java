package com.kasahara.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * byte操作を行うためのクラス
 * <p>
 *
 */
public class ByteUtils {

    /**
     * bit:1
     */
    public static final int BIT_1 = 0x01;

    /**
     * bit:2
     */
    public static final int BIT_2 = 0x02;

    /**
     * bit:3
     */
    public static final int BIT_3 = 0x04;

    /**
     * bit:4
     */
    public static final int BIT_4 = 0x08;

    /**
     * bit:5
     */
    public static final int BIT_5 = 0x10;

    /**
     * bit:6
     */
    public static final int BIT_6 = 0x20;

    /**
     * bit:7
     */
    public static final int BIT_7 = 0x40;

    /**
     * bit:8
     */
    public static final int BIT_8 = 0x80;

    /**
     * 1byteの情報から指定の部分のみbitを取り出す
     * <p>
     * src:01101001
     * start:3
     * length:4
     * return:00001010
     *
     * @param src    もとの1byte情報
     * @param start  取り出すbitの開始位置
     * @param length 取り出す長さ
     * @return 取り出したbit情報
     */
    public static byte extractBitRange(byte src, int start, int length) {
        // (src >> (start - 1) : 不要の下位ビットを削除（右シフト）
        // ~(-1 << length) : 取り出したい長さ分bitをたてる length:3なら 00000111
        // 上記２つの アンド演算 で必要な部分のみのbitになる
        // src
        // ---------------------------------
        // | 0 | 1 | 1 | 0 | 1 | 0 | 0 | 1 |
        // ---------------------------------
        // (src >> (start - 1))                    ~(-1 << length)
        // ---------------------------------       ---------------------------------
        // | 0 | 0 | 0 | 1 | 1 | 0 | 1 | 0 |       | 0 | 0 | 0 | 0 | 1 | 1 | 1 | 1 |
        // ---------------------------------       ---------------------------------
        return (byte) ((src >> (start - 1)) & ~(-1 << length));
    }

    /**
     * 　入力値をlong値に符号なし変換して返却する
     *
     * @param src バイト配列
     * @return long値
     */
    public static long convertToLongValue(byte[] src) {
        return Long.parseLong(convertToString(src), 16); // 基数：16進数
    }

    /**
     * 　入力値を16進文字列に変換して返却する
     *
     * @param src バイト配列
     * @return 16進文字列
     */
    public static String convertToString(byte[] src) {
        return DatatypeConverter.printHexBinary(src).toLowerCase();
    }

    /**
     * byte[]をascii文字列に変換して返却する
     *
     * @param src 入力byte[]
     * @return ascii文字列
     */
    public static String getAsciiFromByteArray(byte[] src) {
        String ascii;
        try {
            ascii = new String(src, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            ascii = "";
        }
        return ascii;
    }

    /**
     * 1byte中の上位4bitと下位4bitを入れ替えて返却する
     * byteはmax：0x80なので、0xffまで対応するためにintで返却する
     *
     * @param src byte文字
     * @return 入れ替え後の値(int)
     */
    public static int swapHighLow(byte src) {
        int val = Byte.toUnsignedInt(src);
        return ((val & 0x0f) << 4) | ((val & 0xf0) >> 4);
    }

    /**
     * 1byte中の上位4bitと下位4bitを入れ替えて返却する
     *
     * @param src byte配列
     * @return 入れ替え後の文字列
     */
    public static String swapHighLow(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (byte b : src) {
            sb.append(StringUtils.getH02xString(swapHighLow(b)));
        }
        return sb.toString();
    }

    /**
     * 指定したbitが立っているかチェックする
     *
     * @param src byte文字
     * @param bit 指定bit
     * @return 判定結果
     */
    public static boolean isBitOn(byte src, int bit) {
        int val = Byte.toUnsignedInt(src);
        return (val & bit) != 0x00;
    }

    /**
     * 指定したbitを立てる
     *
     * @param src byte文字
     * @param bit 指定bit
     * @return bitを立てた値
     */
    public static int setBitOn(byte src, int... bit) {
        int ans = 0x00;
        for (int oneBit : bit) {
            ans |= oneBit;
        }
        int val = Byte.toUnsignedInt(src);
        return val | ans;
    }

    /**
     * byte配列結合
     *
     * @param src 結合データ
     * @return 結合のbyte配列
     */
    public static byte[] appendByteArray(byte[]... src) {
        int totalLength = 0;
        for (byte[] ary : src) {
            totalLength += ary.length;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(totalLength);

        for (byte[] ary : src) {
            byteBuffer.put(ary);
        }
        return byteBuffer.array();
    }

    /**
     * byte配列の任意の位置を置き換える
     *
     * @param dst    置き換え先
     * @param src    置き換えデータ
     * @param offset 置き換え位置
     */
    public static void replaceByteArray(byte[] dst, byte[] src, int offset) {
        int j = 0;
        for (int i = offset; j < src.length; i++, j++) {
            dst[i] = src[j];
        }
    }

    /**
     * longの値を指定された長さのbyte配列に変換する
     * <p>
     * src:22032, size:3
     * return: [0x00, 0x56, 0x10]
     *
     * @param src  元データ
     * @param size byte長さ
     * @return 変換したbyte配列
     */
    public static byte[] convertToByteArray(long src, int size) {
        byte[] ret = new byte[size];
        for (int i = 1; i <= size; i++) {
            ret[i - 1] = (byte) (src >> (8 * (size - i)));
        }
        return ret;
    }
}
