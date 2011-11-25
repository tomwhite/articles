#!/usr/bin/env bash

# This script is run on an EC2 instance to start the proxy.

java -jar /root/s3proxy-0.1/s3proxy-0.1.jar `wget -q -O - http://169.254.169.254/latest/user-data` \
  > /mnt/s3proxy.log 2>&1 &