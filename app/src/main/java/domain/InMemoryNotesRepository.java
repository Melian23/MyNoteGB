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

        result.add(new Notes("Заметка 1", "21.01.2022", "Первая заметка"));
        result.add(new Notes("Заметка 2", "21.02.2022", "Вторая заметка"));
        result.add(new Notes("Заметка 3", "28.03.2022", "Третья заметка"));
        result.add(new Notes("Заметка 4", "11.05.2022", "Четвертая заметка"));
        result.add(new Notes("Заметка 5", "21.01.2022", "Пятая заметка"));
        result.add(new Notes("Заметка 6", "04.09.2022", "Шестая заметка"));

        return result;
    }

    @Override
    public void add(Notes note) {

    }
}
