package cn.hiauth.server.utils;

import java.util.Map;

public interface SmsUtils {

    void sendSms(String phoneNumbers, String templateCode, Map<String, String> templateParam);

}
