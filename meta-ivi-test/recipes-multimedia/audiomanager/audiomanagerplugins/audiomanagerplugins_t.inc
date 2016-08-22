#!/bin/sh
get_list() {
  return 4
}

get_title() {
  echo "AudioManagerPlugins"
}

run_1() {
  /opt/tests/AudioManagerPlugins/AmCommandSenderCAPITest
}

run_2() {
  /opt/tests/AudioManagerPlugins/AmControlReceiverShadowTest
}

run_3() {
  /opt/tests/AudioManagerPlugins/AmRoutingInterfaceCAPITests
}

run_4() {
  /opt/tests/AudioManagerPlugins/AmRoutingReceiveAsyncTest
}
