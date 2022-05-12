package domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InMemoryNotesRepository implements NotesRepository {
    public static NotesRepository INSTANCE;
    private Context context;

    public InMemoryNotesRepository(Context context) {
        this.context = context;
    }

    public static NotesRepository getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new InMemoryNotesRepository(context);
        }
        return INSTANCE;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public List<Notes> getAll() {
        ArrayList<Notes> result = new ArrayList<>();

        result.add(new Notes(UUID.randomUUID().toString(), "Заметка1", "Первая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 2", "Вторая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 3", "Третья заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 4", "Четвертая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 5", "Пятая заметка", new Date()));
        result.add(new Notes(UUID.randomUUID().toString(), "Заметка 6", "Шестая заметка", new Date()));

        return result;
    }

    @Override
    public void add(Notes note) {

    }
}
