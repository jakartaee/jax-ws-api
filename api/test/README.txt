Tests for JAX-WS API involves several different jdk/ServiceLoader configurations,
so the easiest way is using bash.

To run tests, use following scripts:

1) ./setup.sh
    builds jaxws-api and creates directory endorsed which is picked up by tests

2) ./runtests.sh
    runs all the scenarios declared in scenarios.sh
    it just writes results, to be sure nothing is failing, run:
     ./runtests.sh |grep FAILED
