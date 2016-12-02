package com.vnptmedia.opencv20161128;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;

/**
 * Created by vxtuyen on 12/2/2016.
 */

public class MediacodecActivity extends AppCompatActivity {

    private final String TAG = "MediacodecActivity";
    private TextureView mPlaybackView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlaybackView = (TextureView)findViewById(R.id.PlaybackView);


    }


    public void startPlayback() {

        // Construct a URI that points to the video resource that we want to play
        Uri videoUri = Uri.parse("android.resource://"
                + getPackageName() + "/"
                + R.raw.vid_bigbuckbunny);


    }
}
