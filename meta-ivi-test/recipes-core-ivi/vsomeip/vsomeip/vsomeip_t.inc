#!/bin/sh
get_list() {
  return 6
}

get_title() {
  echo "vSOMEIP"
}

_run_dir=/opt/tests/vsomeip
_run() {
  cd ${_run_dir}
  if [ "x${2}" != "x" ]; then
    ./${1}_starter.sh >& /tmp/t
    tail -n 8 /tmp/t | grep -E 'PASS|FAIL'
  else
    ./${1}_starter.sh
  fi
}

run_1() {
  _run application_test $*
}

run_2() {
  _run big_payload_test_local $*
}

run_3() {
  _run external_local_payload_test_client_local $*
}

run_4() {
  _run header_factory_test_send_receive $*
}

run_5() {
  _run local_payload_test $*
}

run_6() {
  _run local_routing_test $*
}
