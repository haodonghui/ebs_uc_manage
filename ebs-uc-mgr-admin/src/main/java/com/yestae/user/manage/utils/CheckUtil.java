package com.yestae.user.manage.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @Package cn.com.zodiac
 * @ClassName
 * @Author Mr.Hao
 * @Date 2019/3/6 0006 17:32
 */
public class CheckUtil {
    public static void main(String[] args) {
    	Set<String> set = getZodiac("654001198912102518");
    	System.err.println(set);
        boolean flag = isChineseWord("磨憨·审");
        if(!flag){
            System.out.println(11);
        }
        System.out.println(flag);
    }
    
    private static Map<Integer, String> zodiacMap = Maps.newHashMap();
    //1：鼠，2：牛，3、虎，4：兔，5：龙，6：蛇,7:马，8:羊，9:猴，10:鸡，11:狗，12:猪
    static {
    	zodiacMap.put(1, "鼠");
    	zodiacMap.put(2, "牛");
    	zodiacMap.put(3, "虎");
    	zodiacMap.put(4, "兔");
    	zodiacMap.put(5, "龙");
    	zodiacMap.put(6, "蛇");
    	zodiacMap.put(7, "马");
    	zodiacMap.put(8, "羊");
    	zodiacMap.put(9, "猴");
    	zodiacMap.put(10, "鸡");
    	zodiacMap.put(11, "狗");
    	zodiacMap.put(12, "猪");
    }

    public static boolean checkIdNo(String idNumber){
        if(StringUtils.isBlank(idNumber)){
            return false;
        }
        char[] id = {};
        for (int i = 0; i < idNumber.length(); i++) {
            id = Arrays.copyOf(id, id.length + 1);
            id[id.length - 1] = idNumber.charAt(i);
        }
        boolean IsFormRight = verForm(idNumber);
        if (IsFormRight) {
            boolean IsCorrect = verify(id);
            if (IsCorrect) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    public static boolean isChineseWord(String realName){

    	if(StringUtils.isBlank(realName)){
    		return false;
    	}
    	String pattern = "[\u4e00-\u9fa5·]+";

    	boolean isMatch = Pattern.matches(pattern, realName);

    	return isMatch;
	}
    
    /**
     * 
     * 生肖校验
     *
     * @param idNo
     * @param zodiac
     * @return
     * @throws
     */
    public static boolean checkZodiac (String idNo, int zodiac){
    	SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String idNoDate = idNo.substring(6, 14).substring(0, 4) + "-" + idNo.substring(6, 14).substring(4, 6) + "-" +
				idNo.substring(6, 14).substring(idNo.substring(6, 14).length() - 2);
		Calendar today = Calendar.getInstance();
		try {
			today.setTime(chineseDateFormat.parse(idNoDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		CalculationZodiacUtils calculationZodiacUtils = new CalculationZodiacUtils(today);
		Integer zodiacByIdNoDate = calculationZodiacUtils.animalsYear();
		
		Lunar lunar = new Lunar();
		lunar.lunarDay = Integer.parseInt(idNo.substring(6, 14).substring(idNo.substring(6, 14).length() - 2));
		lunar.lunarMonth = Integer.parseInt(idNo.substring(6, 14).substring(4, 6));
		lunar.lunarYear = Integer.parseInt(idNo.substring(6, 14).substring(0, 4));
		Solar lunarToSolar = LunarSolarConverterUtils.LunarToSolar(lunar);
		String solarMonth = null;
		String solarDay = null;
		if (String.valueOf(lunarToSolar.getSolarDay()).length() == 2) {
			solarDay = String.valueOf(lunarToSolar.getSolarDay());
		} else if (String.valueOf(lunarToSolar.getSolarDay()).length() < 2) {
			solarDay = 0 + String.valueOf(lunarToSolar.getSolarDay());
		}
		if (String.valueOf(lunarToSolar.getSolarMonth()).length() == 2) {
			solarMonth = String.valueOf(lunarToSolar.getSolarMonth());
		} else if (String.valueOf(lunarToSolar.getSolarMonth()).length() < 2) {
			solarMonth = 0 + String.valueOf(lunarToSolar.getSolarMonth());
		}
		String lunarToSolarDate = lunarToSolar.getSolarYear() + "-" + solarMonth + "-" + solarDay;//1988-05-20
		try {
			today.setTime(chineseDateFormat.parse(lunarToSolarDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		CalculationZodiacUtils calculationZodiacUtils2 = new CalculationZodiacUtils(today);
		Integer zodiacByLunarToSolarDate = calculationZodiacUtils2.animalsYear();
		if(zodiacByIdNoDate==zodiacByLunarToSolarDate){
			if(zodiacByIdNoDate!=zodiac){
				return false;
			}
		}else{
			if (zodiac!=zodiacByIdNoDate && zodiac!=zodiacByLunarToSolarDate){
				return false;
			}
		}
    	return true;
    }

    public static Set<String> getZodiac(String idNo){
    	SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String idNoDate = idNo.substring(6, 14).substring(0, 4) + "-" + idNo.substring(6, 14).substring(4, 6) + "-" +
				idNo.substring(6, 14).substring(idNo.substring(6, 14).length() - 2);
		Calendar today = Calendar.getInstance();
		try {
			today.setTime(chineseDateFormat.parse(idNoDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		CalculationZodiacUtils calculationZodiacUtils = new CalculationZodiacUtils(today);
		Integer zodiacByIdNoDate = calculationZodiacUtils.animalsYear();
		
		Lunar lunar = new Lunar();
		lunar.lunarDay = Integer.parseInt(idNo.substring(6, 14).substring(idNo.substring(6, 14).length() - 2));
		lunar.lunarMonth = Integer.parseInt(idNo.substring(6, 14).substring(4, 6));
		lunar.lunarYear = Integer.parseInt(idNo.substring(6, 14).substring(0, 4));
		Solar lunarToSolar = LunarSolarConverterUtils.LunarToSolar(lunar);
		String solarMonth = null;
		String solarDay = null;
		if (String.valueOf(lunarToSolar.getSolarDay()).length() == 2) {
			solarDay = String.valueOf(lunarToSolar.getSolarDay());
		} else if (String.valueOf(lunarToSolar.getSolarDay()).length() < 2) {
			solarDay = 0 + String.valueOf(lunarToSolar.getSolarDay());
		}
		if (String.valueOf(lunarToSolar.getSolarMonth()).length() == 2) {
			solarMonth = String.valueOf(lunarToSolar.getSolarMonth());
		} else if (String.valueOf(lunarToSolar.getSolarMonth()).length() < 2) {
			solarMonth = 0 + String.valueOf(lunarToSolar.getSolarMonth());
		}
		String lunarToSolarDate = lunarToSolar.getSolarYear() + "-" + solarMonth + "-" + solarDay;//1988-05-20
		try {
			today.setTime(chineseDateFormat.parse(lunarToSolarDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		CalculationZodiacUtils calculationZodiacUtils2 = new CalculationZodiacUtils(today);
		Integer zodiacByLunarToSolarDate = calculationZodiacUtils2.animalsYear();
		
		Set<String> set = Sets.newHashSet();
		set.add(zodiacMap.get(zodiacByLunarToSolarDate));
		set.add(zodiacMap.get(zodiacByIdNoDate));
		
		return set;
    	
    }
    public static boolean numberCheck(String number, int less, int more){
    	if(StringUtils.isBlank(number)){
    		return false;
    	}
    	String s = "{"+less+","+more+"}";
    	Pattern pattern = Pattern.compile("\\d"+s);
    	return pattern.matcher(number).matches();
    }

    private static boolean verForm(String num) {
        String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
        if (!num.matches(reg)) {
            return false;
        }
        return true;
    }
    private static boolean verify(char[] id) {
        int sum = 0;
        //前十七位加权因子
        int w[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        char[] ch = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        for (int i = 0; i < id.length - 1; i++) {
            sum += (id[i] - '0') * w[i];
        }
        int c = sum % 11;
        char code = ch[c];
        char last = id[id.length-1];
        last = last == 'x' ? 'X' : last;
        return last == code;
    }
}
