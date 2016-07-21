#!/bin/sh
get_list() {
  return 1
}

get_title() {
  echo "Common API C++ test code"
}

run_1() {
  /opt/tests/capicxx-perf/capicxx-server &
  /opt/tests/capicxx-perf/capicxx-client
  kill -9 `pidof capicxx-server`
  sleep 1
}
