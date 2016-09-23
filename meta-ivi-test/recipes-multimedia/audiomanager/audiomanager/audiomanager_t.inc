#!/bin/sh
get_list() {
  return 7
}

get_title() {
  echo "audiomanager"
}

_run_dir=/opt/tests/audiomanager
_run() {
  if [ "x${2}" != "x" ]; then
    ${_run_dir}/$1 > /tmp/t
    tail /tmp/t | grep -E 'PASS|FAIL'
  else
    ${_run_dir}/$1
  fi
}

run_1() {
  _run AmControlInterfaceTest $*
}

run_2() {
  _run AmMapHandlerTest $*
}

run_3() {
  _run AmRouterMapTest $*
}

run_4() {
  _run AmRouterTest $*
}

run_5() {
  _run AmRoutingInterfaceTest $*
}

run_6() {
  _run AmSocketHandlerTest $*
}

run_7() {
  _run AmTelnetServerTest $*
}
