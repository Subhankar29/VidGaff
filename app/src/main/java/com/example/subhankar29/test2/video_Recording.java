package com.example.subhankar29.test2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class video_Recording extends AppCompatActivity {

   private static final int VIDEO_CAPTURE = 101;
    Uri videoUri;
    VideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__recording);
    }

    public void startRecordingVideo(View view) {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File mediaFile = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/myvideo.mp4");
            videoUri = Uri.fromFile(mediaFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            startActivityForResult(intent, VIDEO_CAPTURE);
        } else {
            Toast.makeText(this, "No camera on device", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video has been saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
                playbackRecordedVideo();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",  Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",  Toast.LENGTH_LONG).show();
            }
        }
    }

    public void playbackRecordedVideo() {

        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setVideoURI(videoUri);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.start();
    }

   public void convert(View view){

        Intent i = new Intent(getApplicationContext(),convertVid.class);
        File mediaFile = new File(
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/myvideo.mp4");
        videoUri = Uri.fromFile(mediaFile);
        i.putExtra("video1",videoUri);
        startActivityForResult(i,1);
    }

}
