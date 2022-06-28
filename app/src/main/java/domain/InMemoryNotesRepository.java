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
    // однопоточный исполнитель (Executor) если задач будет много - они будут выполняться последовательно
    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper()); //средство для доставки сообщений какому нибудь потоку

    public InMemoryNotesRepository() {
    }

    @Override
    public void getAll(Callback<List<Notes>> callback) {

        ArrayList<Notes> result = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() { //для выполнения в основном потоке

                    @Override
                    public void run() {
                        callback.onSuccess(result); // если все хорошо, список заметок (result) получен
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

    @Override
    public void removeNote(Notes notes, Callback<Void> callback) {

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

        result.remove(notes);

        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(null);
            }
        });
    }

    @Override
    public void upDateNote(Notes notes, String title, String details, Callback<Notes> callback) {

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

        Notes newNote = new Notes(notes.getId(), title, details, notes.getCreatedAt());

        int index = result.indexOf(notes);
        result.set(index, newNote);

        result.remove(notes);

        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(newNote);
            }
        });
    }
}

