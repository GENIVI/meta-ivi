#!/bin/sh
get_list() {
  return 2
}

get_title() {
  echo "persistence-common-object"
}

_run_dir=/opt/tests/persistence-common-object
_run_x() {
  if [ "x${2}" != "x" ]; then
    ${_run_dir}/$1 > /tmp/t
    _c1=`cat /tmp/t | grep :P: | wc -l`
    _c2=`cat /tmp/t | grep :F: | wc -l`
    if [ "x${_c1}" = "x0" ]; then
       echo "[ FAILED ] ${_c2} tests."
    elif [ "x${_c2}" = "x0" ]; then
       echo "[ PASSED ] ${_c1} tests."
    else
       echo " ${_c1} PASSED, ${_c2} FAILED"
    fi
  else
    ${_run_dir}/$1
  fi
}

run_1() {
  _run_x test_pco_key_value_store $*
}

run_2() {
  _run_x persistence_common_object_test $*
}
