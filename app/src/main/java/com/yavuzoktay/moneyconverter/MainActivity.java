package com.yavuzoktay.moneyconverter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;
import com.yavuzoktay.moneyconverter.model.Coin;
import com.yavuzoktay.moneyconverter.remote.CoinService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CoinService mService;

    RadioButton coin2coin,money2coin,coin2money;
    MaterialSpinner fromSpinner,toSpinner;
    RadioGroup radioGroup;

    Button btnConvert;

    ImageView coinImage;
    TextView toTextView;

    String[] money={"USD","EUR","GBP","TRY"};
    String[] coin={"BTC","ETH","ETC","XRP","LTC","XMR","DASH","MAID","AUR","XEM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService=Common.getCoinService();

        fromSpinner=findViewById(R.id.fromSpinner);
        toSpinner=findViewById(R.id.toSpinner);

        toTextView=findViewById(R.id.toTextView);

        btnConvert=findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateValue();
            }
        });

        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId==R.id.coin2coin)
                    setCoin2CoinSource();
                else if (checkedId==R.id.money2coin)
                    setMoney2CoinSource();
                else if (checkedId==R.id.coin2money)
                    setCoin2MoneySource();
            }
        });

        coin2coin= findViewById(R.id.coin2coin);
        money2coin=findViewById(R.id.money2coin);
        coin2money=findViewById(R.id.coin2money);

        coinImage=findViewById(R.id.coinImage);
        loadCoinList();

    }

    private void loadCoinList() {
        if (coin2money.isSelected())
            setCoin2MoneySource();
        else if (coin2coin.isSelected())
            setCoin2CoinSource();
        else if (money2coin.isSelected())
            setMoney2CoinSource();
    }

    private void setCoin2MoneySource() {
        fromSpinner.setItems(coin);
        toSpinner.setItems(money);
    }

    private void setMoney2CoinSource() {
        fromSpinner.setItems(money);
        toSpinner.setItems(coin);
    }

    private void setCoin2CoinSource() {
        fromSpinner.setItems(coin);
        toSpinner.setItems(coin);
    }

    private void calculateValue() {
        final ProgressDialog mDialog=new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Please waiting...");
        mDialog.show();

        final String coinName=toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString();
        String fromCoin=fromSpinner.getItems().get(fromSpinner.getSelectedIndex()).toString();

        mService.calculateValue(fromCoin,coinName).enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                mDialog.dismiss();
                if (coinName.equals("BTC"))
                    showData(response.body().getBTC());
                else if (coinName.equals("ETC"))
                    showData(response.body().getETC());
                else if (coinName.equals("ETH"))
                    showData(response.body().getETH());
                else if (coinName.equals("AUR"))
                    showData(response.body().getAUR());
                else if (coinName.equals("DASH"))
                    showData(response.body().getDASH());
                else if (coinName.equals("MAID"))
                    showData(response.body().getMAID());
                else if (coinName.equals("LTC"))
                    showData(response.body().getLTC());
                else if (coinName.equals("XMR"))
                    showData(response.body().getXMR());
                else if (coinName.equals("XEM"))
                    showData(response.body().getXEM());
                else if (coinName.equals("USD"))
                    showData(response.body().getUSD());
                else if (coinName.equals("EUR"))
                    showData(response.body().getEUR());
                else if (coinName.equals("GBP"))
                    showData(response.body().getGBP());
                else if (coinName.equals("TRY"))
                    showData(response.body().getTRY());


            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {
                Log.e("ERROR" , t.getMessage());
                mDialog.dismiss();
            }
        });

    }

    private void showData(String value) {
        if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("BTC"))
        {
            Picasso.with(MainActivity.this).load(Common.BTC_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("ETC"))
        {
            Picasso.with(MainActivity.this).load(Common.ETC_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("ETH"))
        {
            Picasso.with(MainActivity.this).load(Common.ETH_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XRP"))
        {
            Picasso.with(MainActivity.this).load(Common.XRP_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("LTC"))
        {
            Picasso.with(MainActivity.this).load(Common.LTC_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("AUR"))
        {
            Picasso.with(MainActivity.this).load(Common.AUR_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("DASH"))
        {
            Picasso.with(MainActivity.this).load(Common.DASH_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("MAID"))
        {
            Picasso.with(MainActivity.this).load(Common.MAID_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XMR"))
        {
            Picasso.with(MainActivity.this).load(Common.XMR_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XEM"))
        {
            Picasso.with(MainActivity.this).load(Common.XEM_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("USD"))
        {
            toTextView.setText("$"+value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("GBP"))
        {
            toTextView.setText("£"+value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("EUR"))
        {
            toTextView.setText("€"+value);
        }
        else if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("TRY"))
        {
            toTextView.setText("₺"+value);
        }
    }
}
