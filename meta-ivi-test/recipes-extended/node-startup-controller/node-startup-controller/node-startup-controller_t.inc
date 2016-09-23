#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "Node Startup Controller"
}

_run_dir=/opt/tests/node-startup-controller
_luc_check() {
    while true; do
      ${_run_dir}/luc_check.sh > /tmp/t
      is_nsc=`grep LUC is not required`
      if [ "x${is_nsc}" != "x" ]; then
	  echo "restart node-startup-controller"
	  systemctl stop node-startup-controller
	  sleep 1
	  systemctl start node-startup-controller
      else
	  break
      fi
    done
}
_run_() {
  systemctl start dlt-receive
  systemctl start nsm-dummy
  systemctl start node-startup-controller

  _luc_check
  ${_run_dir}/${1}

  systemctl stop node-startup-controller
  systemctl stop nsm-dummy
  #systemctl stop dlt-receive
}

_check_luc_test_01() {
    cat /tmp/t | grep 'NSC-' | grep 'CTRL' > /tmp/t2
    _c1=`cat /tmp/t2 | grep "Updated LUC to:" | grep app1.unit`
    _c2=`cat /tmp/t2 | grep "\[Starting LUC group: 0\]"`
    _c3=`cat /tmp/t2 | grep "\[Starting LUC app: app1.unit\]"`
    _c4=`cat /tmp/t2 | grep "\[Finished starting LUC app: app1.unit\]"`
    _c5=`cat /tmp/t2 | grep "\[Finished starting LUC group: 0\]"`
    if [ "x${_c1}" != "x" -a "x${_c2}" != "x" -a "x${_c3}" != "x" -a \
	"x${_c4}" != "x" -a "x${_c5}" != "x" ]; then
	echo "[ PASSED ]"
    else
	echo "[ FAILED ]"
    fi
}

_run() {
  if [ "x${2}" != "x" ]; then
    _run_ ${1}.sh > /tmp/t 2> /tmp/t.err
    eval _check_${1}
  else
    _run_ ${1}.sh 2> /tmp/t.err
  fi
}

run_1() {
    _run luc_test_01 $*
    
}

run_2() {
    _run nhm-systemd-test $*
}
