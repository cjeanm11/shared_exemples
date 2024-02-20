

```java

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

interface Observer {
    void update(String message);
}

class RejeuObserver implements Observer {
    @Override
    public void update(String message) {
        ...
    }
}

class RejeuSubject {
    private List<Observer> observers = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void addObserver(Observer observer) {
      ...
    }

    public void removeObserver(Observer observer) {
      ...
    }

    public void updateObservers(String message) {
        List<CompletableFuture<Observers>> futuresToRemove = observers.stream()
                .filter(Observer observer -> 
	                condition1(observer)  &&
	                condition2(observer) &&
	                ...
                )
                .map(Observer observer -> { 
	                return CompletableFuture.runAsync(() ->   observer.update(message), executorService)
	            }
	        ).collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            obervers.removeAll(allOf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Shut down after scheduling task is completed
    public void shutdown() {
        executorService.shutdown();
    }
    
}

