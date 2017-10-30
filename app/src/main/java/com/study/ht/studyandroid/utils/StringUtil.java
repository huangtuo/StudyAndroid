package com.study.ht.studyandroid.utils;

import android.text.TextUtils;
import android.widget.TextView;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ttarfall
 * @date 2016-09-13 18:01
 */
public class StringUtil {

    public static boolean isEmpty(CharSequence s) {
        if (TextUtils.isEmpty(s))
            return true;
        if (s.toString().trim().length() == 0)
            return true;
        if ("null".equals(s)) {
            return true;
        }
        return false;
    }

    /**
     * 判断文件是否是APK文件
     *
     * @param fileName
     * @return
     */
    public static boolean isAPK(String fileName) {
        if (isEmpty(fileName)) {
            return false;
        }
        int index = fileName.lastIndexOf(".");
        if (index < 0) {
            return false;
        }
        String apk = fileName.substring(index + 1);
        if (isEmpty(apk)) {
            return false;
        }
        return apk.toLowerCase().equals("apk");
    }

    /**
     * 获取url中的参数
     *
     * @param url
     * @param key
     * @return 返回String
     */
    public static String getURLString(String url, String key, String defValue) {
        try {
            if (!isEmpty(url) && !isEmpty(key)) {
                URL u = new URL(url);
                String p = u.getQuery();
                String[] parameters = p.split("&");
                for (int i = 0; i < parameters.length; i++) {
                    String[] pp = parameters[i].split("=");
                    if (key.equals(pp[0])) {
                        return pp[1];
                    }
                }
            }
        } catch (Exception e) {

        }
        return defValue;
    }

    /**
     * 获取url中参数
     *
     * @param url
     * @param key
     * @return 返回int
     */
    public static int getURLInt(String url, String key, int defValue) {
        try {
            if (!isEmpty(url) && !isEmpty(key)) {
                URL u = new URL(url);
                String p = u.getQuery();
                String[] parameters = p.split("&");
                for (int i = 0; i < parameters.length; i++) {
                    String[] pp = parameters[i].split("=");
                    if (key.equals(pp[0])) {
                        return Integer.valueOf(pp[1]);
                    }
                }
            }
        } catch (Exception e) {

        }
        return defValue;
    }

    /**
     * 字符串串只能为字母数字下划线组合，并且首字母不能为数字,并且长度为3-16位字符
     *
     * @param msg
     * @return
     */
    public static boolean isLetNumUndline(CharSequence msg) {
        if (!isEmpty(msg)) {
            String reg = "^[a-zA-Z_][\\w_]{2,15}$";
            return msg.toString().matches(reg);
        }
        return false;
    }

    /**
     * 检查字符串是否是邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(CharSequence email) {
        if (!isEmpty(email)) {
            String reg = "^([\\w\\.-]+)@([\\w\\.-]+)\\.([a-z\\.]{2,6})$";
            return email.toString().matches(reg);
        }
        return false;
    }

    public static boolean isPhoneNumber(CharSequence phone) {
        if (!isEmpty(phone)) {
            String reg = "^1[34578]\\d{9}$";
            return phone.toString().matches(reg);
        }
        return false;
    }

    /**
     * 检查密码格式是否正确
     *
     * @param pwd
     * @return
     */
    public static boolean isPassword(CharSequence pwd) {
        if (!isEmpty(pwd)) {
            String p = pwd.toString();
            String n = "\\d+";//匹配是否包含数字
            Matcher mn = Pattern.compile(n).matcher(pwd);
            String g = "[a-zA-Z]+";//匹配是否包含字母
            Matcher mg = Pattern.compile(g).matcher(pwd);
            String reg = "^[\\S]{6,16}$";//匹配任何非空白字符。等价于[^ \f\n\r\t\v]
            return mn.find() && mg.find() && p.matches(reg);
        }
        return false;
    }

    /**
     * 加密邮箱
     *
     * @param email
     * @return
     */
    public static CharSequence getEmail(CharSequence email) {
        if (isEmail(email)) {
            String e = email.toString().trim();
            int index = e.indexOf("@");
            if (index > 0) {
                String e1;
                String e2;
                if (index > 2) {
                    e1 = e.substring(0, 2) + "***";
                    e2 = e.substring(index - 1);
                } else {
                    if (index > 1) {
                        e1 = e.substring(0, 1) + "***";
                        e2 = e.substring(index - 1);
                    } else {
                        e1 = e.substring(0, index) + "***";
                        e2 = e.substring(index);
                    }
                }
                email = e1 + e2;
            }
        }
        return email;
    }

    /**
     * 返回银行卡尾号
     *
     * @param number
     * @return
     */
    public static CharSequence getCardNumber4(CharSequence number) {
        if (!isEmpty(number) && number.length() > 4) {
            int l = number.length();
            return number.subSequence(l - 4, l);
        }
        return number;
    }

    /**
     * 加密手机号返回
     *
     * @param phoneNumber
     * @return
     */
    public static CharSequence getPhoneNumber(CharSequence phoneNumber) {
        if (isPhoneNumber(phoneNumber)) {
            return phoneNumber.subSequence(0, 3) + "****" + phoneNumber.subSequence(7, 11);
        }
        return phoneNumber;
    }

    /**
     * 返回手机尾号
     *
     * @param phoneNumber
     * @return
     */
    public static CharSequence getPhoneNumber4(CharSequence phoneNumber) {
        if (isPhoneNumber(phoneNumber)) {
            return phoneNumber.subSequence(7, 11);
        }
        return phoneNumber;
    }

    /**
     * 判断字符串是否是金额
     *
     * @param money
     * @return
     */
    public static boolean isMoneyNumber(CharSequence money) {
        if (!isEmpty(money)) {
            String m = NumberUtil.getMoneyString(money.toString());
            String reg = "^(([1-9]\\d*)|0)(\\.[\\d]{0,2})?$";
            return m.matches(reg);
        }
        return false;
    }

    /**
     * 统一去空格
     *
     * @param textView
     * @return
     */
    public static String TrimText(TextView textView) {
        if (null != textView) {
            return textView.getText().toString().trim();
        }

        return "";
    }

    /**
     * 全角转半角
     */
    public static String SBC2DBC(String QJstr) {
        try {
            String outStr = "";
            String Tstr = "";
            byte[] b = null;

            for (int i = 0; i < QJstr.length(); i++) {
                try {
                    Tstr = QJstr.substring(i, i + 1);
                    b = Tstr.getBytes("unicode");
                } catch (java.io.UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (b[3] == -1) {
                    b[2] = (byte) (b[2] + 32);
                    b[3] = 0;
                    try {
                        outStr = outStr + new String(b, "unicode");
                    } catch (java.io.UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else
                    outStr = outStr + Tstr;
            }
            return outStr;
        } catch (Exception e) {
            return QJstr;
        }

    }

    /**
     * 冒泡排序
     *
     * @param num
     * @return
     */
    public static long[] maopao(long num[]) {
        int i, j;
        long temp;
        for (i = 0; i < num.length - 1; i++) {
            for (j = 0; j < num.length - 1 - i; j++) {
                if (num[j] > num[j + 1])//交换元素
                {
                    temp = num[j + 1];
                    num[j + 1] = num[j];
                    num[j] = temp;
                }
            }
        }
        return num;
    }

    /**
     * 冒泡排序
     *
     * @param num
     * @return 二维数组
     */
    public static long[][] maopao(long num[][]) {
        int i, j;
        long[] temp;
        for (i = 0; i < num.length - 1; i++) {
            for (j = 0; j < num.length - 1 - i; j++) {
                if (num[j][0] > num[j + 1][0])// 交换元素
                {
                    temp = num[j + 1];
                    num[j + 1] = num[j];
                    num[j] = temp;
                }
            }
        }
        return num;
    }


    /**
     * 功能：身份证的有效验证
     * @param id  有效：true 无效：false
     * @return
     */
    public static boolean isIDCardValidate(String id) {
        return IdCardCheckUtil.isIDCardValidate(id);
    }

}
