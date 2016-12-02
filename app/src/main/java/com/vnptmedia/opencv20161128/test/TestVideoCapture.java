package com.vnptmedia.opencv20161128.test;

import android.graphics.Camera;

import org.opencv.videoio.VideoCapture;

/**
 * Created by vxtuyen on 11/30/2016.
 */

public class TestVideoCapture {

    VideoCapture camera;
    public void test() {
        camera = new VideoCapture(0);
    }
}
