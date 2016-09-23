#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "Common API C++ test code"
}

_run_dir=/opt/tests/capicxx-perf
_run_1() {
  ${_run_dir}/capicxx-server &
  ${_run_dir}/capicxx-client
  kill -9 `pidof capicxx-server`
  sleep 1
}

run_1() {
  if [ "x${1}" != "x" ]; then
    _run_1 >& /tmp/t
    _test_completed=`cat /tmp/t | grep "test completed"`
    _10000_sent=`cat /tmp/t | grep "sync messages sent" | grep 10000`
    if [ "x${_test_completed}" != "x" -a "y${_10000_sent}" != "y" ]; then
       echo " PASSED"
    else
       echo " FAILED"
    fi
  else
    _run_1
  fi
}
