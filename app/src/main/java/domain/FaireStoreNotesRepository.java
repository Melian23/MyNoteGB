package domain;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FaireStoreNotesRepository implements NotesRepository {

    public static final String KEY_TITLE = "title";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_DATA = "data";
    public static final String NOTES = "notes";

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    public void getAll(Callback<List<Notes>> callback) {

        firestore.collection(NOTES)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<Notes> result = new ArrayList<>();

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            String id = documentSnapshot.getId();
                            String title = documentSnapshot.getString(KEY_TITLE);
                            String details = documentSnapshot.getString(KEY_DETAILS);
                            Date data = documentSnapshot.getDate(KEY_DATA);

                            result.add(new Notes(id, title, details, data));
                        }

                        callback.onSuccess(result);
                    }
                });
    }

    @Override
    public void addNote(String title, String details, Callback<Notes> callback) {

        HashMap<String, Object> resultAdd = new HashMap<>();

        Date createdAt = new Date();

        resultAdd.put(KEY_TITLE, title);
        resultAdd.put(KEY_DETAILS, details);
        resultAdd.put(KEY_DATA, createdAt);

        firestore.collection(NOTES)
                .add(resultAdd)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        callback.onSuccess(new Notes(documentReference.getId(), title, details, createdAt));
                    }
                });
    }

    @Override
    public void removeNote(Notes notes, Callback<Void> callback) {

        firestore.collection(NOTES)
                .document(notes.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(unused);
                    }
                });
    }

    @Override
    public void upDateNote(Notes notes, String title, String details, Callback<Notes> callback) {

        HashMap<String, Object> resultUpdate = new HashMap<>();

        resultUpdate.put(KEY_TITLE, title);
        resultUpdate.put(KEY_DETAILS, details);

        firestore.collection(NOTES)
                .document(notes.getId())
                .update(resultUpdate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Notes result = new Notes(notes.getId(), title, details, notes.getCreatedAt());
                        callback.onSuccess(result);
                    }
                });
    }
}
