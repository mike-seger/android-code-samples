package com.codexpedia.heycouchbaselite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    final String dbname = "hello";
    private String docId = "123";
    private Manager manager;
    private Database couchDb;

    private TextView tvLogWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLogWindow = (TextView) findViewById(R.id.tv_log_window);
        tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nStart Couchbase App!");
        if (!Manager.isValidDatabaseName(dbname)) {
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nBad couchbase db name!");
            return;
        }
        createManager();
        createCouchdb();
        createDocument(docId);

        Document retrievedDocument = retrieveDocument(docId);
        getMovieFromDocument(retrievedDocument);
        updateDocument(retrievedDocument);
        deleteDocument(retrievedDocument);

        tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nEnd the App!");
    }


    public void createManager() {
        try {
            manager = new Manager(new AndroidContext(getApplicationContext()), Manager.DEFAULT_OPTIONS);
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nCouchbase Manager created!");
        } catch (IOException e) {
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nFailed to create Couchbase Manager! " + e);
            return;
        }
    }

    public void createCouchdb() {
        try {
            couchDb = manager.getDatabase(dbname);
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nCouchbase Database created!");
        } catch (CouchbaseLiteException e) {
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nFailed to create Couchbase Database!");
            return;
        }
    }

    public void createDocument(String docId) {
        // create some dummy data
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar calendar = GregorianCalendar.getInstance();
        String currentTimeString = dateFormatter.format(calendar.getTime());
        Movie movie = new Movie("Star War", "The force awakens!", 120);

        // put those dummy data together
        Map<String, Object> docContent = new HashMap<String, Object>();
        docContent.put("message", "Hey Couchbase Lite");
        docContent.put("creationDate", currentTimeString);
        docContent.put("movie", movie);
        tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\ndocContent=" + String.valueOf(docContent));

        // create an empty document, add content and write it to the couchDb
        Document document = new Document(couchDb, docId);
        try {
            document.putProperties(docContent);
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nDocument written to couchDb named " + dbname + " with ID = " + document.getId());
        } catch (CouchbaseLiteException e) {
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nFailed to write document to Couchbase database!");
        }
    }

    public Document retrieveDocument(String docId) {
        Document retrievedDocument = couchDb.getDocument(docId);
        tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nretrievedDocument=" + String.valueOf(retrievedDocument.getProperties()));

        return retrievedDocument;
    }

    public void updateDocument(Document doc) {
        Map<String, Object> updatedProperties = new HashMap<String, Object>();
        updatedProperties.putAll(doc.getProperties());
        updatedProperties.put ("message", "We're having a heat wave!");
        updatedProperties.put ("temperature", "95");

        try {
            doc.putProperties(updatedProperties);
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nupdated retrievedDocument=" + String.valueOf(doc.getProperties()));
        } catch (CouchbaseLiteException e) {
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nFailed to update document! ");
        }
    }

    public void deleteDocument(Document doc) {
        try {
            doc.delete();
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nDeleted document, deletion status = " + doc.isDeleted());
        } catch (CouchbaseLiteException e) {
            tvLogWindow.setText(tvLogWindow.getText().toString() + "\n\nFailed to delete document! " + e);
        }
    }

    private void getMovieFromDocument(Document doc) {
        Object movieObj = doc.getProperties().get("movie");
        Gson gson = new Gson();
        String jsonString = gson.toJson(movieObj, Map.class);
        Movie movie = gson.fromJson(jsonString, Movie.class);
        Log.i("getMovieFromDocument", "jsonString>>>" + jsonString);
        Log.i("getMovieFromDocument", "movie>>>" + movie.getTitle());
    }
}
