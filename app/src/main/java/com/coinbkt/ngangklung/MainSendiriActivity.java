package com.coinbkt.ngangklung;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainSendiriActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivDO)
    ImageView ivDO;
    @BindView(R.id.ivRE)
    ImageView ivRE;
    @BindView(R.id.ivMI)
    ImageView ivMI;
    @BindView(R.id.ivFA)
    ImageView ivFA;
    @BindView(R.id.ivSOL)
    ImageView ivSOL;
    @BindView(R.id.ivLA)
    ImageView ivLA;
    @BindView(R.id.ivSI)
    ImageView ivSI;

    //Use MediaPlayer API and create an global variable for it
    private MediaPlayer mediaPlayer;
    //handles audio focus when playing a sound file
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sendiri);
        ButterKnife.bind(this);

        landscapeHint();

        ivBack.setOnClickListener(this);
        ivDO.setOnClickListener(this);
        ivRE.setOnClickListener(this);
        ivMI.setOnClickListener(this);
        ivFA.setOnClickListener(this);
        ivSOL.setOnClickListener(this);
        ivLA.setOnClickListener(this);
        ivSI.setOnClickListener(this);
    }

    public void landscapeHint(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.landscape_hint);
        dialog.setCanceledOnTouchOutside(true);
        //for dismissing anywhere you touch
        View masterView = dialog.findViewById(R.id.rlMasterView);
        masterView.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

    public void startDialog(int id) {
        // Create and setup the AudioManager to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        releaseMediaPlayer();

        int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                //Use the music stream
                AudioManager.STREAM_MUSIC,
                //Request permanent focus
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //Create and setup the MediaPlayer for the audio resource associated with the current word
            mediaPlayer = MediaPlayer.create(MainSendiriActivity.this, id);

            //Start the audio file
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currenly palying sound
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources because we no longer need it.
            mediaPlayer.stop();
            mediaPlayer.release();

            // Set media player back to null. For our code, we've decided that setting the media player to null is an easy way
            // to tell that the media player is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = (new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //Pause
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //Resume
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //Stop and release resource
                releaseMediaPlayer();
            }
        }
    });

    private MediaPlayer.OnCompletionListener completionListener = mediaPlayer -> releaseMediaPlayer();

    @Override
    public void onClick(View v) {
        ImageView iv = (ImageView) v;
        switch (iv.getId()){
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivDO:
                releaseMediaPlayer();
                startDialog(R.raw.lowdo);
                break;
            case R.id.ivRE:
                releaseMediaPlayer();
                startDialog(R.raw.re);
                break;
            case R.id.ivMI:
                releaseMediaPlayer();
                startDialog(R.raw.mi);
                break;
            case R.id.ivFA:
                releaseMediaPlayer();
                startDialog(R.raw.fa);
                break;
            case R.id.ivSOL:
                releaseMediaPlayer();
                startDialog(R.raw.sol);
                break;
            case R.id.ivLA:
                releaseMediaPlayer();
                startDialog(R.raw.la);
                break;
            case R.id.ivSI:
                releaseMediaPlayer();
                startDialog(R.raw.si);
                break;
        }
    }
}
