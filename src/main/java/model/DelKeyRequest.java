package model;

public class DelKeyRequest {
    private String key;
    private int clientId;

    public DelKeyRequest(String key, int clientId) {
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
