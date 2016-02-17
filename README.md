# Build Status

[![Build Status](https://travis-ci.org/izmailoff/longest-palindrome.png?branch=master)](https://travis-ci.org/izmailoff/longest-palindrome)

# Test Coverage
[![Coverage Status](https://coveralls.io/repos/izmailoff/longest-palindrome/badge.svg?branch=master)](https://coveralls.io/r/izmailoff/longest-palindrome?branch=master)
	
# About
Contains an example of finding a longest palindrome in a sequence.

# Building

## Prerequisites
Install these applications on your dev machine in order to be able to build the src code:

 * Java Development Kit (JDK) >= 1.7
 * Optionally install SBT, or use one provided with the project (see *sbt* shell scripts)

## Run the project|tests from SBT
SBT is a build tool that downloads source code dependencies, compiles code, runs tests,
generates scaladocs, and produces executables.

Install SBT or get it with `./get_sbt.sh` on Unix.

Start up SBT from Unix/Windows shell:

    > sbt

or if it's not on a PATH:

    > ./sbt

In SBT shell type (note semicolons):

    ;clean; test

You can also run it as a single command from OS shell:

    > sbt clean test

This will run all tests.

    
