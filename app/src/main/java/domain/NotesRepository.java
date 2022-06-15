package domain;

import android.telecom.Call;

import java.util.List;

public interface NotesRepository {

    void getAll(Callback<List<Notes>> callback);

    void addNote(String title, String details, Callback<Notes> callback);

    void removeNote (Notes notes, Callback<Void> callback);

    void upDateNote (Notes notes, String title, String details, Callback <Notes> callback);
}
