package di;

import android.content.Context;

import domain.InMemoryNotesRepository;
import domain.NotesRepository;
import domain.SharedPrefNotesRepository;

public class Dependencies {

    public static NotesRepository notesRepository;

    public static NotesRepository getNotesRepository (Context context){
        if (notesRepository == null){
            notesRepository = new SharedPrefNotesRepository(context);
        }
        return notesRepository;
    }
}
