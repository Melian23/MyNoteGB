package domain;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InMemoryNotesRepository implements NotesRepository {

    private ArrayList<Notes> result = new ArrayList<>();
    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    public InMemoryNotesRepository() {


    }

    @Override
    public void getAll(Callback<List<Notes>> callback) {

            ArrayList<Notes> result = new ArrayList<>();

            result.add(new Notes(UUID.randomUUID().toString(), "Заметка 1", "Описание заметки", new Date()));

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(result);
                    }
                });
            }
        });
    }

    @Override
    public void addNote(String title, String details, Callback<Notes> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Notes note = new Notes(UUID.randomUUID().toString(), title, details, new Date());

        result.add(note);

        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(note);
            }
        });
    }
}
