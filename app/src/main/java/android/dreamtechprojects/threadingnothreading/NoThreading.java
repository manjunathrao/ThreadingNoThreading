package android.dreamtechprojects.threadingnothreading;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Sony on 18-08-2015.
 */
public class NoThreading extends Activity {

    Button imageButton, toastButton;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_for_threading);

        // Initialize the Button and ImageView
        imageButton = (Button) findViewById(R.id.threadingImageViewButton);
        toastButton = (Button) findViewById(R.id.threadingToastButton);
        imageView = (ImageView) findViewById(R.id.threadingImageView);


        // Button Event Handling
        // Toast the screen, acknowledging that the button was clicked
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Toast Received", Toast.LENGTH_SHORT).show();
            }
        });

        //Button Event Handling
        //Download the image from the URL and set it in the Image View
        imageButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {

                // Set up the download
                URL url;
                InputStream inputStream;
                BufferedInputStream bufferedInputStream;

                try {
                    url = new URL("http://vignette4.wikia.nocookie.net/someordinarygamers/images/6/6a/Troll_face-1280x800.jpg/revision/latest?cb=20140401052630");
                    inputStream = url.openStream();
                    bufferedInputStream = new BufferedInputStream(inputStream);

                    //Covert the Buffered Input Stream to a Bitmap Image
                    Bitmap bitmapImage = BitmapFactory.decodeStream(bufferedInputStream);

                    if (inputStream != null) {
                        inputStream.close();
                    } else if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }

                    imageView.setBackground(new BitmapDrawable(bitmapImage));
                } catch (java.io.IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(), "Oops, something went wrong!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
