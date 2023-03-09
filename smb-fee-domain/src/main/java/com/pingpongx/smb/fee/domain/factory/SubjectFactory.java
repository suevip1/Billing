package com.pingpongx.smb.fee.domain.factory;

import com.pingpongx.smb.fee.domain.module.Subject;

public class SubjectFactory {

    public static Subject deFaultSubject(){
        return newSubject("default","PingPong default");
    }

    public static Subject newSubject(String code,String name){
        Subject subject = new Subject();
        subject.setCode(code);
        subject.setDisplayName(name);
        return subject;
    }

}
