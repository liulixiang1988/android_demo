package liulixiang.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by liulixiang on 15/6/25.
 */
public class AudioPlayer {
    private MediaPlayer mPlayer;

    public void stop(){
        if (mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void start(Context context){
        stop();
        mPlayer = MediaPlayer.create(context, R.raw.one_small_step);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        mPlayer.start();
    }
}
