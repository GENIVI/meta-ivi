#!/bin/sh
#
# Copyright (c) 2012 Wind River Systems, Inc.
#
# The right to copy, distribute, modify, or otherwise make use
# of this software may be licensed only pursuant to the terms
# of an applicable Wind River license agreement.
#

RUNDIR="/var/run/sshd"
KEYPATH="/etc/ssh"

if [ ! -d "$RUNDIR" ]; then
    mkdir -p /var/run/sshd
fi

if [ ! -f "$KEYPATH/ssh_host_dsa_key" ]; then
	ssh-keygen -t dsa -N "" -f /etc/ssh/ssh_host_dsa_key -q
fi

if [ ! -f "$KEYPATH/ssh_host_rsa_key" ]; then
	ssh-keygen -t rsa -N "" -f /etc/ssh/ssh_host_rsa_key -q
fi

if [ ! -f "$KEYPATH/ssh_host_rsa1_key" ]; then
	ssh-keygen -t rsa1 -N "" -f /etc/ssh/ssh_host_rsa1_key -q
fi

if [ ! -f "$KEYPATH/ssh_host_ecdsa_key" ]; then
	ssh-keygen -t ecdsa -N "" -f /etc/ssh/ssh_host_ecdsa_key -q
fi
