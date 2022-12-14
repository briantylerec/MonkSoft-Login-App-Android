cmake_minimum_required(VERSION 3.18.1)

project("monklogin")

set(Protobuf_LIBRARIES "/usr/local/protobuf/lib")
set(Protobuf_INCLUDE_DIR "/usr/local/protobuf/include")

include_directories(${Protobuf_LIBRARIES})
include_directories(${Protobuf_INCLUDE_DIR})

add_library( monklogin SHARED native-lib.cpp )

find_library( log-lib log )

target_link_libraries( monklogin ${log-lib} )