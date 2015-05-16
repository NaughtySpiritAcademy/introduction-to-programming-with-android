package co.naughtyspirit.waitinginlinequeue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ClientDispatcher {
    Queue<Integer> clients;

    public Queue<Integer> createClients() {
        clients = new LinkedList<>();
        for (int i = 1; i <= 4; i++) {
            clients.add(i);
        }
        return clients;
    }

    public int getNextClient() {
        return clients.poll();
    }
}
