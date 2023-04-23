package ru.mirea.volegovaa.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import ru.mirea.volegovaa.data_thread.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
        private ActivityMainBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            final Runnable runn1 = new Runnable() {
                public void run() {
                    binding.QueueRuns.setText("Run1");
                }
            };

            final Runnable runn2 = new Runnable() {
                public void run() {
                    binding.QueueRuns.setText("Run2");
                }
            };

            final Runnable runn3 = new Runnable() {
                public void run() {
                    binding.QueueRuns.setText("Run3");
                }
            };



            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        runOnUiThread(runn1);

                        TimeUnit.SECONDS.sleep(1);
                        binding.QueueRuns.postDelayed(runn3, 2000);

                        binding.QueueRuns.post(runn2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
    }
}