package com.study.ht.studyandroid.utils;

import android.net.ParseException;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输入校验工具类
 *
 * @author wang
 */
public class IdCardCheckUtil {
    /**
     * 包含字母
     *
     * @param word
     * @return
     */
    public static boolean CharhasLetter(String word) {
        boolean sign = false;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i))) {
                sign = true;
            }
        }
        return sign;
    }

    /**
     * 包含数字
     *
     * @param word
     * @return
     */
    public static boolean CharhasNum(String word) {
        boolean sign = false;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) {
                sign = true;
            }
        }
        return sign;
    }

    /**
     * 包含字母与字母
     *
     * @param word
     * @return
     */
    public static boolean CharhasNumAndLetter(String word) {
        if (!CharhasNum(word)) {
            return false;
        }
        if (!CharhasLetter(word)) {
            return false;
        }
        return true;
    }

    /**
     * 只能含字母 或 数字
     *
     * @param word
     * @return
     */
    public static boolean CharOnlyhasNumOrLetter(String word) {
        if (!word.matches("^[a-zA-Z0-9]+$")) {
            return false;
        }
        return true;
    }

    /**
     * 只能含有数字 与 字母
     *
     * @param word
     * @return
     */
    public static boolean CharOnlyhasNumAndLetter(String word) {
        if (CharhasNumAndLetter(word)) {
            if (!word.matches("^[a-zA-Z0-9]+$")) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * -------------Jpush-----------------
     */
    // 校验Tag Alias 只能是数字,英文字母和中文
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_-]{0,}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * 功能：身份证的有效验证
     *
     * @param IDStr 身份证号
     * @return 有效：true 无效：false
     * @throws ParseException
     * @throws java.text.ParseException
     * @throws NumberFormatException
     */
    public static boolean isIDCardValidate(String IDStr) {
        try {

            String errorInfo = "";// 记录错误信息
            String Ai = "";

            // ================ 号码的长度 15位或18位 ================
            if (IDStr.length() != 15 && IDStr.length() != 18) {
                errorInfo = "号码长度应该为15位或18位。";
                return false;
            }

            // ================ 数字 除最后以为都为数字 ================
            if (IDStr.length() == 18) {
                Ai = IDStr.substring(0, 17);

            } else if (IDStr.length() == 15) {
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
            }

            if (isNumeric(Ai) == false) {
                errorInfo = "15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
                return false;
            }

            // ================ 出生年月是否有效 ================
            String strYear = Ai.substring(6, 10);// 年份
            String strMonth = Ai.substring(10, 12);// 月份
            String strDay = Ai.substring(12, 14);// 月份

            if (isDate(strYear + "-" + strMonth + "-"
                    + strDay) == false) {
                errorInfo = "生日无效。";
                return false;
            }

            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "生日不在有效范围。";
                return false;
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                errorInfo = "月份无效";
                return false;
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                errorInfo = "日期无效";
                return false;
            }

            // ================ 地区码时候有效 ================
            Hashtable h = GetAreaCode();
            if (h.get(Ai.substring(0, 2)) == null) {
                errorInfo = "地区编码错误。";
                return false;
            }


            if (IDStr.length() == 18) {
                String rlt = IDStr.substring(17, 18);
                if (rlt.equals("x") || rlt.equals("y") || rlt.equals("z") || rlt.equals("X") || rlt.equals("Y") || rlt.equals("Z") || isNumeric(rlt)) {
                    Ai = Ai + rlt;
                    if (Ai.equals(IDStr) == false) {
                        errorInfo = "身份证无效，最后一位字母错误";
                        return false;
                    }
                } else
                    return false;
            } else {
                return true;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
        /*
         * 判断一个字符时候为数字 if(Character.isDigit(str.charAt(0))) { return true; }
		 * else { return false; }
		 */
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {

        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if ((strDate != null)) {
            Pattern pattern = Pattern.compile(datePattern1);
            Matcher match = pattern.matcher(strDate);
            if (match.matches()) {
                pattern = Pattern.compile(datePattern2);
                match = pattern.matcher(strDate);
                return match.matches();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    public static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 加密用户姓名，只显示首位，其余显示*例如：张三显示张* 张三三显示张** 张显示张
     *
     * @param userName 用户姓名
     * @return
     */
    public static String getUserName(String userName) {
        if (!TextUtils.isEmpty(userName) && userName.length() > 1) {
            char[] chars = userName.trim().toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (i > 0) {
                    chars[i] = '*';
                }
            }
            userName = String.valueOf(chars);
//            if (userName.length() < 1) {
//                userName = userName + "*";
//            }
        } else
            userName = "";
        return userName;
    }


    /**
     * 获取格式化的电话
     */
    public static String getFormatPhone(String phone) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(phone)) {
            phone = phone.trim();
            if (phone.length() > 3) {
                String sub = phone.substring(0, 3);
                sb.append(sub);
                sb.append(" ");
                if (phone.length() > 7) {
                    sub = phone.substring(3, 7);
                    sb.append(sub);
                    sb.append(" ");
                    if (phone.length() >= 11) {
                        sub = phone.substring(7, phone.length());
                        sb.append(sub);
                    } else
                        sb.append(phone.substring(4, phone.length()));
                } else
                    sb.append(phone.substring(4, phone.length()));
            } else
                sb.append(phone);
        } else
            sb.append(phone);
        return sb.toString();
    }

}
