#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

echo "Working directory: $(pwd)"

if [ ! -f "passwords.txt" ]; then
    echo "Error: passwords.txt not found in $(pwd)"
    exit 1
fi

mvn clean compile exec:java -Dexec.mainClass="org.example.Main"
