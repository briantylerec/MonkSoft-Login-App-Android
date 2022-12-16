
This application includes a login with username and Monkcode text inputs.
Works for any username but with only one Monkcode, if it's correct the next screen shows two differents greetings
depending if it is your first login or not (and logout button).

The Monkcode is encrypted in kotlin/java side when press "Login" and sent to c++ side to decypt and validate if the Monkcode is correct.


---------------------------------------------------------------------


REQUIREMENTS


1) [Android](https://www.android.com/) for your specific OS

2) Android SDK and java (11.0.17)

3) Android device Emulator or physical device (API 29: Android 10.0 Q)

4) [Protocol buffer](https://developers.google.com/protocol-buffers) for kotlin (plugin in android) and c++ (3.20.3) for your specific OS.

    Make sure you have the CMakeLists.txt (../app/src/main/cpp/) with the correct path from your protobuf instalation.

        set(Protobuf_LIBRARIES "/usr/local/protobuf/lib")
        set(Protobuf_INCLUDE_DIR "/usr/local/protobuf/include")

5) [CMAKE](https://cmake.org/download/) 3.18.1 

6) Inside Android
        [Android NDK](developer.android.com/ndk/)(25.1)
        CMAKE 3.18.1

7) Set enviroment variables
        
        export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
        export JVM_HOME=/usr/lib/jvm/java-11-openjdk-amd64/include
        export LIBRARY_PATH=$LIBRARY_PATH:/usr/local/protobuf/lib
        export PATH=$PATH:/usr/local/protobuf/bin
        export NDK=/root/Android/Sdk/ndk/25.1.8937393
        export absl_DIR=/usr/include/absl 


---------------------------------------------------------------------


STEPS TO EXECUTE PROJECT


1) Clone the repository [https://github.com/briantylerec/MonkSoft-Login-App-Android.git](https://github.com/briantylerec/MonkSoft-Login-App-Android.git) in your computer.

2) Open the project and let gradle donwload libraries.

3) Run app


---------------------------------------------------------------------


POSSIBLE PROBLEMS


-   Protobuf version, maybe need another.
-   Library [absl](https://abseil.io/docs/cpp/quickstart) (probably needed for c++ side)
-   Missing enviroment variables
-   Wrong path from requirements #4 for protofuf


---------------------------------------------------------------------


APP FEATURES

-   Android, kotlin/java, coroutines, MVVM, Junit-tests, liveData
-   Rooms and secure shared preferences
-   NDK and JNI
-   Vigenere algorithm for encrypt data in both sides (kotlin/c++)


---------------------------------------------------------------------


CREDITS

-   Developer [Brian Mora](https://www.linkedin.com/in/briantylerec/)


---------------------------------------------------------------------
