package com.epush.broker.event;

import com.ericsson.otp.erlang.*;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanxinquan on 16/2/1.
 */
public class EventUtil {

    public static final String getString(OtpErlangTuple t,int index) throws UnsupportedEncodingException {
        return new String(((OtpErlangBinary)t.elementAt(index)).binaryValue(),"utf-8");
    }

    public static final byte[] getBytes(OtpErlangTuple t,int index){
        return ((OtpErlangBinary)t.elementAt(index)).binaryValue();
    }

    public static final OtpErlangPid getPid(OtpErlangTuple t,int index) {
        return (OtpErlangPid) t.elementAt(index);
    }

    public static final Map<String,String> getMap(OtpErlangTuple t,int index) throws UnsupportedEncodingException {
        OtpErlangMap emap = (OtpErlangMap) t.elementAt(index);
        Map<String,String> retMap = new HashMap<String, String>();

        for(Map.Entry<OtpErlangObject, OtpErlangObject> es : emap.entrySet()){
            retMap.put(getString(es.getKey()),getString(es.getValue()));
        }
        return retMap;
    }

    public static final byte[] getPidBinary(OtpErlangPid pid){
        OtpOutputStream out = new OtpOutputStream(pid);
        return out.toByteArray();
    }


    private static String getString(OtpErlangObject obj) throws UnsupportedEncodingException {
        return new String(((OtpErlangBinary) obj).binaryValue(),"utf-8");
    }
 }
