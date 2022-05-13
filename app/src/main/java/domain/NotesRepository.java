package domain;

import android.telecom.Call;

import java.util.List;

public interface NotesRepository {

    void getAll(Callback<List<Notes>> callback);

    void add(Notes note);
}
