package il.ac.huji.tipcalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TipCalculatorActivity extends ActionBarActivity {

    //const
    public static final String TAG = "MY_LOG";
    public static final double TIP_PERCENT = 0.12;

    //views
    RelativeLayout background;
    Button btnCalc;
    CheckBox chkBoxRnd;
    EditText edtTxtAmount;
    TextView txtResult;

    //variables
    boolean round = true;
    double billAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        //init variables
        btnCalc = (Button) findViewById(R.id.btnCalculate);
        edtTxtAmount = (EditText) findViewById(R.id.edtBillAmount);
        chkBoxRnd = (CheckBox) findViewById(R.id.chkRound);
        txtResult = (TextView) findViewById(R.id.txtTipResult);

        edtTxtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                //check if the content is a number
                if (text.matches("-?\\d+(\\.\\d+)?")){
                    billAmount = Double.parseDouble(text);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        chkBoxRnd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                round = isChecked;
            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double tip = billAmount * TIP_PERCENT;
                //should we round?
                if (round) {
                    tip = Math.round(tip);
                }

                String resultText = "Tip: " + tip + "$";
                txtResult.setText(resultText);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
