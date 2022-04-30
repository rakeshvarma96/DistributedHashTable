import controller.DHTController;
import model.DelKeyRequest;
import model.GetKeyRequest;
import model.PutKeyValRequest;

public class DHTApplication {

    private DHTController dhtController;

    public DHTApplication() {
        dhtController = new DHTController();
    }

    public static void main(String[] args) {
        try {
            DHTApplication dhtApplication = new DHTApplication();
            System.out.println("Distributed hash table application is up and running.");
            dhtApplication.dhtController.put(new PutKeyValRequest("mike", "ross", 1));
            dhtApplication.dhtController.delete(new DelKeyRequest("mike", 1));
            System.out.println(dhtApplication.dhtController.get(new GetKeyRequest("mike",  1)));
            System.out.println(dhtApplication.dhtController.get(new GetKeyRequest("michael",  1)));
            dhtApplication.dhtController.put(new PutKeyValRequest("harvey", "specter", 450));
            System.out.println(dhtApplication.dhtController.get(new GetKeyRequest("harvey", 450)));
            System.out.println(dhtApplication.dhtController.get(new GetKeyRequest("closer", 450)));
        } catch (Exception exception) {
            System.out.println("Error while running distributed hash table app");
            exception.printStackTrace();
        }
    }
}
