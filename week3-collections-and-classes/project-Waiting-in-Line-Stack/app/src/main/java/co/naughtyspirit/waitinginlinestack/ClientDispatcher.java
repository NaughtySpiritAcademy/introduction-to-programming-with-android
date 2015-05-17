package co.naughtyspirit.waitinginlinestack;

import java.util.Stack;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ClientDispatcher {
    Stack<Integer> clients;

    public Stack<Integer> createClients() {
        clients = new Stack<>();
        for (int i = 1; i <= 4; i++) {
            clients.add(i);
        }
        return clients;
    }

    public int getNextClient() {
        return clients.pop();
    }

    public boolean hasNoMoreClients() {
        return clients.empty();
    }
}
