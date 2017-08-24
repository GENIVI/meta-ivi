#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "Node State Manager"
}

_run_dir=/opt/tests/node-state-manager
_run_1() {
  ${_run_dir}/NodeStateTest
}

stop_NSM() {
  systemctl stop node-startup-controller

  _p=`pidof NodeStateManager`
  if [ "x${_p}" != "x" ]; then
      kill -9 ${_p}
  fi
  _p=`pidof nsm-dummy`
  if [ "x${_p}" != "x" ]; then
      kill -9 ${_p}
  fi
}

_lib=/usr/lib/libNodeStateMachineStub.so
_lib_2=/usr/lib/libNodeStateMachineTest.so
_lib_t=${_run_dir}/lib/libNodeStateMachineTest.so
_lib_bak=${_run_dir}/lib/libNodeStateMachineStub.so
run_1() {
  if [ ! -f ${_lib_bak} ]; then
    cp ${_lib} ${_run_dir}/lib
  fi
  cp ${_lib_t} ${_lib}
  ldconfig
  sleep 1

  systemctl start dbus
  systemctl start dlt
  stop_NSM >& /dev/null
  dbus-launch >& /tmp/d.pid
  _dbus_pid=`grep BUS_PID /tmp/d.pid  | awk -F= '{print $2}'`
  sleep 1
  /usr/bin/NodeStateManager >& /dev/null &
  sleep 4

  if [ "x${1}" != "x" ]; then
    _run_1 > /tmp/t 2> /tmp/t.err
    _c1=`cat /tmp/t | grep success | wc -l`
    _c2=`cat /tmp/t | grep failed | wc -l`
    if [ "x${_c1}" = "x0" ]; then
       echo "[ FAILED ] ${_c2} tests."
    elif [ "x${_c2}" = "x0" ]; then
       echo "[ PASSED ] ${_c1} tests."
    else
       echo " ${_c1} PASSED, ${_c2} FAILED"
    fi
  else
    _run_1 2> /tmp/t.err
  fi

  kill -9 ${_dbus_pid} >& /dev/null
  stop_NSM >& /dev/null
  systemctl stop dlt

  rm -f ${_lib} ${_lib_2}
  mv ${_lib_bak} ${_lib}
  ldconfig
}
