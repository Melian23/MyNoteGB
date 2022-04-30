package domain;

import java.util.List;

public interface NotesRepository {

    List<Notes> getAll();

    void add(Notes note);
}
