package model;

public class GetKeyRequest {
    private String key;
    private int clientId;

    public GetKeyRequest(String key, int clientId) {
        this.key = key;
        this.clientId = clientId;
    }

    public String getKey() {
        return key;
    }

    public int getClientId() {
        return clientId;
    }
}
