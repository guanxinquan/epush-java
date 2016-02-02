package com.epush;

import com.ericsson.otp.erlang.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by guanxinquan on 16/2/1.
 */
public class Test {

    public static void main(String[] args) throws OtpErlangDecodeException, IOException {
//        byte[] b = new byte[]{104,6,100,0,5,108,111,103,105,110,109,0,0,0,4,57,57,57,56,109,0,0,0,4,57,57,57,56,103,100,0,13,110,111,110,111,100,101,64,110,111,104,111,115,116,0,0,0,119,0,0,0,0,0,109,0,0,0,4,57,57,57,56,116,0,0,0,1,109,0,0,0,4,116,101,115,116,109,0,0,0,1,49};
//        OtpInputStream InputStream = new  OtpInputStream(b);
//        OtpErlangObject obj = OtpErlangTuple.decode(InputStream);
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(obj));
        String[] names = OtpEpmd.lookupNames();
        for(String name : names){
            System.out.println(name);
        }
    }

}
