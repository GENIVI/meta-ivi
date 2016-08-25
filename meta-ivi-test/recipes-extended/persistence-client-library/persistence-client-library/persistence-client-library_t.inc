#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "persisitence-client-library"
}

_run_dir=/opt/tests/persistence-client-library
_run_1() {
  persadmin_tool install /Data/Data.tar.gz > /tmp/t
  ${_run_dir}/persistence_client_library_test
}

run_1() {
  #systemctl restart dlt-dbus
  #systemctl restart dlt-example-user
  #systemctl restart pas-daemon
  #systemctl restart node-startup-controller
  if [ "x${1}" != "x" ]; then
    _run_1 > /tmp/t
    _c1=`cat /tmp/t | grep :P: | wc -l`
    _c2=`cat /tmp/t | grep :F: | wc -l`
    if [ "x${_c1}" = "x0" ]; then
       echo "[ FAILED ] ${_c2} tests."
    elif [ "x${_c2}" = "x0" ]; then
       echo "[ PASSED ] ${_c1} tests."
    else
       echo " ${_c1} PASSED, ${_c2} FAILED"
    fi
  else
    _run_1
  fi
  killall -9 nsm-dummy
}
