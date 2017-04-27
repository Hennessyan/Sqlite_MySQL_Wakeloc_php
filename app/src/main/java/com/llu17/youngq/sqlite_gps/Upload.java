package com.llu17.youngq.sqlite_gps;

import android.content.ContentValues;
import android.database.SQLException;
import android.util.Log;

import com.llu17.youngq.sqlite_gps.data.GpsContract;

import java.util.TimerTask;

import static com.llu17.youngq.sqlite_gps.CollectorService.id;
import static com.llu17.youngq.sqlite_gps.CollectorService.mDb;

/**
 * Created by youngq on 17/2/9.
 */

public class Upload extends TimerTask {
    int count = 0;
    double[] nums1,nums2,nums3,nums4;
    int[] nums5;
    int step = 0;
    int tag = 0;

    public Upload(double[] array1, double[] array2, double[] array3, double[] array4, int[] array5){
        nums1 = array1;
        nums2 = array2;
        nums3 = array3;
        nums4 = array4;
        nums5 = array5;
    }


    @Override
    public void run() {
        count++;
        long temp_time = System.currentTimeMillis();

        ContentValues cv_acce = new ContentValues();
        cv_acce.put(GpsContract.AccelerometerEntry.COLUMN_ID,id);
        cv_acce.put(GpsContract.AccelerometerEntry.COLUMN_TAG, tag);
        cv_acce.put(GpsContract.AccelerometerEntry.COLUMN_TIMESTAMP,temp_time);
        cv_acce.put(GpsContract.AccelerometerEntry.COLUMN_X, nums1[0]);
        cv_acce.put(GpsContract.AccelerometerEntry.COLUMN_Y, nums1[1]);
        cv_acce.put(GpsContract.AccelerometerEntry.COLUMN_Z, nums1[2]);

        ContentValues cv_gyro = new ContentValues();
        cv_gyro.put(GpsContract.GyroscopeEntry.COLUMN_ID,id);
        cv_gyro.put(GpsContract.GyroscopeEntry.COLUMN_TAG, tag);
        cv_gyro.put(GpsContract.GyroscopeEntry.COLUMN_TIMESTAMP,temp_time);
        cv_gyro.put(GpsContract.GyroscopeEntry.COLUMN_X, nums2[0]);
        cv_gyro.put(GpsContract.GyroscopeEntry.COLUMN_Y, nums2[1]);
        cv_gyro.put(GpsContract.GyroscopeEntry.COLUMN_Z, nums2[2]);

        step = (int)nums3[1];
        ContentValues cv_step = new ContentValues();
        cv_step.put(GpsContract.StepEntry.COLUMN_ID,id);
        cv_step.put(GpsContract.StepEntry.COLUMN_TAG, tag);
        cv_step.put(GpsContract.StepEntry.COLUMN_TIMESTAMP,temp_time);
        cv_step.put(GpsContract.StepEntry.COLUMN_COUNT, step);

        ContentValues cv_gps = new ContentValues();
        cv_gps.put(GpsContract.GpsEntry.COLUMN_ID,id);
        cv_gps.put(GpsContract.GpsEntry.COLUMN_TAG, tag);
        cv_gps.put(GpsContract.GpsEntry.COLUMN_TIMESTAMP,temp_time);
        cv_gps.put(GpsContract.GpsEntry.COLUMN_LATITUDE, nums4[0]);
        cv_gps.put(GpsContract.GpsEntry.COLUMN_LONGITUDE, nums4[1]);

        ContentValues cv_motion = new ContentValues();
        cv_motion.put(GpsContract.MotionStateEntry.COLUMN_ID,id);
        cv_motion.put(GpsContract.MotionStateEntry.COLUMN_TAG, tag);
        cv_motion.put(GpsContract.MotionStateEntry.COLUMN_TIMESTAMP,temp_time);
        cv_motion.put(GpsContract.MotionStateEntry.COLUMN_STATE, nums5[0]);
        try
        {
            mDb.beginTransaction();
            mDb.insert(GpsContract.AccelerometerEntry.TABLE_NAME, null, cv_acce);
            mDb.insert(GpsContract.GyroscopeEntry.TABLE_NAME, null, cv_gyro);
            mDb.insert(GpsContract.StepEntry.TABLE_NAME, null, cv_step);
            mDb.insert(GpsContract.GpsEntry.TABLE_NAME, null, cv_gps);
            mDb.insert(GpsContract.MotionStateEntry.TABLE_NAME, null, cv_motion);
            mDb.setTransactionSuccessful();
            Log.e("===insert===","success!" + count);
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            mDb.endTransaction();
        }
    }
    
}

