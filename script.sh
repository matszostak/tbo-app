#!/bin/bash

# Check if exactly 4 arguments are provided
if [ "$#" -ne 4 ]; then
  echo "Please provide exactly 4 arguments."
  exit 1
fi

# Print the arguments in order
echo "Argument 1: $1"
echo "Argument 2: $2"
echo "Argument 3: $3"
eval "$4"
# echo "Argument 4: $4"
# eval "$4"
