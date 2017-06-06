#!/bin/bash

VM_name=$1

source admin-openrc.sh
openstack server unpause $VM_name