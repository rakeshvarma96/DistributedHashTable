package service;

import java.util.*;

import static constants.CommonConstants.*;

public class LoadBalancerService {

    private Map<Integer, Integer> ringIdxToNode;
    private Random random;
    private int virtualPlaceCount;
    private int nodeCount;
    private int ringLen;

    public LoadBalancerService() {
        this.init();
    }

    public void init() {
        this.virtualPlaceCount = Integer.parseInt(System.getProperty(VIRTUAL_PLACE_COUNT));
        this.nodeCount = Integer.parseInt(System.getProperty(NODE_COUNT));
        this.ringLen = Integer.parseInt(System.getProperty(RING_LEN));
        this.ringIdxToNode = new TreeMap<>();
        this.random = new Random();
        this.placeNodesOnTheRing();
    }

    private void placeNodesOnTheRing() {
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < virtualPlaceCount; j++) {
                int ringIdx = getRandom();
                while (ringIdxToNode.containsKey(ringIdx))
                    ringIdx = getRandom();
                ringIdxToNode.put(ringIdx, i);
            }
        }
    }

    private int getRandom() {
        return random.nextInt(this.ringLen);
    }

    public int getNode(int reqId) {
        List<Integer> ringIds = new ArrayList<>(ringIdxToNode.keySet());
        reqId = reqId % ringLen;
        return getNearestNode(ringIds, reqId);
    }

    private int getNearestNode(List<Integer> ringIds, int reqId) {
        int l, r, n;
        n = ringIds.size();
        l = 0;
        r = n;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (ringIds.get(m) < reqId)
                l = m + 1;
            else
                r = m;
        }
        return l == n ? 0 : l;
    }


}
