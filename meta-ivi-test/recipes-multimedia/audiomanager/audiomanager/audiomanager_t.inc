#!/bin/sh
get_list() {
  return 7
}

get_title() {
  echo "audiomanager"
}

run_1() {
  /opt/tests/audiomanager/AmControlInterfaceTest
}

run_2() {
  /opt/tests/audiomanager/AmMapHandlerTest
}

run_3() {
  /opt/tests/audiomanager/AmRouterMapTest
}

run_4() {
  /opt/tests/audiomanager/AmRouterTest
}

run_5() {
  /opt/tests/audiomanager/AmRoutingInterfaceTest
}

run_6() {
  /opt/tests/audiomanager/AmSocketHandlerTest
}

run_7() {
  /opt/tests/audiomanager/AmTelnetServerTest
}
