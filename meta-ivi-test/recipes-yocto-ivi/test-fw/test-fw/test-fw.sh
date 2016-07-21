#!/bin/bash
# create dbus-session
dbus-launch >& /tmp/t
export `grep -r DBUS_SESSION_BUS_ADDRESS /tmp/t`

test_list=`ls /opt/tests/*/*_t.inc | awk -F/ '{print $4}' | awk -F_t '{print $1}'`

_test_run() {
  n=$1
  echo "##########"
  get_title
  echo "##########"

  if [ "x${n}" = "x" ]; then
    get_list
    cnt=$?
    echo "[ number of tests:" $cnt "]"
    for k in `seq $cnt`; do
      echo "[" run_${k} "]"
      eval run_${k}
    done
  else
      echo "[" run_${n} "]"
      eval run_${n}
  fi
}

if [ "x$1" = "xhelp" ]; then
   echo "$0"
   echo " - run all test"
   echo ""
   echo "$0 list"
   echo " - list all test program"
   echo ""
   echo "$0 <name>"
   echo " - run all sequence of test <name>"
   echo ""
   echo "$0 <name> <no>"
   echo " - run sequence <no> of test <name>"
   echo ""
   exit 0
fi

for i in $test_list; do
  j="/opt/tests/${i}/${i}_t.inc"
  . $j

  if [ "x$1" != "x" ]; then
    if [ "$1" = "list" ]; then
      get_list
      cnt=$?
      echo $i $cnt
    elif [ "${1}" = "${i}" ]; then
      _test_run $2
      break
    fi
    continue;
  fi
  _test_run
done