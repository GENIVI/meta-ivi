#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "Node State Manager"
}

run_1() {
  cd /usr/lib
  mv libNodeStateMachineStub.so libNodeStateMachineStub.so.bak
  cp /opt/tests/node-state-manager/lib/libNodeStateMachineTest.so \
     libNodeStateMachineStub.so
  ldconfig
  sleep 1

  systemctl start node-startup-controller

  /opt/tests/node-state-manager/NodeStateTest

  systemctl stop node-startup-controller
  kill -9 `pidof NodeStateManager`

  rm -f libNodeStateMachineStub.so
  mv libNodeStateMachineStub.so.bak libNodeStateMachineStub.so
  ldconfig
}
