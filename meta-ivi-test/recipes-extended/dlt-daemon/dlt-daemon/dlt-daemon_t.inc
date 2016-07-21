#!/bin/sh
get_list() {
  return 2
}

get_title() {
  echo "dlt-daemon"
}

run_1() {
  /opt/tests/dlt-daemon/dlt-test-filetransfer
}

run_2() {
  /opt/tests/dlt-daemon/dlt-test-user
}
