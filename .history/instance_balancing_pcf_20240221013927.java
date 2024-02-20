import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.CloudPlatform;
import org.springframework.cloud.Cloud;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class YourSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourSpringApplication.class, args);
    }

    private String extractMessageKey(String message) {
        return "num_ref_courtier";
    }

    // Perform consistent hashing based on the message key and instance count
    private int consistentHash(String messageKey, int instanceCount) {
        return Math.abs(messageKey.hashCode()) % instanceCount;
    }

    // Route the message to the selected instance based on consistent hashing
    private void routeMessageToInstance(String message, List<String> instances) {
        String messageKey = extractMessageKey(message);
        int instanceCount = instances.size();
        int selectedInstance = consistentHash(messageKey, instanceCount);
        String selectedInstanceUrl = instances.get(selectedInstance);

        // Your logic to send the message to the selected instance
        System.out.println("Routing message to instance: " + selectedInstanceUrl);
    }

    // Retrieve the list of instance URLs from the Cloud Foundry environment
    private List<String> getInstanceUrls() {
        Cloud cloud = new CloudFactory().getCloud();
        int instanceCount = cloud.getApplicationInstanceInfo().length;
        
        // Construct a list of instance URLs based on the application name and instance index
        return IntStream.range(0, instanceCount)
                .mapToObj(i -> cloud.getApplicationInstanceInfo()[i].getUris().get(0))
                .collect(Collectors.toList());
    }
}
