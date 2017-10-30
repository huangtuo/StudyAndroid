package com.study.ht.studyandroid.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author ttarfall
 * @date 2016-09-19 18:28
 */
public class NumberUtil {

    /**
     * 将金额格式的字符串转为普通格式的字符串
     *
     * @param s
     * @return
     */
    public static String getMoneyString(String s) {
        try {
            if (!TextUtils.isEmpty(s)) {
                if (s.contains(",")) {
                    s = s.replaceAll(",", "");
                }
                return s.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    /**
     * 格式化字符串为 两位有效数字截取的字符串
     *
     * @param s
     * @return
     */
    public static String getDF2MoneyNumber(String s) {
        try {
            return getB2MoneyNumber(getBigDecimal(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    /**
     * BigDecimal 类型的数据转化为 2位有效数字的截取字符串
     *
     * @param bigDecimal
     * @return
     */
    public static String getB2MoneyNumber(BigDecimal bigDecimal) {
        try {
            if (bigDecimal != null) {
                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
                DecimalFormat df = new DecimalFormat(",###,##0.00");
                df.setRoundingMode(RoundingMode.DOWN);
                return df.format(bigDecimal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    /**
     * 格式化字符串为 整数有效数字截取的字符串
     *
     * @param s
     * @return
     */
    public static String getDFMoneyNumber(String s) {
        try {
            return getBMoneyNumber(getBigDecimal(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * BigDecimal 类型的数据转化为 整数有效数字的截取字符串
     *
     * @param bigDecimal
     * @return
     */
    public static String getBMoneyNumber(BigDecimal bigDecimal) {
        try {
            if (bigDecimal != null) {
                bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
                DecimalFormat df = new DecimalFormat(",###,##0");
                df.setRoundingMode(RoundingMode.DOWN);
                return df.format(bigDecimal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 四舍五入保留两位有效数字
     *
     * @param s
     * @return
     * @author ttarfall
     * @date 2015-9-10 下午3:28:36
     */
    public static String getTwoDecimals(String s) {
        try {
            BigDecimal d = getBigDecimal(s);
            if (d != null)
                return getTwoDecimals(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    /**
     * 四舍五入保留两位有效数字
     *
     * @param d
     * @return
     * @author ttarfall
     * @date 2015-9-10 下午3:28:36
     */
    public static String getTwoDecimals(BigDecimal d) {
        try {
            DecimalFormat df2 = new DecimalFormat("###0.00");
            df2.setRoundingMode(RoundingMode.HALF_UP);
            return df2.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";

    }

    /**
     * 四舍五入保留1位有效数字
     *
     * @param s
     * @return
     * @author ttarfall
     * @date 2015-9-10 下午3:28:36
     */
    public static String getOneDecimals(String s) {
        try {
            BigDecimal d = getBigDecimal(s);
            if (d != null)
                return getOneDecimals(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.0";
    }

    /**
     * 四舍五入保留1位有效数字
     *
     * @param d
     * @return
     * @author ttarfall
     * @date 2015-9-10 下午3:28:36
     */
    public static String getOneDecimals(BigDecimal d) {
        try {
            DecimalFormat df = new DecimalFormat("###0.0");
            df.setRoundingMode(RoundingMode.HALF_UP);
            return df.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.0";

    }

    /**
     * 四舍五入保留整数
     *
     * @param s
     * @return
     * @author ttarfall
     * @date 2015-9-10 下午3:28:36
     */
    public static String getIntDecimals(String s) {
        try {
            BigDecimal d = getBigDecimal(s);
            return getIntDecimals(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 四舍五入保留整数
     *
     * @param d
     * @return
     * @author ttarfall
     * @date 2015-9-10 下午3:28:36
     */
    public static String getIntDecimals(BigDecimal d) {
        try {
            DecimalFormat df = new DecimalFormat("###0");
            df.setRoundingMode(RoundingMode.HALF_UP);
            return df.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";

    }


    /**
     * 字符串类型的数字或者金额数字转换为BigDecimal类型
     *
     * @param s
     * @return
     * @author ttarfall
     * @date 2015-9-10 下午3:20:03
     */
    public static BigDecimal getBigDecimal(String s) {
        try {
            if (!TextUtils.isEmpty(s)) {
                return new BigDecimal(getMoneyString(s));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取int类型的进度
     *
     * @param progress
     * @return
     */
    public static int getIntProgress(float progress) {
        int p = 0;
        if (progress < 0.0f) {
            p = 0;
        } else if (progress < 1.0f) {
            p = 1;
        } else if (progress > 99f && progress < 100f) {
            p = 99;
        } else if (progress >= 100f) {
            p = 100;
        } else {
            try {
                String str = getIntDecimals(progress + "");
                p = Integer.parseInt(str);
            } catch (Exception e) {

            }
        }
        return p;
    }


    public static int getIntNumber(String number) {
        try {
            if (!TextUtils.isEmpty(number)) {
                return getIntNumber(getMoneyNumber(number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getLongNumber(String number) {
        try {
            if (!TextUtils.isEmpty(number)) {
                return getLongNumber(getMoneyNumber(number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取整数
     *
     * @param number
     * @return
     */
    public static int getIntNumber(double number) {
        try {
            if (number != 0.0f) {
                DecimalFormat df = new DecimalFormat("##0");
                df.setRoundingMode(RoundingMode.HALF_UP);
                double n = Math.abs(number - 0.49);
                return Integer.parseInt(df.format(n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getLongNumber(double number) {
        try {
            if (number != 0.0f) {
                DecimalFormat df = new DecimalFormat("##0");
                df.setRoundingMode(RoundingMode.HALF_UP);
                double n = Math.abs(number - 0.49);
                return Long.parseLong(df.format(n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 格式化金额
     *
     * @param s
     * @return
     */
    public static double getMoneyNumber(String s) {
        try {
            if (!TextUtils.isEmpty(s)) {
                if (s.contains(",")) {
                    s = s.replace(",", "");
                }
                s = s.trim();
                return Double.valueOf(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    /**
     * 是否大于10000
     *
     * @param money
     * @return
     */
    public static boolean isMillion(double money) {
        return money - 10000.00 >= 0;
    }

}
