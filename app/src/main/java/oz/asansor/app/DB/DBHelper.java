package oz.asansor.app.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import oz.asansor.app.Model.Asansor;

public class DBHelper extends SQLiteOpenHelper {

    private String TAG = "SQLiteOpenHelper";

    private static final String DATABASE_NAME = "asansor";
    // Contacts table name
    private static final String TABLE_ASANSOR = "asansorTablo";

    String asansorSQL = "CREATE TABLE "
            + TABLE_ASANSOR + "(id INTEGER PRIMARY KEY, "
            + "kat TEXT, "
            + "evSahibi TEXT, "
            + "documentID TEXT" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d("DBHelper", "Asansor SQL : " + asansorSQL);
        database.execSQL(asansorSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_ASANSOR);
        onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onUpgrade(database, oldVersion, newVersion);
    }

    public void insertAsansor(Asansor asansor, String documentID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("kat", asansor.getKat());
        values.put("evSahibi", asansor.getEvSahibi());
        values.put("documentID", documentID);

        db.insert(TABLE_ASANSOR, null, values);
    }

    public void createTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }

    public void deleteAsansor() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASANSOR);
    }

    public ArrayList<Asansor> getAllAsansors() {
        ArrayList<Asansor> asansors = new ArrayList<Asansor>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_ASANSOR,
                new String[]{"id", "kat", "evSahibi", "documentID"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Asansor asansor= new Asansor();
            asansor.setKat(cursor.getString(1));
            asansor.setEvSahibi(cursor.getString(2));
            asansor.setDocumentID(cursor.getString(3));

            asansors.add(asansor);
        }
        db.close();
        return asansors;
    }
}