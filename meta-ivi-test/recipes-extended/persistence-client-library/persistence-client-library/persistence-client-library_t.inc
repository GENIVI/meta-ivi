#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "persisitence-client-library"
}

run_1() {
  systemctl restart dlt-dbus
  systemctl restart dlt-example-user
  systemctl restart pas-daemon
  systemctl restart node-startup-controller
  persadmin_tool install /Data/Data.tar.gz 
  /opt/tests/persistence-client-library/persistence_client_library_test
}
