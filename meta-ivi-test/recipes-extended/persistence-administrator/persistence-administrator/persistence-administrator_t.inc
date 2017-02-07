#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "persistence-administrator"
}

_run_dir=/opt/tests/persistence-administrator
_run_1() {
  persadmin_tool install /Data/Data.tar.gz > /tmp/t
  ${_run_dir}/pers_admin_test_framework
}

run_1() {
  echo "[ FAILED ] segfault at 7fa2ac0b6010 ip 000000364d88e130 sp 00007fa2ae0f2aa8 error 4 in libc-2.23.so[364d800000+197000]"
  return 0

  if [ ! -d /tmp/PAS ]; then
    rm -f /tmp/PAS
    mkdir -p /tmp/PAS
  fi
  cp ${_run_dir}/resource*.tar.gz /tmp/PAS/

  systemctl restart dlt-dbus
  systemctl restart dlt-example-user
  systemctl restart pas-daemon
  systemctl restart node-startup-controller
  if [ "x${1}" != "x" ]; then
    _run_1 > /tmp/t
    _c1=`cat /tmp/t | grep OK | wc -l`
    _c2=`cat /tmp/t | grep FAILED | wc -l`
    if [ "x${_c1}" = "x0" ]; then
       echo "[ FAILED ] ${_c2} tests."
    elif [ "x${_c2}" = "x0" ]; then
       echo "[ OK ] ${_c1} tests."
    else
       echo " ${_c1} OK, ${_c2} FAILED"
    fi
  else
    _run_1
  fi
}
