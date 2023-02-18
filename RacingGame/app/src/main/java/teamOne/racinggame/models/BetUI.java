package teamOne.racinggame.models;

import android.widget.ImageButton;
import android.widget.TextView;

public class BetUI {
    private ImageButton btnBet;
    private TextView tvBet;
    private ImageButton imgBet;
    private ImageButton btnClearBet;

    public BetUI() {
    }

    public BetUI(ImageButton btnBet, TextView tvBet, ImageButton btnClearBet) {
        this.btnBet = btnBet;
        this.tvBet = tvBet;
        this.btnClearBet = btnClearBet;
    }

    public ImageButton getBtnBet() {
        return btnBet;
    }

    public void setBtnBet(ImageButton btnBet) {
        this.btnBet = btnBet;
    }

    public TextView getTvBet() {
        return tvBet;
    }

    public void setTvBet(TextView tvBet) {
        this.tvBet = tvBet;
    }

    public ImageButton getImgBet() {
        return imgBet;
    }

    public void setImgBet(ImageButton imgBet) {
        this.imgBet = imgBet;
    }

    public ImageButton getBtnClearBet() {
        return btnClearBet;
    }

    public void setBtnClearBet(ImageButton btnClearBet) {
        this.btnClearBet = btnClearBet;
    }
}
