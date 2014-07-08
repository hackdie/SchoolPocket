package com.intsol.school;
 
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
 
import com.google.android.gcm.GCMBaseIntentService;
 
public class GCMIntentService extends GCMBaseIntentService {
 
    private static final String TAG = "School Pocket";
 
    // Use your PROJECT ID from Google API into SENDER_ID
    public static final String SENDER_ID = "85645220674";
    
    public GCMIntentService() {
        super(SENDER_ID);
    }
 
    @Override
    protected void onRegistered(Context context, String registrationId) {
    	Log.i(TAG, "onRegistered: registration	Id=" + registrationId);
    }
 
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "onUnregistered: registrationId=" + registrationId);
    }
 
    @SuppressLint("NewApi")
	@Override
    protected void onMessage(Context context, Intent data) {
    	
    	Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        
    	
        // intent triggered, you can add other intent for other actions
        Intent intent = new Intent(GCMIntentService.this, MainActivity.class);
        
        // Pass data to the new activity
        intent.putExtra("flag_notif", true);
        intent.putExtra("from_mje", data.getStringExtra("from_mje"));
        
        // Starts the activity on notification click
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        
    	
        String message,title;
        // datos desde el servidor
        message = data.getStringExtra("message");
        title = data.getStringExtra("title");
    	       
    	// Construimos Notificacion
        Notification mNotification = new Notification.Builder(this)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentIntent(pIntent)
            .setSound(soundUri)    	
            .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, mNotification);
        
        Log.i("Status","Registro Completo");
    }
 
    @Override
    protected void onError(Context arg0, String errorId) {
 
        Log.e(TAG, "onError: errorId=" + errorId);
    }
 
}