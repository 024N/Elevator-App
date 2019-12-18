package oz.asansor.app.Utils;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import oz.asansor.app.Model.Asansor;

public class Util {

    private String TAG = "Util";
    private FirebaseFirestore database;

    public void createAsansorData(Asansor asansor) {
        Map<String, String> asansors = new HashMap<>();
        asansors.put("evSahibi", asansor.getEvSahibi());
        asansors.put("kat", asansor.getKat());

        long now = System.currentTimeMillis();

        database = FirebaseFirestore.getInstance();
        database.collection("asansor").document(String.valueOf(now))
                .set(asansors)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

}