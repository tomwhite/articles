#!/bin/bash

cd build
find javadoc -type f | python ../update_s3.py

cd ../dist
find . -name s3map*.zip | python ../update_s3.py