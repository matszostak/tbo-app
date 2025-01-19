#!/bin/bash

if [ "$#" -ne 5 ]; then
  echo "Please provide exactly 5 arguments."
  exit 1
fi

echo "$1"
echo "$2"
eval "$3"
eval "$4"
echo "$4"
