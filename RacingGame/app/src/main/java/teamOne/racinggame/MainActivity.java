package teamOne.racinggame;

import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import teamOne.racinggame.models.Bet;
import teamOne.racinggame.models.BetUI;
import teamOne.racinggame.services.ServiceBet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    double INCOME = 1;
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private SeekBar seekBar4;
    private Handler handler1;
    private Handler handler2;
    private Handler handler3;
    private Handler handler4;
    private Runnable runnable1;
    private Runnable runnable2;
    private Runnable runnable3;
    private Runnable runnable4;
    private ImageButton startBtn;
    private ImageButton resetBtn;
//    private ImageButton btnBet1;
//    private ImageButton btnBet2;
//    private ImageButton btnBet3;
//    private ImageButton btnBet4;
    private TextView tvBet1;
    private TextView tvBet2;
    private TextView tvBet3;
    private TextView tvBet4;
    private ArrayList<TextView> textViewList;
    private TextView tvResult1;
    private TextView tvResult2;
    private TextView tvResult3;
    private TextView tvResult4;
    private TextView tvTotal;
    private String winner;
    private ServiceBet serviceBet = new ServiceBet();
    private ArrayList<BetUI> listBntBet;

    private pl.droidsonroids.gif.GifImageView fire1;
    private pl.droidsonroids.gif.GifImageView fire2;
    private ImageView fire01;
    private ImageView fire02;
    private int currentRank = 0;
    //set up sound effects
    MediaPlayer startSound;
    MediaPlayer racingSound;
    MediaPlayer endRaceSound;
    MediaPlayer betSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random random = new Random();
        int MAX_STEP = 5;
        int MIN_STEP = 1;

        startSound = MediaPlayer.create(this, R.raw.start_sound);
        racingSound = MediaPlayer.create(this, R.raw.racing_sound);
        endRaceSound = MediaPlayer.create(this, R.raw.winner_sound);
        betSound = MediaPlayer.create(this, R.raw.coin_ring);

        // popup bet
        this.listBntBet = new ArrayList<>();
        BetUI betUI1 = new BetUI(findViewById(R.id.btnBet1),
                findViewById(R.id.tvBet1),
                findViewById(R.id.btnCancel1));
        listBntBet.add(betUI1);
        BetUI betUI2 = new BetUI(findViewById(R.id.btnBet2),
                findViewById(R.id.tvBet2),
                findViewById(R.id.btnCancel2));
        listBntBet.add(betUI2);

        BetUI betUI3 = new BetUI(findViewById(R.id.btnBet3),
                findViewById(R.id.tvBet3),
                findViewById(R.id.btnCancel3));
        listBntBet.add(betUI3);
        BetUI betUI4 = new BetUI(findViewById(R.id.btnBet4),
                findViewById(R.id.tvBet4),
                findViewById(R.id.btnCancel4));
        listBntBet.add(betUI4);

        seekBar1 = findViewById(R.id.seekBar1);
        seekBar1.setEnabled(false);

        seekBar2 = findViewById(R.id.seekBar2);
        seekBar2.setEnabled(false);

        seekBar3 = findViewById(R.id.seekBar3);
        seekBar3.setEnabled(false);

        seekBar4 = findViewById(R.id.seekBar4);
        seekBar4.setEnabled(false);

        startBtn = findViewById(R.id.startBtn);
        resetBtn = findViewById(R.id.resetBtn);

        tvBet1 = findViewById(R.id.tvBet1);
        tvBet2 = findViewById(R.id.tvBet2);
        tvBet3 = findViewById(R.id.tvBet3);
        tvBet4 = findViewById(R.id.tvBet4);
        tvResult1 = findViewById(R.id.tvResult1);
        tvResult2 = findViewById(R.id.tvResult2);
        tvResult3 = findViewById(R.id.tvResult3);
        tvResult4 = findViewById(R.id.tvResult4);
        textViewList = new ArrayList<>();
        textViewList.add(tvResult1);
        textViewList.add(tvResult2);
        textViewList.add(tvResult3);
        textViewList.add(tvResult4);

        tvTotal = findViewById(R.id.tvTotal);
        serviceBet.validateMaxMin(tvTotal);

        handler1 = new Handler();
        handler2 = new Handler();
        handler3 = new Handler();
        handler4 = new Handler();

        startBtn.setOnClickListener(this);
        runnable1 = new Runnable() {
            @Override
            public void run() {
                for (BetUI betUI : listBntBet) {
                    betUI.getBtnBet().setEnabled(false);
                    betUI.getBtnClearBet().setEnabled(false);
                }
                seekBar1.setProgress(seekBar1.getProgress() + random.nextInt(MAX_STEP - MIN_STEP) + MIN_STEP);
                handler1.postDelayed(this, 100);
                if (seekBar1.getProgress() == 100) {
                    currentRank += 1;
                    handler1.removeCallbacks(this);
                    updateRank(1, tvBet1, tvResult1);
//                    if (currentRank == 1) {
//                        win_stories(tvBet1);
//                        Toast.makeText(MainActivity.this, "Ngựa số 1 thắng", Toast.LENGTH_LONG).show();
//                    }
                }
            }
        };
        runnable2 = new Runnable() {
            @Override
            public void run() {
                seekBar2.setProgress(seekBar2.getProgress() + random.nextInt(MAX_STEP - MIN_STEP) + MIN_STEP);
                handler2.postDelayed(this, 100);
                if (seekBar2.getProgress() == 100) {
                    currentRank += 1;
                    handler2.removeCallbacks(this);
                    updateRank(2, tvBet2, tvResult2);
//                      stopHandlers();
//                      win_stories(tvBet2);
//                      Toast.makeText(MainActivity.this, "Ngựa số 2 thắng", Toast.LENGTH_LONG).show();
////                    raceResult.setText("Winner: seekBar2");
//                      resetBtn.setOnClickListener(MainActivity.this);
                }
            }
        };
        runnable3 = new Runnable() {
            @Override
            public void run() {
                seekBar3.setProgress(seekBar3.getProgress() + random.nextInt(MAX_STEP - MIN_STEP) + MIN_STEP);
                handler3.postDelayed(this, 100);
                if (seekBar3.getProgress() == 100) {
                    currentRank += 1;
                    handler3.removeCallbacks(this);
                    updateRank(3, tvBet3, tvResult3);
//                      stopHandlers();
//                      win_stories(tvBet3);
//                      Toast.makeText(MainActivity.this, "Ngựa số 3 thắng", Toast.LENGTH_LONG).show();
//                      raceResult.setText("Winner: seekBar3");
//                      resetBtn.setOnClickListener(MainActivity.this);
                }
            }
        };
        runnable4 = new Runnable() {
            @Override
            public void run() {
                seekBar4.setProgress(seekBar4.getProgress() + random.nextInt(MAX_STEP - MIN_STEP) + MIN_STEP);
                handler4.postDelayed(this, 100);
                if (seekBar4.getProgress() == 100) {
                    currentRank += 1;
                    handler4.removeCallbacks(this);
                    updateRank(4, tvBet4, tvResult4);
//                      stopHandlers();
//                      win_stories(tvBet4);
////                      raceResult.setText("Winner: seekBar4");
//                      resetBtn.setOnClickListener(MainActivity.this);
                }
            }
        };


        int balance = Integer.parseInt(tvTotal.getText().toString());
        Bet bet = new Bet(balance, 0);
        for (BetUI betUI : listBntBet) {
            serviceBet.applyBet(betUI, bet, tvTotal, MainActivity.this);
        }
    }
    private void win_stories(TextView tvWin){
        //son
        if(racingSound.isPlaying()){
            racingSound.pause();
        }
        endRaceSound.start();
        fire01 = findViewById(R.id.fire01);
        fire02 = findViewById(R.id.fire02);
        fire01.setImageResource(R.drawable.rong);
        fire02.setImageResource(R.drawable.rong);
        fire1 = (pl.droidsonroids.gif.GifImageView) findViewById(R.id.fire1);
        fire1.setImageResource(R.drawable.feuerwerk_137);
        fire2 = (pl.droidsonroids.gif.GifImageView) findViewById(R.id.fire2);
        fire2.setImageResource(R.drawable.feuerwerk_137);
        winner = tvWin.getText().toString();
        changeMoney();
        clearMoneys();
    }
    private void changeMoney(){
        int total = Integer.parseInt(tvTotal.getText().toString());
        int betNum = Integer.parseInt(winner);
        double revenue = betNum * ( 1 + INCOME);
        total = total + (int) revenue;
        String result = ""+total;
        tvTotal.setText(result);
    }


    private void startRace() {
        resetBtn.setOnClickListener(null);
        startBtn.setOnClickListener(null);

        // Start updating the SeekBars' progress
        handler1.postDelayed(runnable1, 100);
        handler2.postDelayed(runnable2, 100);
        handler3.postDelayed(runnable3, 100);
        handler4.postDelayed(runnable4, 100);

    }

//    private void stopHandlers() {
//        handler1.removeCallbacks(runnable1);
//        handler2.removeCallbacks(runnable2);
//        handler3.removeCallbacks(runnable3);
//        handler4.removeCallbacks(runnable4);
//
//    }

    private void resetRace() {
        startBtn.setOnClickListener(MainActivity.this);
        currentRank = 0;

        for (TextView tv:
                textViewList) {
            tv.setText("");
        }

        seekBar1.setProgress(0);
        seekBar2.setProgress(0);
        seekBar3.setProgress(0);
        seekBar4.setProgress(0);
        fire1.setImageResource(R.drawable.fire);
        fire2.setImageResource(R.drawable.fire);
    }

    private void updateRank(int i, TextView tvBet, TextView tvResult) {
        // update end of track ranking
        tvResult.setText("" + currentRank);

        if (currentRank == 1) {
            Toast.makeText(MainActivity.this, "Animal racer on lane number " + i + " won!", Toast.LENGTH_LONG).show();
            win_stories(tvBet);
        }

        if (currentRank == 4) {
            resetBtn.setOnClickListener(MainActivity.this);
            for (BetUI betUI : this.listBntBet) {
                betUI.getBtnBet().setEnabled(true);
                betUI.getBtnClearBet().setEnabled(true);
            }
        }
        int balance = Integer.parseInt(tvTotal.getText().toString());
        Bet bet = new Bet(balance, 0);
        for (BetUI betUI : listBntBet) {
            serviceBet.applyBet(betUI, bet, tvTotal, MainActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Start button
            case R.id.startBtn:
                startSound.start();
                startRace();
                racingSound.start();
                break;
            case R.id.resetBtn:
                resetRace();
                break;
        }
    }
    public void clearMoneys(){
        tvBet1.setText("0");
        tvBet2.setText("0");
        tvBet3.setText("0");
        tvBet4.setText("0");
    }


}