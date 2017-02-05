#!/bin/sh
get_list() {
  return 2
}

get_title() {
  echo "Node Health Monitor"
}

_run_dir=/opt/tests/node-health-monitor
_run_() {
  systemctl start nsm-dummy
  node-health-monitor &

  cd ${_run_dir}
  ./${1}

  kill -9 `pidof node-health-monitor`
  systemctl stop nsm-dummy
}

_run() {
  if [ "x${2}" != "x" ]; then
    _run_ $1 > /tmp/t 2> /tmp/t.err
    _c1=$?
    if [ "x${_c1}" = "x0" ]; then
       echo "[ PASSED ]"
    else
       echo "[ FAILED ]"
    fi
  else
    _run_ $1 2> /tmp/t.err
  fi
}

run_1() {
    _run nhm-main-test $*
}

run_2() {
    _run nhm-systemd-test $*
}
