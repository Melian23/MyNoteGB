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
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка1", "Первая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 2", "Вторая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 3", "Третья заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 4", "Четвертая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 5", "Пятая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 6", "Шестая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 7", "Седьмая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 8", "Восьмая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 9", "Девятая заметка", new Date()));

    }

    @Override
    public void getAll(Callback<List<Notes>> callback) {

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
    public void add(Notes note) {

    }
}
