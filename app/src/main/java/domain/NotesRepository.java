package domain;

import java.util.List;

public interface NotesRepository {

    void getAll(Callback<List<Notes>> callback);

    void addNote(String title, String details, Callback<Notes> callback);

    void removeNote (Notes notes, Callback<Void> callback);
}
