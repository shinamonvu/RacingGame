package teamOne.racinggame.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import teamOne.racinggame.R;
import teamOne.racinggame.models.Bet;
import teamOne.racinggame.models.BetUI;


public class ServiceBet extends AppCompatActivity {
    public static final int MAX_BET = 10000000;
    public static final int MIN_BET = 0;
    public void applyBet(BetUI betUI, Bet bet, TextView balance,
                            Context context){
        betUI.getBtnBet().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, bet, balance, context, betUI);
            }
        });
        betUI.getBtnClearBet().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer bl = Integer.parseInt(balance.getText().toString());
                bl += Integer.parseInt(betUI.getTvBet().getText().toString());
                balance.setText(bl.toString());
                betUI.getTvBet().setText("0");
                bet.setBalance(bl);

            }
        });
    }
    private void showPopupWindow(View view, Bet bet, TextView balance, Context context, BetUI betUI) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the pop-up window layout
        View popupView = inflater.inflate(R.layout.layout_popup, null);
        // Create the pop-up window
        int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        // Find the EditText and Button in the pop-up window layout
        EditText editTextNumber = popupView.findViewById(R.id.editTextNumber);
        editTextNumber.setText(betUI.getTvBet().getText().toString());
        editTextNumber.setFilters(new InputFilter[]{ new InputFilterMinMax(MIN_BET, MAX_BET)});
        Button submitButton = popupView.findViewById(R.id.buttonSubmit);
        Button exitButton = popupView.findViewById(R.id.buttonExit);
        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    editTextNumber.setError("Input invalid!");
                    submitButton.setEnabled(false);

                } else {
                    editTextNumber.setError(null);
                    submitButton.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
            // Other required methods ...
        });
        // Set the Button's OnClickListener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the updated text from the EditText
                Integer betCoin = Integer.parseInt(editTextNumber.getText().toString());
                Integer betRemain = Integer.parseInt(betUI.getTvBet().getText().toString());
                bet.setBet(betCoin);
                if (bet.validateBalance(bet.caculateBalanceRemain())){
                    bet.setBalance(bet.getBalance() - (betCoin - betRemain));
                    balance.setText(bet.getBalance().toString());
                    betUI.getTvBet().setText(betCoin.toString());
                }
                else {
                    showErrorDialog("Your balance is in valid!", context);
                }
                // Dismiss the pop-up window
                popupWindow.dismiss();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        // Show the pop-up window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
    private void showErrorDialog(String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle("Error")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void validateMaxMin(TextView t){
        t.setFilters(new InputFilter[]{ new InputFilterMinMax(ServiceBet.MIN_BET, ServiceBet.MAX_BET)});
    }
    public class InputFilterMinMax implements InputFilter {
        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }
        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }
    }
}
