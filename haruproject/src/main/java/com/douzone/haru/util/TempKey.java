package com.douzone.haru.util;

import java.util.Random;

/*
 * 작성자 : 이승현
 * 회원가입 이메일 인증 난수, 비밀번호 변경 이메일 발송시 
 * 생성하는 난수 생성 클래스
 */
public class TempKey {
    
    private boolean lowerCheck;
    private int size;
    
    public String getKey(int size, boolean lowerCheck) {
        this.size = size;
        this.lowerCheck = lowerCheck;
        return init();
    }
    
    private String init() {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        do {
            num = ran.nextInt(75)+48;
            if((num>=48 && num<=57) || (num>=65 && num<=90) || (num>=97 && num<=122)) {
                sb.append((char)num);
            }else {
                continue;
            }
        } while (sb.length() < size);
        if(lowerCheck) {
            return sb.toString().toLowerCase();
        }
        return sb.toString();
    }
    
}