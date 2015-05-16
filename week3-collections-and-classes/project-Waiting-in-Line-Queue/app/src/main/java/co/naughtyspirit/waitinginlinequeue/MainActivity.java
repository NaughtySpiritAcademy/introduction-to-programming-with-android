package co.naughtyspirit.waitinginlinequeue;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;
import java.util.Queue;

import co.naughtyspirit.waitinginlinequeue.entities.Cashier;
import co.naughtyspirit.waitinginlinequeue.entities.Desk;
import co.naughtyspirit.waitinginlinequeue.entities.Person;
import co.naughtyspirit.waitinginlinequeue.ui.CanvasView;


public class MainActivity extends Activity implements View.OnClickListener {

    private CanvasView canvasView;
    private ClientDispatcher clientDispatcher;
    private Queue<Person> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvasView = (CanvasView) findViewById(R.id.canvas_view);
        findViewById(R.id.next_button).setOnClickListener(this);

        clientDispatcher = new ClientDispatcher();
        clients = new LinkedList<>();

        Queue<Integer> clientNumbers = clientDispatcher.createClients();
        for (Integer clientNumber : clientNumbers) {
            Person client = new Person(clientNumber, Constants.MAX_PERSON_IN_QUEUE - clientNumber + 1);
            clients.add(client);
        }
        drawScene();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                Person nextClient = new Person(clientDispatcher.getNextClient(), Constants.CLIENT_DESK_POSITION);
                nextClient.goToDesk();
                clients.poll();
                for (Person client : clients) {
                    client.moveUp();
                }
                drawScene(nextClient);
                if (clients.isEmpty()) {
                    showDialog(R.string.complete_dialog_title, R.string.complete_dialog_message);
                }
                break;
        }
    }

    private void showDialog(int title, int message) {
        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setCancelable(false).show();
    }

    private void drawScene() {
        drawScene(null);
    }

    private void drawScene(Person currentClient) {
        canvasView.clearDrawables();
        canvasView.add(new Cashier());
        canvasView.add(new Desk());
        if (currentClient != null) {
            canvasView.add(currentClient);
        }

        for (Person client : clients) {
            canvasView.add(client);
        }
        canvasView.invalidate();
    }
}
