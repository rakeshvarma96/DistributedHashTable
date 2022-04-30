package service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static constants.CommonConstants.NODE_COUNT;
import static constants.CommonConstants.NOT_FOUND;

public class DHTService {

    private LoadBalancerService loadBalancerService;
    private Map<Integer, Map<String, String>> nodeToDataStore;
    private int nodeCount;

    public DHTService() {
        this.loadBalancerService = new LoadBalancerService();
        this.init();
    }

    private void init() {
        this.nodeCount = Integer.parseInt(System.getProperty(NODE_COUNT));
        ;
        this.nodeToDataStore = new HashMap<>();
        for (int i = 0; i < nodeCount; i++)
            nodeToDataStore.put(i, new ConcurrentHashMap<>());
    }

    public void put(int clientId, String key, String val) {
        System.out.println("Received put request with key: " + key + " , value: " + val + " and clientId: " + clientId);
        int nodeId = loadBalancerService.getNode(clientId);
        System.out.println("Put request with key: " + key + " , value: " + val + " and clientId: " + clientId + " is stored in node: " + nodeId);
        nodeToDataStore.get(nodeId).put(key, val);
    }

    public String get(int clientId, String key) {
        System.out.println("Received get request with key: " + key + " and clientId: " + clientId);
        int nodeId = loadBalancerService.getNode(clientId);
        System.out.println("Querying node: " + nodeId + " for get request with key: " + key + " and clientId: " + clientId);
        if (nodeToDataStore.get(nodeId).containsKey(key)) {
            System.out.println("Get request with key: " + key + " and clientId: " + clientId + " is retrieved from node: " + nodeId);
            return nodeToDataStore.get(nodeId).get(key);
        }
        return NOT_FOUND;
    }

    public void del(int clientId, String key) {
        System.out.println("Received del request with key: " + key + " and clientId: " + clientId);
        int nodeId = loadBalancerService.getNode(clientId);
        System.out.println("Querying node: " + nodeId + " for del request with key: " + key + " and clientId: " + clientId);
        if (nodeToDataStore.get(nodeId).containsKey(key)) {
            System.out.println("Del request with key: " + key + " and clientId: " + clientId + " is deleted from node: " + nodeId);
            nodeToDataStore.get(nodeId).remove(key);
        }
    }
}
