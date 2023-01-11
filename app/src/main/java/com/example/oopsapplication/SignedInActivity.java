package com.example.oopsapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;

/*
import android.support.annotation.NonNull;
androidx.annotation.NonNull

import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
*/

public class SignedInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in2);

        FirebaseUser currentUser =
                FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser == null) {
            startActivity(new Intent(this, FirebaseAuthActivity.class));
            finish();
            return;
        }

        TextView email = (TextView) findViewById(R.id.email);
        TextView displayname = (TextView) findViewById(R.id.displayname);

        email.setText(currentUser.getEmail());
        displayname.setText(currentUser.getDisplayName());

        if (currentUser.getPhotoUrl() != null) {
            displayImage(currentUser.getPhotoUrl());
        }
    }

    /* public void changePage(View view)
     {Intent intent5 = new Intent(SignedInActivity.this,customization_page.class);
         startActivity(intent5);}*/
    public void change(View view)
    {

        Intent intent1 = new Intent(SignedInActivity.this,MainActivity.class);
        startActivity(intent1);
    }

    public void signOut(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(
                                    SignedInActivity.this,
                                    FirebaseAuthActivity.class));
                            finish();
                        } else {
                            // Report error to user
                        }
                    }
                });
    }

    public void deleteAccount(View view) {
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignedInActivity.this,
                                    FirebaseAuthActivity.class));
                            finish();
                        } else {
                            // Notify user of error
                        }
                    }
                });
    }

    void displayImage(Uri imageUrl) {

        // show The Image in a ImageView
        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute(imageUrl.toString());
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}