package ru.mirea.volegovaa.looper;

import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.volegovaa.looper.databinding.ActivityMainBinding;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d("result", msg.getData().getString("result"));
            }
        };

        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.start.setText("Мой номер по списку 3");
        binding.ClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();

                bundle.putString("prof", binding.ProfessionEdit.getText().toString());
                bundle.putString("exp", binding.ExpirienceEdit.getText().toString());

                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }
}