package com.monksoft.monklogin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.monksoft.monklogin.utils.EncryptDecrypt;

import org.junit.Before;
import org.junit.Test;

public class EncryptDecryptTest {

    private String msg;
    private String wrongMsg;
    private String key;
    private String encryptedMsg;
    private String wrongEncryptedMsg;

    @Before
    public void setup(){
        msg = "ColombiA";
        wrongMsg = "EcuadoR";
        key = "Ekumen";
        encryptedMsg = "GyfaqomK";
        wrongEncryptedMsg = "Gyfaqomh";
    }

    @Test
    public void given_a_msg_and_key_then_check_encrypted_msg_is_correct() {
        assertEquals(encryptedMsg, EncryptDecrypt.encrypt(msg, key));
    }

    @Test
    public void given_an_encrypted_msg_and_key_then_check_original_msg_is_correct() {
        assertEquals(msg, EncryptDecrypt.decrypt(encryptedMsg, key));
    }

    @Test
    public void given_a_msg_and_key_then_check_encrypted_msg_is_not_correct() {
        assertNotEquals(wrongEncryptedMsg, EncryptDecrypt.encrypt(msg, key));
    }

    @Test
    public void given_an_encrypted_msg_and_key_then_check_original_msg_is_not_correct() {
        assertNotEquals(wrongMsg, EncryptDecrypt.decrypt(encryptedMsg, key));
    }
}