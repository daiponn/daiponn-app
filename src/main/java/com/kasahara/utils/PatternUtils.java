package com.kasahara.utils;

import java.util.regex.Pattern;

/**
 * Created by yasunaga on 2017/10/27.
 */
public class PatternUtils {

    /**
     * v4 IPアドレス形式かどうかをチェックするための正規表現
     */
    public static final String IP_V4_ADDRESS_FORMULA = "^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";

    /**
     * v4 IPアドレス形式かどうかをチェックするための正規表現(レンジを含む)
     */
    public static final String IP_V4_CIDR_FORMULA = "^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\/([1-9]|[1-2][0-9]|3[0-2])$";

    /**
     * IMSI形式かどうかをチェックするための正規表現
     */
    public static final String IMSI_FORMURA = "^[0-9]{15,16}$";

    /**
     * 法人ID形式かどうかをチェックするための正規表現
     */
    public static final String CORPORATION_ID_FORMURA = "^[0-9]{12}$";

    private Pattern pattern;

    /**
     * コンストラクター
     *
     * @param pattern チェック用正規表現
     */
    public PatternUtils(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * コンストラクター
     */
    public PatternUtils() {
    }

    /**
     * パターンセット
     *
     * @param pattern チェック用正規表現
     */
    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * チェック用正規表現を満たしているかテストし結果を返却する
     *
     * @param testData テストデータ
     * @return boolean
     */
    public boolean isMatch(String testData) {
        try {
            return pattern.matcher(testData).matches();
        } catch(NullPointerException e) {
            return false;
        }
    }
}
