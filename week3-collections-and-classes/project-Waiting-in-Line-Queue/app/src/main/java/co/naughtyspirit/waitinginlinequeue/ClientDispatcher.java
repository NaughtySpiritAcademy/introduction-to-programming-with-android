package co.naughtyspirit.waitinginlinequeue;

import java.util.ArrayDeque;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ClientDispatcher {
    ArrayDeque<String> clients;

    public ArrayDeque<String> createClients() {
        clients = new ArrayDeque<>();
        for(int i = 1; i <= 4; i++) {
            clients.add("Ivancho");
        }



        return clients;
    }

    public int getNextClient() {
        return clients.poll();
    }

    public boolean hasNoMoreClients() {
        return clients.isEmpty();
    }
}
