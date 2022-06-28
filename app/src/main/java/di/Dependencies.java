package di;

import domain.InMemoryNotesRepository;
import domain.NotesRepository;

public class Dependencies {

    public static final NotesRepository NOTES_REPOSITORY = new InMemoryNotesRepository();
}
