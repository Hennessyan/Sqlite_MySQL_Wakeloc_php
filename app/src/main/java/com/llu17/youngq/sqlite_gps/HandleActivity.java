package com.llu17.youngq.sqlite_gps;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

import static com.llu17.youngq.sqlite_gps.CollectorService.state;

/**
 * Created by pradeepsaiuppula on 2/9/17.
 */

public class HandleActivity extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
//    UploadData d=new UploadData();

    public HandleActivity(String name) {
        super(name);
    }
    public HandleActivity(){
        //don't remove this line.
        super("Handle Activity");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Hellooooo","do you even come here");
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities( result.getProbableActivities() );
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        int Threshold=75;
        for( DetectedActivity activity : probableActivities ) {
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    Log.e( "ActivityRecogition", "In Vehicle: " + activity.getConfidence() );
                    if( activity.getConfidence() >= Threshold ) {
//                        d.upload_activity(0);
                        state[0] = 0;
                    }
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    Log.e( "ActivityRecogition", "On Bicycle: " + activity.getConfidence() );
                    if( activity.getConfidence() >= Threshold ) {
//                        d.upload_activity(1);
                        state[0] = 1;
                    }
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    Log.e( "ActivityRecogition", "On Foot: " + activity.getConfidence() );
                    if( activity.getConfidence() >= Threshold ) {
//                        d.upload_activity(2);
                        state[0] = 2;
                    }
                    break;
                }
                case DetectedActivity.RUNNING: {
                    Log.e( "ActivityRecogition", "Running: " + activity.getConfidence() );
                    if( activity.getConfidence() >= Threshold ) {
//                        d.upload_activity(8);
                        state[0] = 8;
                    }
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.e( "ActivityRecogition", "Still: " + activity.getConfidence() );
                    if( activity.getConfidence() >= Threshold ) {
//                        d.upload_activity(3);
                        state[0] = 3;
                    }
                    break;
                }
                case DetectedActivity.TILTING: {
                    Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() );
                    if( activity.getConfidence() >= Threshold ) {
//                        d.upload_activity(5);
                        state[0] = 5;
                    }
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.e( "ActivityRecogition", "Walking: " + activity.getConfidence() );
                    if( activity.getConfidence() >= Threshold ) {
//                        d.upload_activity(7);
                        state[0] = 7;
                    }
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence() );
                    if( activity.getConfidence() >= Threshold ) {
//                        d.upload_activity(4);
                        state[0] = 4;
                    }
                    break;
                }
            }
        }
    }
    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
        Log.e("===HA===","stop");
    }
}
