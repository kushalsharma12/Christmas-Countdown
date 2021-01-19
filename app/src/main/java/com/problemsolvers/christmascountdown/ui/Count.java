package com.problemsolvers.christmascountdown.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.problemsolvers.christmascountdown.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Count extends Fragment {
    private TextView txtDay, txtHour, txtMinute, txtSecond;
    private TextView tvEventStart;
    private Handler handler;
    private Runnable runnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_count, container, false);
        txtDay = root.findViewById(R.id.txtDay);
        txtHour = root.findViewById(R.id.txtHour);
        txtMinute = root.findViewById(R.id.txtMinute);
        txtSecond = root.findViewById(R.id.txtSecond);
        tvEventStart = root.findViewById(R.id.tveventStart);

        handler = new Handler();
        runnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2020-12-25");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / ( 24 * 60 * 60 * 1000 );
                        diff -= days * ( 24 * 60 * 60 * 1000 );
                        long hours = diff / ( 60 * 60 * 1000 );
                        diff -= hours * ( 60 * 60 * 1000 );
                        long minutes = diff / ( 60 * 1000 );
                        diff -= minutes * ( 60 * 1000 );
                        long seconds = diff / 1000;
                        txtDay.setText("" + String.format("%02d", days));
                        txtHour.setText("" + String.format("%02d", hours));
                        txtMinute.setText(""
                                + String.format("%02d", minutes));
                        txtSecond.setText(""
                                + String.format("%02d", seconds));
                    } else {
                        tvEventStart.setVisibility(View.VISIBLE);
                        tvEventStart.setText("Merry Christmas! You are amazing " +
                                "and this complete universe is helping you to make" +
                                " you more amazing.So enjoy the Christmas and Be Happy!");

                        root.findViewById(R.id.LinearLayout).setVisibility(View.GONE);
//                        root.findViewById(R.id.LinearLayout2).setVisibility(View.GONE);
//                        root.findViewById(R.id.LinearLayout3).setVisibility(View.GONE);
//                        root.findViewById(R.id.LinearLayout4).setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);

        return root;

    }
}
