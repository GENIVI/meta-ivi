#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "Common API C++ runtime"
}

run_1() {
  /opt/tests/common-api-test/E05ManagerService &
  /opt/tests/common-api-test/E05ManagerClient &
  sleep 5
  kill -9 `pidof E05ManagerClient`
  kill -9 `pidof E05ManagerService`
  sleep 1
}
