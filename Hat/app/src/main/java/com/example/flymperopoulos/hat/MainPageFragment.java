package com.example.flymperopoulos.hat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;

/**
 * Created by flymperopoulos on 10/18/2014.
 */

public class MainPageFragment extends Fragment {

    private Context context;
    public MainPageFragment() {}

    private MediaRecorder recorder;
    private MediaPlayer mediaPlayer;
    private String OUTPUT_FILE;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.mainmenu, container, false);

        final ImageView hat = (ImageView) rootView.findViewById(R.id.imageLabel);

        final ImageView newhat = (ImageView) rootView.findViewById(R.id.recordhat);
        newhat.setVisibility(View.INVISIBLE);

        OUTPUT_FILE = Environment.getExternalStorageDirectory() + "/audiorecorder.3gpp";

        hat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        StartRecording();
                        newhat.setVisibility(View.VISIBLE);
                        hat.setVisibility(View.INVISIBLE);
                        Log.d("record", "recording?");
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    StopRecording();
                    newhat.setVisibility(View.INVISIBLE);
                    hat.setVisibility(View.VISIBLE);
                    Log.d("stop", "stop recording");

                    return true;
                }
                return false;
            }
        });
        Button send = (Button)rootView.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyActivity) getActivity()).changeToContactsFragment();
            }
        });


        return rootView;
    }

    private void StartRecording() throws Exception{
        // Release the recorder object if it is already in use we will release it
        releaseMediaRecorder();
        File outFile = new File(OUTPUT_FILE);

        if (outFile.exists())
            outFile.delete();

        recorder = new MediaRecorder();

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(OUTPUT_FILE);
        recorder.prepare();
        recorder.start();
    }

    private void StopRecording(){
        if (recorder != null)
            recorder.stop();
    }

    private void releaseMediaRecorder() {
        // We have a recorder object, and we want to release it
        if (recorder != null){
            recorder.release();
            try {
                mediaPlayer.release();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}