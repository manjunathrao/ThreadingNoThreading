package android.dreamtechprojects.threadingnothreading;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Sony on 18-08-2015.
 */
public class Threading extends Activity {

    Button threadingToastButton, threadingImageViewButton;
    ImageView threadingImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_for_threading);

        threadingImageView = (ImageView) findViewById(R.id.threadingImageView);
        threadingImageViewButton = (Button) findViewById(R.id.threadingImageViewButton);
        threadingToastButton = (Button) findViewById(R.id.threadingToastButton);

        // Start the Async Task to download the image, when this button is pressed.
        threadingImageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadImage().execute("http://vignette4.wikia.nocookie.net/someordinarygamers/images/6/6a/Troll_face-1280x800.jpg/revision/latest?cb=20140401052630");
            }
        });


        threadingToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Toast received", Toast.LENGTH_SHORT).show();
            }
        });


    }


    // Set a drawable to the image view
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setImage(Drawable drawable) {
        threadingImageView.setBackground(drawable);
    }


    // Inner class to download the image
    public class DownloadImage extends AsyncTask<String, Integer, Drawable> {

        @Override
        protected Drawable doInBackground(String... strings) {
            return downloadImage(strings[0]);
        }


        @Override
        protected void onPostExecute(Drawable drawable) {
            setImage(drawable);
        }

        // Logic to download the image
        private Drawable downloadImage(String _url) {

            // Prepare to download the imate
            URL url;
            InputStream inputStream;
            BufferedInputStream bufferedInputStream;

            try {
                url = new URL(_url);
                inputStream = url.openStream();

                // Read the input stream
                bufferedInputStream = new BufferedInputStream(inputStream);

                // Convert the input stream into a bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

                //Close the streams
                if (inputStream != null) {
                    inputStream.close();
                }

                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }

                return new BitmapDrawable(bitmap);
            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;

        }
    }
}
