package team4.com.team4eshop;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.textView);
        text.setTextColor(Color.BLUE);
        text.setTextSize(36);

        Button btnBuy = (Button) findViewById(R.id.button2);
        btnBuy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0)
            {   Intent i = new Intent(getApplicationContext(),CategoryActivity.class);
                startActivity(i);
            }
        });

        Button btnSell = (Button) findViewById(R.id.button);
        btnSell.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0)
            {   Intent i = new Intent(getApplicationContext(),SellItem.class);
                startActivity(i);
            }
        });
    }
}
