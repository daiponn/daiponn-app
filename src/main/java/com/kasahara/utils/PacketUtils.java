package com.kasahara.utils;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

/**
 * パケット操作を行うためのクラス
 *
 * Created by H.Yasunaga on 2017/08/24.
 */
public class PacketUtils {

    private byte[] targetArray;
    private int offset;

    /**
     * 抽出対象セット(抽出開始位置指定版)
     *
     * @param src 抽出対象
     * @param idx 抽出開始位置(0〜)
     */
    public void setTargatArrayWithOffset(byte[] src, int idx) {
        targetArray = src;
        offset = idx;
    }

    /**
     * 抽出対象セット
     *
     * @param src 抽出対象
     */
    public void setTargatArray(byte[] src) {
        targetArray = src;
    }

    /**
     * 抽出開始位置指定
     *
     * @param idx 抽出開始位置(0〜)
     */
    public void setOffset(int idx) {
        offset = idx;
    }

    /**
     * byte(オクテット)での抽出
     *
     * @param info パケット構造情報
     * @return 抽出結果(byte)
     */
    public byte extractByte(PacketStructure info) {
        int startBit;
        int length;
        if (info.getStartBit() == 0 && info.getLength() == 0) { // 1オクテット抽出用
            startBit = 1;
            length = 8;
        } else {
            startBit = info.getStartBit();
            length = info.getLength();
        }
        return ByteUtils.extractBitRange(targetArray[getStartOffset(info)], startBit, length);
    }


    /**
     * longでの抽出
     *
     * @param info パケット構造情報
     * @return 抽出結果(long)
     */
    public long extractLong(PacketStructure info) {
        if (info.getStartBit() == 0 && info.getLength() == 0) {
            // オクテット単位での処理
            return ByteUtils.convertToLongValue(Arrays.copyOfRange(targetArray, getStartOffset(info),
                    offset + info.getEndOctet()));
        } else {
            // オクテット内のビット単位での処理(この指定は無い想定)
            return 0L;
        }
    }

    /**
     * 始点と長さを指定してlongでの抽出
     * @param start 始点
     * @param length 長さ
     * @return 抽出結果(Byte[])
     */
    public long extractLong(int start, int length) {
        return ByteUtils.convertToLongValue(extractByteArray(start, length));
    }

    /**
     * Stringでの抽出
     *
     * @param info パケット構造情報
     * @return 抽出結果(String)
     */
    public String extractString(PacketStructure info) {
        if (info.getStartOctet() != info.getEndOctet()) {
            // オクテット単位での処理
            return ByteUtils.convertToString(Arrays.copyOfRange(targetArray, getStartOffset(info),
                    offset + info.getEndOctet()));
        } else {
            // オクテット内のビット単位での処理
            byte[] str = new byte[1];
            str[0] = extractByte(info);
            return ByteUtils.convertToString(str);
        }
    }

    /**
     * AsciiStringでの抽出
     *
     * @param info パケット構造情報
     * @return 抽出結果(AsciiString)
     */
    public String extractAsciiString(PacketStructure info) {
        byte[] str;
        if (info.getStartOctet() != info.getEndOctet()) {
            // オクテット単位での処理
            str = Arrays.copyOfRange(targetArray, getStartOffset(info),
                    offset + info.getEndOctet());
        } else {
            // オクテット内のビット単位での処理
            str = new byte[1];
            str[0] = extractByte(info);
        }
        return ByteUtils.getAsciiFromByteArray(str);
    }

    /**
     * 始点と長さを指定してAsciiStringでの抽出
     * @param start 始点
     * @param length 長さ
     * @return 抽出結果(AsciiString)
     */
    public String extractAsciiString(int start, int length) {
        return ByteUtils.getAsciiFromByteArray(extractByteArray(start, length));
    }

    /**
     * 始点と長さを指定してByte配列で抽出
     * @param info パケット構造情報
     * @return 抽出結果(Byte[])
     */
    public byte[] extractByteArray(PacketStructure info) {
        return Arrays.copyOfRange(targetArray, getStartOffset(info), offset + info.getEndOctet());
    }

    /**
     * 始点と長さを指定してByte配列で抽出
     * @param start 始点
     * @param length 長さ
     * @return 抽出結果(Byte[])
     */
    public byte[] extractByteArray(int start, int length) {
        return Arrays.copyOfRange(targetArray, offset + start,
                offset + start + length);
    }

//    /**
//     * AVPFlagを考慮して抽出開始位置を返す(VendorIdがあれば読み飛ばす)
//     * @param avpFlags AVP Flags
//     * @return 抽出開始位置
//     */
//    public int getValueStartOffset(byte avpFlags) {
//        if (isVendorFlagOn(avpFlags)) {
//            return AVPFormat.AVP_VENDOR_ID.getEndOctet();
//        } else {
//            return AVPFormat.AVP_LENGTH.getEndOctet();
//        }
//    }

    /**
     * 入力値のバイナリーでの文字数を返却する
     *
     * @param src 16進文字列
     * @return 入力値のバイナリーでの文字数
     */
    public static String getContentLength(String src){
        return StringUtils.getH04xString(DatatypeConverter.parseHexBinary(src).length);
    }

    /**
     * 与えられたbyte配列を1byteずつ解析して、上位4bitと下位4bitを入れ替えた文字列を生成する
     * fは無視する
     * create session requestなどで受け取る IMSIの変換などに使う
     * src = [21, 34, 85, 10, f0]
     * resultStr = "124358010"
     *
     * @param src 入力byte[]
     * @return 文字列
     */
    public static String swapByteAndConvertToString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (byte b : src) {
            sb.append(b & 0x0f);
            int lowerBit = (b & 0xf0) >> 4;
            if (lowerBit != 15) {
                sb.append(lowerBit);
            }
        }
        return sb.toString();
    }


    private boolean isVendorFlagOn(byte avpFlags) {
        return ByteUtils.isBitOn(avpFlags, ByteUtils.BIT_8);
    }

    private int getStartOffset(PacketStructure info) {
        return offset + info.getStartOctet() - 1;
    }

    /**
     * パケット構造interface
     */
    public interface PacketStructure {

        /**
         * 開始オクテット
         */
        int getStartOctet();

        /**
         * 終了オクテット
         */
        int getEndOctet();

        /**
         * 開始ビット位置(最下位ビットを1とする)
         * １オクテット中にビット指定がない時は0
         */
        int getStartBit();

        /**
         * ビット長
         * １オクテット中にビット指定がない時は0
         */
        int getLength();

    }
}
