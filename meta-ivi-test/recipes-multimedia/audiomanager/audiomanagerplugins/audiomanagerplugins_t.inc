#!/bin/sh
get_list() {
  return 5
}

get_title() {
  echo "audiomanagerplugins"
}

_run_dir=/opt/tests/audiomanagerplugins
_run() {
  if [ "x${2}" != "x" ]; then
    ${_run_dir}/$1 > /tmp/t
    tail /tmp/t | grep -E 'PASS|FAIL'
  else
    ${_run_dir}/$1
  fi
}

run_1() {
  _run AmCommandSenderCAPITest $*
}

run_2() {
  _run AmCommandSenderDbusSignalTest $*
}

run_3() {
  _run AmCommandSenderDbusTest $*s
}

run_4() {
  _run AmRoutingInterfaceCAPITests $*
}

run_5() {
  _run AmRoutingReceiveAsyncTest $*
}
