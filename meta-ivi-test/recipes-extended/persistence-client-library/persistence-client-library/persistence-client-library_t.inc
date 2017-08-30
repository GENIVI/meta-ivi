#!/bin/sh
get_list() {
  return 3
}

get_title() {
  echo "persistence-client-library"
}

_run_dir=/opt/tests/persistence-client-library

start_NSM() {
  systemctl start dbus
  systemctl start dlt
  systemctl start dlt-dbus
  sleep 1
  /usr/bin/NodeStateManager >& /dev/null &
  sleep 4
}

stop_NSM() {
  _p=`pidof NodeStateManager`
  if [ "x${_p}" != "x" ]; then
    kill -9 ${_p}
  fi
  _p=`pidof nsm-dummy`
  if [ "x${_p}" != "x" ]; then
    systemctl stop nsm-dummy
    kill -9 ${_p}
  fi
  systemctl stop dlt-dbus
}

_setup_env() {
  stop_NSM
  if [ ! -d /Data/tmp ]; then
     rm -f /Data/tmp >& /dev/null
     mkdir /Data/tmp
  fi
  _m=`mount | grep /Data/mnt-c`
  if [ "x${_m}" == "x" ]; then
     mount -o bind /Data/tmp /Data/mnt-c
     mount -o bind /Data/tmp /Data/mnt-wt
  fi
  start_NSM
  systemctl restart pas-daemon
  sleep 2

  if [ ! -e /tmp/myBackup ]; then
     mkdir /tmp/myBackup
  fi
  if [ ! -e /Data/mnt-wt/pfs_test/ ]; then
     persadmin_tool install ${_rundir}/PAS_data.tar.gz > /tmp/t.2
  fi
}

_run() {
  _setup_env

  if [ "x${2}" != "x" ]; then
    ${_run_dir}/$1 > /tmp/t
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
    ${_run_dir}/$1
  fi
}

_run_2() {
  _setup_env

  if [ "x${2}" != "x" ]; then
    yes | ${_run_dir}/$1 > /tmp/t
    _c1=`cat /tmp/t | grep "Press enter to end" | wc -l`
    if [ "x${_c1}" = "x0" ]; then
       echo "[ FAILED ]"
    else
       echo "[ PASSED ]"
    fi
  else
    yes | ${_run_dir}/$1
  fi
}

run_1() {
  _run persistence_client_library_test $*
}

run_2() {
  _run persistence_client_library_test_file $*
}

run_3() {
  _run_2 persistence_client_library_dbus_test $*
}
