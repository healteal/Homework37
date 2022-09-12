package Task2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class General {
    static int clientNumber = 1;

    public static void main(String[] args) {
        List<Client> clients = Collections.synchronizedList(new LinkedList<>());
        AtomicInteger tables = new AtomicInteger();
        ExecutorService servants = Executors.newFixedThreadPool(3);
        while (clientNumber <= 1000) {
            while (tables.get() < 5) {
                addClient(clients);
                tables.getAndIncrement();
            }
            for (int i = 0; i < 3; i++) {
                int servant = i;
                servants.execute(() -> {
                    if (!clients.isEmpty()) {
                        Thread.currentThread().setName("официант " + (servant + 1));
                        for (int j = 0; j < clients.size(); j++) {
                            if (!clients.get(j).isBlocked()) {
                                clients.get(j).setBlocked(true);
                                Client client = clients.get(j);
                                System.out.println(Thread.currentThread().getName()
                                        + " обслужил клиента "
                                        + client.getClientNumber()
                                        + " с заказом " + client.getMeal());
                                for (int k = 0; k < clients.size(); k++) {
                                    if (clients.get(k).getClientNumber() == client.getClientNumber()) {
                                        clients.remove(k);
                                        break;
                                    }
                                }
                                tables.getAndDecrement();
                                break;
                            }
                        }
                    }
                });
            }
        }
        servants.shutdown();
    }

    static List<Client> addClient(List<Client> clients) {
        int random = (int) (Math.random() * 10);
        if (random % 3 == 0) {
            clients.add(new Client(clientNumber, Meal.CHIPS, false));
            clientNumber++;
            return clients;
        } else if (random % 2 == 0) {
            clients.add(new Client(clientNumber, Meal.FISH, false));
            clientNumber++;
            return clients;
        } else clients.add(new Client(clientNumber, Meal.MEAT, false));
        clientNumber++;
        return clients;
    }
}
