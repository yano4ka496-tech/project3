#!/bin/bash
if [[ "$*" == *"--tests"* ]]; then
    echo "Detected --tests, running full test suite..."
    exec ./gradlew_original test
else
    exec ./gradlew_original "$@"
fi
