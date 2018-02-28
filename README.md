# GateKeeper Challenge Service Prototype
[![Build Status](https://travis-ci.org/GateKeeperApp/challenge-service.svg?branch=master)](https://travis-ci.org/GateKeeperApp/challenge-service)

Directions for the challenge from local machine:

1) Make a GET call on http://localhost:8081/challenge

2) The response body will contain an id and a list of integers.

3) Make a POST call on http://localhost:8081/challenge/{id}
   - where the id is the id in the response body from step 1
   - the body needs to have the following structure:
        >> {
        >>   "value": {integer_sum}
        >> }
   
   - where the integer_sum is the sum of all the integers in the response body list from step 2

4) A successful solution will return a 201 and an incorrect solution will return a 422.
