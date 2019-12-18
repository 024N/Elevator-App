package oz.asansor.app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import oz.asansor.app.DB.DBHelper;
import oz.asansor.app.Fragment.AsansorFragment;
import oz.asansor.app.Model.Asansor;

import android.util.Log;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private String TAG = "MainActivity";
    private FirebaseFirestore database;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        startProgressDialog();
        if(isNetworkAvailable()) {
            database = FirebaseFirestore.getInstance();            // Access a Cloud Firestore instance from your Activity
            readAsansorFromCloud();
        }
        else{
            Toast.makeText(this, "Güncellenemiyor, lütfen internet bağlantınızı kontrol edin!", Toast.LENGTH_LONG).show();
            stopProgressDialog();
        }
    }

    private void readAsansorFromCloud(){
        database.collection("asansor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            dbHelper.deleteAsansor();
                            dbHelper.createTables();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                dbHelper.insertAsansor(document.toObject(Asansor.class), document.getId());
                            }
                            fragmentRouter("asansorFragment");
                            stopProgressDialog();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(getApplicationContext(),"Bir hata oluştu daha sonra tekrar deneyiniz.", Toast.LENGTH_LONG).show();
                            stopProgressDialog();
                        }
                    }
                });
    }

    private void fragmentRouter(String fragmentType){
        if(!isNetworkAvailable()) {
            Toast.makeText(this, "Güncellenemiyor, lütfen internet bağlantınızı kontrol edin!", Toast.LENGTH_LONG).show();
        }

        Fragment fragment = new AsansorFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); //fragmentleri temizler
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.content, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackPressed() {
        finish();
        startActivity(getIntent());
    }
}