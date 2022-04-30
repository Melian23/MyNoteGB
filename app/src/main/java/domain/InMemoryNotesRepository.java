package domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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

        result.add(new Notes("Заметка 1", 45545, "Первая заметка"));
        result.add(new Notes("Заметка 2", 45545, "Вторая заметка"));
        result.add(new Notes("Заметка 3", 45545, "Третья заметка"));
        result.add(new Notes("Заметка 4", 45545, "Четвертая заметка"));
        result.add(new Notes("Заметка 5", 45545, "Пятая заметка"));
        result.add(new Notes("Заметка 6", 45545, "Шестая заметка"));

        return result;
    }

    @Override
    public void add(Notes note) {

    }
}
