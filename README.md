# DistributedHashTable

Distributed hash table provides 3 api's namely put(key, val), get(key), del(key).
This is implemented based on consistent hashing.

How to run the application
---------------------------
1. Pass the following parameters as VM options --> -DnodeCount=3 -DvirtualPlaceCount=3 -DringLen=360
2. Please modify the input request in the DHTApplication class to check different api's.

Little note on the VM options :
--------------------------------
1. nodeCount tells us how many servers we are going to use.
2. virtualPlaceCount tells us how many hash funtions we are going to use , so that the same server occupies mulitple places on the ring inorder to evenly distribute the load among all the servers
3. ringLen tells us what is the range on which we keep the servers. 


How consistent hashing is implemented:
--------------------------------------
1. Consistent Hashing helps us in sending the requests with some particular clientId to the same server every time and at the same time the load on the servers is distributed using the mutiple virtual hash functions.
2. First we keep the servers on the ring.
3. For each server we calculate the index on the ring using random function. We generate as many random indices as the number of virtualPlaceCount. This means that our server occupies all these indices. We store the ringIndex to node mapping in a TreeMap.
4. When we receive a request with some clientId
    a) we take clientId modulus ringLength in order to ensure that this value lies with in the range (0, ringLen).
    b) Take all the occupied ring indices from the ringIndex to node mapping TreeMap into an array and do binary search with the clientId to find the nearest number which is equal to or just greater.
    c) Perform the operations(put, get, del) on this node's dataStore(HashMap).
