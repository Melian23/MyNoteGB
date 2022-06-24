package domain;

import java.util.List;

public class FaireStoreNotesRepository implements NotesRepository {
    @Override
    public void getAll(Callback<List<Notes>> callback) {

    }

    @Override
    public void addNote(String title, String details, Callback<Notes> callback) {

    }

    @Override
    public void removeNote(Notes notes, Callback<Void> callback) {

    }

    @Override
    public void upDateNote(Notes notes, String title, String details, Callback<Notes> callback) {

    }
}
