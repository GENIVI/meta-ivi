#!/bin/sh
get_list() {
  return 2
}

get_title() {
  echo "dlt-daemon"
}

_run_dir=/opt/tests/dlt-daemon
run_1() {
  if [ "x${1}" != "x" ]; then
    ${_run_dir}/dlt-test-filetransfer > /tmp/t
    _c1=$?
    if [ "x${_c1}" = "x0" ]; then
       echo " PASSED"
    else
       echo " FAILED"
    fi
  else
    ${_run_dir}/dlt-test-filetransfer
  fi
}

run_2() {
  if [ "x${1}" != "x" ]; then
    ${_run_dir}/dlt-test-user > /tmp/t
    _c1=$?
    if [ "x${_c1}" = "x0" ]; then
       echo " PASSED"
    else
       echo " FAILED"
    fi
  else
    ${_run_dir}/dlt-test-user
  fi
}
