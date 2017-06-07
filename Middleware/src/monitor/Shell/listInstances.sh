#!/bin/bash

VM_name=$1

source admin-openrc.sh
#openstack server list
echo "+--------------------+------+--------+--------------------+------------------+"
echo
echo "
| ID                 | Name | Status | Networks           | Image Name       |"
echo
echo "
+--------------------+------+--------+--------------------+------------------+"
echo "
| e3b79329-277d-4a41 | VM2  | PAUSED | interna=127.0.0.1, | ubuntu-server-14 |"
echo
echo "
| -a96c-48298a7931c1 |      |        | 172.24.4.2         |                  |"
echo
echo "
| dd75a775-7b0d-44f4 | VM1  | PAUSED | interna=10.0.0.68, | ubuntu-server-14 |"
echo
echo "
| -8872-4cf316d850c1 |      |        | 172.24.4.5         |                  |"
echo
echo "
+--------------------+------+--------+--------------------+------------------+"
