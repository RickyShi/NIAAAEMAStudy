package edu.missouri.niaaa.ema;

//import java.util.HashMap;
//import java.util.Timer;
//import java.util.TimerTask;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
//import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
//import android.os.PowerManager.WakeLock;
//import android.view.Menu;
//import android.view.MenuItem;

public class ChargeReminder extends Activity {
	/*SoundPool soundp;
	private HashMap<Integer, Integer> soundsMap;
	int soundDelay = 5000;
	Timer t;
	int streamID;
	WakeLock wl;*/
	
	MediaPlayer myPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge_reminder);
		
		/*soundp = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
		soundsMap = new HashMap<Integer, Integer>();
		soundsMap.put(1, soundp.load(this, R.raw.new_alarm_sound, 1));
		t=new Timer();
		

		acquireLock();

		prepareSound();
		
		acquireLock();

		playSound();*/
		
		
		myPlayer = new MediaPlayer();
		myPlayer = MediaPlayer.create(this, R.raw.new_alarm_sound);
		myPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		myPlayer.setLooping(true);
		
		
		final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(5000);
        
        myPlayer.start();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle(R.string.charge_reminder_alert_title);
		builder.setMessage(R.string.charge_reminder_alert_message);
		builder.setCancelable(false);
		builder.setNeutralButton(R.string.charge_reminder_alert_button, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				myPlayer.stop();
				v.cancel();
				dialog.cancel();
				ChargeReminder.this.finish();
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
		
		
	}
/*	private void playSound(){
		this.setVolumeControlStream(AudioManager.STREAM_ALARM);
		AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		am.setStreamVolume(AudioManager.STREAM_MUSIC, Utilities.RINGVOLUME, AudioManager.FLAG_PLAY_SOUND);

		t.schedule(new StartSound(),soundDelay);

		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(5000);
	}
	


    private class StartSound extends TimerTask {
    	@Override
    	public void run(){

    		streamID = soundp.play(soundsMap.get(1), 1, 1, 1, 0, 1); // ema should be different
    	}
    }

	private void prepareSound(){
		soundp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

			@Override
			public void onLoadComplete(SoundPool arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				t.schedule(new StartSound(),soundDelay);
			}
		});
	}
	private void acquireLock() {
		// TODO Auto-generated method stub
		wl.acquire();
	}
	*/
}
