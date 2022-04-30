package controller;

import model.DelKeyRequest;
import model.GetKeyRequest;
import model.PutKeyValRequest;
import service.DHTService;

import static constants.CommonConstants.*;
import static util.ValidationUtil.validateNonEmptyString;
import static util.ValidationUtil.validateNonNull;

public class DHTController {

    private DHTService dhtService;

    public DHTController() {
        dhtService = new DHTService();
    }

    public void put(PutKeyValRequest putKeyValRequest) throws Exception {
        validateNonNull(putKeyValRequest, PUT_KEY_VAL_REQUEST);
        validateNonNull(putKeyValRequest.getKey(), KEY);
        validateNonNull(putKeyValRequest.getValue(), VALUE);
        validateNonNull(putKeyValRequest.getClientId(), CLIENT_ID);
        validateNonEmptyString(putKeyValRequest.getKey(), KEY);
        validateNonEmptyString(putKeyValRequest.getValue(), VALUE);
        dhtService.put(putKeyValRequest.getClientId(),
                putKeyValRequest.getKey(),
                putKeyValRequest.getValue());
    }

    public String get(GetKeyRequest getKeyRequest) throws Exception {
        validateNonNull(getKeyRequest, GET_KEY_REQUEST);
        validateNonNull(getKeyRequest.getKey(), KEY);
        validateNonNull(getKeyRequest.getKey(), CLIENT_ID);
        validateNonEmptyString(getKeyRequest.getKey(), KEY);
        return dhtService.get(getKeyRequest.getClientId(), getKeyRequest.getKey());
    }

    public void delete(DelKeyRequest delKeyRequest) throws Exception {
        validateNonNull(delKeyRequest, DEL_KEY_REQUEST);
        validateNonNull(delKeyRequest.getKey(), KEY);
        validateNonNull(delKeyRequest.getKey(), CLIENT_ID);
        validateNonEmptyString(delKeyRequest.getKey(), KEY);
        dhtService.del(delKeyRequest.getClientId(), delKeyRequest.getKey());
    }
}
