#!/bin/sh

rm /tmp/session_amgr
dbus-daemon --session --print-address --fork > /tmp/session_amgr
export DBUS_SESSION_BUS_ADDRESS=`cat /tmp/session_amgr`
AudioManager -c/usr/lib/audioManager/control/libPluginControlInterface.so -l/usr/lib/audioManager/command/ -r/usr/lib/audioManager/routing/

