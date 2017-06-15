roo#!/bin/sh
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
  systemctl start dlt-dbus
  _p=`pidof NodeStateManager`          
  if [ "x${_p}" != "x" ]; then       
    kill -9 ${_p} >& /dev/null       
  fi                                 
  systemctl stop nsm-dummy           
  systemctl stop node-startup-controller
  systemctl stop nsm-dummy              
  systemctl start node-startup-controller
  systemctl start nsm-dummy              
  if [ ! -d /tmp/PAS ]; then
    rm -f /tmp/PAS
    mkdir -p /tmp/PAS
  fi
  cp ${_run_dir}/resource*.tar.gz /tmp/PAS/

  systemctl start pas-daemon
  if [ "x${1}" != "x" ]; then
    _run_1 > /tmp/t
    _c1=`cat /tmp/t | grep OK | wc -l`
    _c2=`cat /tmp/t | grep FAILED | wc -l`
    if [ "x${_c1}" = "x0" ]; then
       echo "[ FAILED ] ${_c2} tests."
    elif [ "x${_c2}" = "x0" ]; then
       echo "[ OK ] ${_c1} tests."
    else
       echo " ${_c1} PASSED, ${_c2} FAILED"
    fi
  else
    _run_1
  fi
  systemctl stop pas-daemon                        
  systemctl stop nsm-dummy               
  systemctl stop node-startup-controller 
  systemctl stop nsm-dummy               
  systemctl stop dlt-dbus                
}
