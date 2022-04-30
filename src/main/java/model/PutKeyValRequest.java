package model;

public class PutKeyValRequest {
    private String key;
    private String value;
    private int clientId;

    public PutKeyValRequest(String key, String value, int clientId) {
        this.key = key;
        this.value = value;
        this.clientId = clientId;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getClientId() {
        return clientId;
    }
}
