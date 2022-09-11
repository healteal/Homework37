package Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class General {
    static AtomicInteger jar = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        System.out.println("Введите количество пчёл: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int countOfBees = Integer.parseInt(bufferedReader.readLine());
        ExecutorService threads = Executors.newFixedThreadPool(countOfBees);
        while (true) {
            for (int i = 0; i < countOfBees; i++) {
                int beeNumber = i;
                threads.execute(() -> {
                    Thread.currentThread().setName(beeNumber + "ая пчела принесла последнюю порцию мёда");
                    jar.getAndAdd(1);
                    if (jar.get() == 100) {
                        System.out.println(Thread.currentThread().getName());
                        System.exit(1); // shutdown ?
                    }
                });
            }
        }
    }
}
