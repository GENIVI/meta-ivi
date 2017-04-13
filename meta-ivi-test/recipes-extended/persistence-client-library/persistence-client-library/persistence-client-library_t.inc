#!/bin/sh
get_list() {
  return 3
}

get_title() {
  echo "persistence-client-library"
}

_run_dir=/opt/tests/persistence-client-library

_setup_env() {
  _p=`pidof NodeStateManager`
  if [ "x${_p}" != "x" ]; then
      kill -9 ${_p} >& /dev/null
  fi
  _p=`pidof nsm-dummy`
  if [ "x${_p}" != "x" ]; then
      kill -9 ${_p} >& /dev/null
  fi
  sleep 2
  systemctl restart pas-daemon
  systemctl restart nodestatemanager-daemon

  if [ ! -e /tmp/myBackup ]; then
     mkdir /tmp/myBackup
  fi
  if [ ! -e /Data/mnt-wt/lt-persistence_client_library_test/ ]; then
     persadmin_tool install /Data/mnt-backup/PAS_data.tar.gz > /tmp/t
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
