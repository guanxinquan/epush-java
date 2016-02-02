package com.epush.broker.sender;

import com.ericsson.otp.erlang.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by guanxinquan on 16/2/2.
 */
public class ESender {

    private static final Logger logger = LoggerFactory.getLogger(ESender.class);

    private OtpNode node;
    private OtpMbox mbox;

    public ESender(String nodeName) throws IOException {
        node = new OtpNode(nodeName);
        mbox = node.createMbox();
    }

    public void sendSync(String channel,OtpErlangPid pid){
        OtpErlangObject[] msg = new OtpErlangObject[2];
        msg[0] = new OtpErlangAtom("sync");
        msg[1] = new OtpErlangBinary(channel.getBytes(Charset.forName("utf-8")));
        OtpErlangTuple tuple = new OtpErlangTuple(msg);
        mbox.send(pid,tuple);
    }

    public void sendMessage(String channel,String oldTag,String newTag,byte[] body,OtpErlangPid pid){
        OtpErlangObject[] msg = new OtpErlangObject[5];
        msg[0] = new OtpErlangAtom("message");
        msg[1] = new OtpErlangBinary(channel.getBytes(Charset.forName("utf-8")));
        msg[2] = new OtpErlangBinary(oldTag.getBytes(Charset.forName("utf-8")));
        msg[3] = new OtpErlangBinary(newTag.getBytes(Charset.forName("utf-8")));
        msg[4] = new OtpErlangBinary(body);
        OtpErlangTuple tuple = new OtpErlangTuple(msg);
        mbox.send(pid,tuple);
    }

    public void sendKick(OtpErlangPid pid){
        OtpErlangAtom atom = new OtpErlangAtom("kick");
        mbox.send(pid,atom);
    }

}
