#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "persistence-administrator"
}

run_1() {
  echo "error!"
  echo "segfault at 7fa2ac0b6010 ip 000000364d88e130 sp 00007fa2ae0f2aa8 error 4 in libc-2.23.so[364d800000+197000]"
  exit -1

  if [ ! -d /tmp/PAS ]; then
    rm -f /tmp/PAS
    mkdir -p /tmp/PAS
  fi
  cp /opt/tests/persistence-administrator/resource*.tar.gz /tmp/PAS/

  systemctl restart dlt-dbus
  systemctl restart dlt-example-user
  systemctl restart pas-daemon
  systemctl restart node-startup-controller
  persadmin_tool install /Data/Data.tar.gz 
  /opt/tests/persistence-administrator/pers_admin_test_framework
}
