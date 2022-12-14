#include <jni.h>
#include <string>
#include <fstream>
#include <cstring>
#include <iostream>
#include <string>
#include "data.pb.h"
#include <streambuf>

using namespace std;
using namespace google::protobuf;
string ekuCode = "ColombiA";
string cpyText = "";
string decryptKey = "Monksoft";

std::string item_name;
std::ifstream nameFileIn;
std::ofstream nameFileOut;

// make ekuCode length same as text
string getNewKey(string msg, string key) {
    if(key.length() < msg.length()) {
        for (int i = 0; i <= msg.length() - key.length(); ++i)
            key += key[i];
    }
    return key;
}

string getOriginalLowerUpperCase(string msg){
    string newMsg = "";
    for (int i = 0; i < msg.length(); i++) {
        string t = "";
        string cc = cpyText[i]  + t;
        string ccc = cpyText[i]  + t;
        string f = msg[i] + t;

        transform(ccc.begin(), ccc.end(), ccc.begin(), ::toupper);

        if(cc == ccc) {
            transform(f.begin(), f.end(), f.begin(), ::toupper);
            newMsg += f;
        } else {
            transform(f.begin(), f.end(), f.begin(), ::tolower);
            newMsg += f;
        }
    }
    return newMsg;
}

string encrypt(string msg, string key) {

    cpyText = msg;
    string encryptedMsg = "";

    key = getNewKey(msg, key);

    transform(key.begin(), key.end(), key.begin(), ::toupper);
    transform(msg.begin(), msg.end(), msg.begin(), ::toupper);

    // process to ecrypt msg
    for(int i = 0; i < msg.length(); ++i)
        encryptedMsg += (char) (((msg[i] + key[i]) % 26) + 'A');

    // change to original lower/upper case msg
    return getOriginalLowerUpperCase(encryptedMsg);
}

string decrypt(string msg, string key) {

    cpyText = msg;
    string decryptedMsg = "";

    key = getNewKey(msg, key);

    transform(key.begin(), key.end(), key.begin(), ::toupper);
    transform(msg.begin(), msg.end(), msg.begin(), ::toupper);

    // process to decrypt msg
    for(int i = 0; i < msg.length(); ++i)
        decryptedMsg += (char) (((msg[i] - key[i] +26) % 26) + 'A');

    // change to original lower/upper case msg
    return getOriginalLowerUpperCase(decryptedMsg);
}

bool readBuffer() {
    try {
        nameFileIn.open("/data/data/com.monksoft.monklogin/files/datastore/data.mk");

        if(nameFileIn.good()){
            nameFileIn >> item_name;
            std::cout << item_name;
            string r = item_name.substr(1, item_name.length() - 1);

            remove("/data/data/com.monksoft.monklogin/files/datastore/data.mk");
            nameFileIn.close();

            nameFileOut.open("/data/data/com.monksoft.monklogin/files/datastore/data.mk");
            nameFileOut.close();

            if (decrypt(r, decryptKey) == ekuCode) {
                return true;
            }
        }
    } catch (exception){
        return false;
    }
    return false;
}

extern "C" jboolean Java_com_monksoft_monklogin_presentation_view_LoginFragment_sendMonkCode (
        JNIEnv* env,
        jobject /* this */) {
    return readBuffer();
}

int main() { return 1; }