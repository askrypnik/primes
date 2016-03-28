### Open source project to calculate prime numbers

A maven-enabled project calculating prime numbers using multi threaded environment

@author: Alex Skrypnik

# Project structure:

0. README.md - this file
1. Main codebase:      /src/main
2. JUnit coverage:     /src/test/java/sigma/primes  
3. Execution samples 
  a/ iterative search:     /bin/startIterative.bat, sample result: iterative.zip
  b/ eratosthenes search:  /bin/startEratosthenes.bat, sample result: eratosthenes.zip
4. 3rd party libs: junit v. 4.12, log4j v. 1.2.12

both sample results have been produced using the following run:

-start=1 -end=10000000 -algo=Eratosthenes -concurrency=10

# Data invariants:
1. 1 <= start < end < TotalMemory (in bytes) / 4
2. 1 <= concurrency < NumberOfCPUCores * 2

Note: maven repo is sourced from my local workspace, so the .bat scripts would need to be reviewed

# Performance summary:

Searching for a list of prime numbers from the range between 1 and 10,000,000 using iterative method, i.e. by simply running 
a nested loop for each observable to check if there are positive divisors takes > 1,300 milliseconds.
Utilising Sieves of Eratosthenes method (wiki:https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) produces same outcome 
but takes slightly more than 400 milliseconds on the same sample set.
Both methods have been implemented and tested.
There is scope for CPU optimisation as I have not gone through inlining analysis and have not run any performance analysis,
focusing solely on the Java 8 compatible design.
Furthermore, memory consumption varies betwen the implementation methods as the Sieves of Eratosthenes approach requires O(n) 
memory, which can be optimised.

# Sample runs

1. Eratosthenes method: sigma.primes.Main -start=1 -end=10000000 -algo=Eratosthenes -concurrency=10

Execution result: 

2016-03-28 20:09:46 INFO  Main:53 - Started Prime Numbers resolver
2016-03-28 20:09:46 INFO  Main:27 - Starting Eratosthenes with Start = 1, End = 10000000, and concurrency level 10
2016-03-28 20:09:46 INFO  Main:49 - Took 422 millis. Found 664580 numbers

2. Iterative method:    sigma.primes.Main -start=1 -end=10000000 -algo=Iterative -concurrency=10

Execution result:

2016-03-28 20:09:56 INFO  Main:53 - Started Prime Numbers resolver
2016-03-28 20:09:56 INFO  Main:27 - Starting Iterative with Start = 1, End = 10000000, and concurrency level 10
2016-03-28 20:09:58 INFO  Main:49 - Took 1331 millis. Found 664580 numbers
