package com.example.emory;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;

public class TodoDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TodoList todolist = TodoList.getInstance();
    ArrayList<TodoList> todos = new ArrayList<>();
    private static final String SHARED_PREFS = "sharedPrefs";
    private DayMonthYear fullDate;
    EditText nameEditText, noteEditText;
    TextView deadlineEditText;
    Calendar now = Calendar.getInstance();

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        fullDate = new DayMonthYear();
        deadlineEditText = findViewById(R.id.deadlineEditText);
        deadlineEditText.setText(fullDate.getCurrentFullDate());

        saveTodoList();
    }

    public void saveTodoList() {
        Button addBtn = findViewById(R.id.addTodo);
        addBtn.setOnClickListener(v -> {
            nameEditText = findViewById(R.id.nameTodoEditText);
            deadlineEditText = findViewById(R.id.deadlineEditText);
            noteEditText = findViewById(R.id.noteEditText);
            Todo todo = new Todo(nameEditText.getText().toString(),
                    deadlineEditText.getText().toString(), noteEditText.getText().toString());
            todolist.addActivity(todo);
            todos.add(todolist);
            Log.d("haha", String.valueOf(todos));
            Gson gson = new Gson();
            SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("todolist", gson.toJson(todos));
            editor.commit();
            Intent intent = new Intent(TodoDetailsActivity.this, AddTodoListActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void onCalendarClick(View v) {
        fullDate.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        deadlineEditText.setText(fullDate.setFullDate(year, month, dayOfMonth));
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, month);
        now.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 07);
        now.set(Calendar.SECOND, 0);
        now.setTimeInMillis(System.currentTimeMillis());
        scheduleNotification(getNotification("You have deadline today"), 2000);

    }

    private void scheduleNotification(Notification notification, long delay) {
        Intent notificationIntent = new Intent(this, Reminder.class);
        notificationIntent.putExtra(Reminder.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(Reminder.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, now.getTimeInMillis(), pendingIntent);
    }

    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle("Notification from Emory");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }
}

