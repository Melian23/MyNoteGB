package di;

import domain.FaireStoreNotesRepository;
import domain.NotesRepository;

public class Dependencies {

    public static NotesRepository NOTES_REPOSITORY = new FaireStoreNotesRepository();

    public static NotesRepository getNotesRepository() {

        return NOTES_REPOSITORY;
    }
}
