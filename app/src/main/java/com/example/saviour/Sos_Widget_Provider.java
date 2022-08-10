package com.example.saviour;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class Sos_Widget_Provider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appwidget : appWidgetIds) {
            Intent intent = new Intent(context, SmsService.class);
           // intent.setFlags(Intent.);
            //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            PendingIntent pendingIntent=PendingIntent.getService(context,0,intent,0);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.sos_widget);
            remoteViews.setOnClickPendingIntent(R.id.soswidgeticon, pendingIntent);
            appWidgetManager.updateAppWidget(appwidget, remoteViews);
        }
    }
}