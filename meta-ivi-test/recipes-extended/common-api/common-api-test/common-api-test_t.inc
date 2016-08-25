#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "Common API C++ runtime"
}

_run_dir=/opt/tests/common-api-test
_run_1() {
  ${_run_dir}/E05ManagerService &
  ${_run_dir}/E05ManagerClient &
  sleep 5
  kill -9 `pidof E05ManagerClient`
  kill -9 `pidof E05ManagerService`
  sleep 1
}

run_1() {
  if [ "x${1}" != "x" ]; then
    _run_1 >& /tmp/t
    _c1=`cat /tmp/t | grep "Special device 0 detected"`
    _c2=`cat /tmp/t | grep "Special device 0 removed"`
    _c3=`cat /tmp/t | grep "Device 1 detected"`
    _c4=`cat /tmp/t | grep "Device 1 removed"`
    if [ "x${_c1}" != "x" -a "x${_c2}" != "x" -a \
	"x${_c3}" != "x" -a "x${_c4}" != "x" ]; then
       echo " PASSED"
    else
       echo " FAILED"
    fi
  else
    _run_1
  fi
}
