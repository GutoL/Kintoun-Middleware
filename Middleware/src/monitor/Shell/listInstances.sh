#!/bin/bash

VM_name=$1

source admin-openrc.sh
#openstack server list
echo "+---------------------+------+---------+------------+-------------+------------------+------------+
| ID                  | Name | Status  | Task State | Power State | Networks         | Image Name |"

echo "
| d5c854f9-d3e5-4f... | VM1  | ACTIVE  | -          | Running     | private=10.0.0.3 | cirros     |"

echo "| 42290b01-0968-43... | VM2  | SHUTOFF | -          | Shutdown    | private=10.0.0.4 | centos     |
+---------------------+------+---------+------------+-------------+------------------+------------+"
