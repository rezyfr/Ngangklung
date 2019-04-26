package com.coinbkt.ngangklung;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.coinbkt.ngangklung.adapter.NadaAdapter;
import com.coinbkt.ngangklung.model.Nada;
import com.github.tbouron.shakedetector.library.ShakeDetector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NadaActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.gridView)
    GridView gridView;

    //Use MediaPlayer API and create an global variable for it
    private MediaPlayer mediaPlayer;
    //handles audio focus when playing a sound file
    private AudioManager audioManager;

    int notePos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nada);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final NadaAdapter nadaAdapter = new NadaAdapter(this, nadas);

        gridView.setAdapter(nadaAdapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            notePos = position;

            Nada nada = nadas[position];
            nada.toggleSelected();
            for(int i = 0; i<nadas.length; i++){
                Nada nadab = nadas[i];
                if(i!=position) {
                    if(nadab.getIsSelected())
                        nadab.toggleSelected();
                }
                else{
                    if(!nadab.getIsSelected())
                        notePos = -1;
                }
            }
            nadaAdapter.notifyDataSetChanged();
        });

        playNote();
    }

    private void playNote (){
        ShakeDetector.create(this, () -> {
            switch(notePos){
                case -1:
                    releaseMediaPlayer();
                    Toast.makeText(getApplicationContext(), "Sila Pilih Salah Satu Tangga Nada", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    vibrate();
                    releaseMediaPlayer();
                    startDialog(R.raw.lowdo);
                    break;
                case 1:
                    vibrate();
                    releaseMediaPlayer();
                    startDialog(R.raw.re);
                    break;
                case 2:
                    vibrate();
                    releaseMediaPlayer();
                    startDialog(R.raw.mi);
                    break;
                case 3:
                    vibrate();
                    releaseMediaPlayer();
                    startDialog(R.raw.fa);
                    break;
                case 4:
                    vibrate();
                    releaseMediaPlayer();
                    startDialog(R.raw.sol);
                    break;
                case 5:
                    vibrate();
                    releaseMediaPlayer();
                    startDialog(R.raw.la);
                    break;
                case 6:
                    vibrate();
                    releaseMediaPlayer();
                    startDialog(R.raw.si);
                    break;
                case 7:
                    vibrate();
                    releaseMediaPlayer();
                    startDialog(R.raw.highdo);
                    break;
            }
        });
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
            mediaPlayer = MediaPlayer.create(NadaActivity.this, id);

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

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private void vibrate(){
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 600 milliseconds
        v.vibrate(600);
    }
    private Nada[] nadas = {
            new Nada(R.drawable.card_do_unselected),
            new Nada(R.drawable.card_re_unselected),
            new Nada(R.drawable.card_mi_unselected),
            new Nada(R.drawable.card_fa_unselected),
            new Nada(R.drawable.card_sol_unselected),
            new Nada(R.drawable.card_la_unselected),
            new Nada(R.drawable.card_si_unselected),
            new Nada(R.drawable.card_doo_unselected)
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShakeDetector.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShakeDetector.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShakeDetector.destroy();
    }

}
