package com.monksoft.monklogin.utils;

public class EncryptDecrypt {

    static private String cpyText;

    public static String encrypt(String msg, String key) {

        cpyText = msg;

        String encryptedMsg = "";

        key = getNewKey(msg, key).toUpperCase();
        msg = msg.toUpperCase();

        // process to encrypt msg
        for(int i = 0; i < msg.length(); ++i)
            encryptedMsg += (char) (((msg.charAt(i) + key.charAt(i)) % 26) + 'A');

        // change to original lower/upper case msg
        return getOriginalLowerUpperCase(encryptedMsg);
    }

    public static String decrypt(String msg, String key) {

        cpyText = msg;
        String decryptedMsg = "";

        key = getNewKey(msg, key).toUpperCase();
        msg = msg.toUpperCase();

        // process to decrypt msg
        for(int i = 0; i < msg.length(); ++i)
            decryptedMsg += (char) (((msg.charAt(i) - key.charAt(i) +26) % 26) + 'A');

        // change to original lower/upper case msg
        return getOriginalLowerUpperCase(decryptedMsg);
    }

    // make key length same as text
    public static String getNewKey(String msg, String key) {
        if(key.length() < msg.length()) {
            for (int i = 0; i <= msg.length() - key.length(); ++i)
                key += key.charAt(i) + "";
        }
        return key;
    }

    public static String getOriginalLowerUpperCase(String msg){
        String newMsg = "";
        for (int i = 0; i < msg.length(); i++) {
            String c = cpyText.charAt(i) + "";
            String f = msg.charAt(i) + "";

            if(c.equals(c.toLowerCase()))
                newMsg += f.toLowerCase();
            else
                newMsg += f.toUpperCase();
        }
        return newMsg;
    }
}