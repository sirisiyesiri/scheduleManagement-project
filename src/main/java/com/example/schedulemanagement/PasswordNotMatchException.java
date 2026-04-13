package com.example.schedulemanagement;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException() {
        super("비밀번호가 일치하지 않습니다.");
        // IllegalArgumentException의 경우 "인자가 잘못된 경우"에 대한 exception으로
        // 비밀번호 불일치 시에 사용하기엔 너무 범용적 -> 해당 경우에 대한 exception만듬
    }
}
