package com.vnptmedia.opencv20161128;

import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import com.vnptmedia.opencv20161128.Camera.MyCamera;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextureView textureView;
    private Button bttakePicture;
    private MyCamera myCamera;
    private int indexCamera;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        if(OpenCVLoader.initDebug()) {
            Log.d(TAG, "OpenCV loader");
        } else {
            Log.d(TAG, "OpenCV can't load");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indexCamera = 0;
        textureView = (TextureView)findViewById(R.id.texture);
        bttakePicture = (Button)findViewById(R.id.btn_takepicture);
        myCamera = new MyCamera(this, textureView);
        Log.d(TAG, "Set SurfaceTextureListener");
        textureView.setSurfaceTextureListener(surfaceTextureListener);
        bttakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCamera.takePicture();
            }
        });

    }

    TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
            Log.d(TAG, "Opencamera from Surfacetexture");
            myCamera.openCamera(indexCamera);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            myCamera.closeCamera();
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        myCamera.startBackgroundThread();
//        if (textureView.isAvailable()) {
//            openCamera();
//        } else {
//            textureView.setSurfaceTextureListener(textureListener);
//        }
    }
    @Override
    protected void onPause() {
        Log.e(TAG, "onPause");
        //closeCamera();
        myCamera.stopBackgroundThread();
        super.onPause();
    }

    //--------------All native method-----------------------------------------------------------------------------------------
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
