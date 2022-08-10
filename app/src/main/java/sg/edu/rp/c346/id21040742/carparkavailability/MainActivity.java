package sg.edu.rp.c346.id21040742.carparkavailability;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvCarpark;
    AsyncHttpClient client;
    Button btnSearch;
    EditText text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCarpark = findViewById(R.id.lvCarpark);
        client = new AsyncHttpClient();
        btnSearch = findViewById(R.id.btnSearch);
        text = findViewById(R.id.EditText);
        ArrayList<Carpark> alCarpark = new ArrayList<Carpark>();
        ArrayList<Carpark> alResult = new ArrayList();

//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String search =  text.getText().toString();
//
//                for (int i = 0; i < alCarpark.size(); i++ ) {
//                    if (alCarpark.get(i).getCarpark_number().contains(search)) {
//                        alResult.add(alCarpark.get(i));
//
//                    }
//                }
//
//                ArrayAdapter<Carpark> adapter = new ArrayAdapter<Carpark>(MainActivity.this,android.R.layout.simple_list_item_1,alResult);
//                lvCarpark.setAdapter((adapter));
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Carpark> alCarpark = new ArrayList<Carpark>();


        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            String total_lots;
            String lot_type;
            String lots_available;
            String carpark_number;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrCarparkData = firstObj.getJSONArray("carpark_data");


                    for (int i = 0; i < jsonArrCarparkData.length(); i++) {
                        JSONObject jsonObjCarparkData = jsonArrCarparkData.getJSONObject(i);
                        JSONArray jsonArrCarparkInfo = jsonObjCarparkData.getJSONArray("carpark_info");
                        JSONObject jsonObjCarparkData2 = jsonArrCarparkInfo.getJSONObject(0);

                        carpark_number = jsonObjCarparkData.getString("carpark_number");
                        total_lots = jsonObjCarparkData2.getString("total_lots");
                        lot_type = jsonObjCarparkData2.getString("lot_type");
                        lots_available = jsonObjCarparkData2.getString("lots_available");

                        Carpark carpark = new Carpark(total_lots, lot_type, lots_available,carpark_number);
                        alCarpark.add(carpark);
                    }
                } catch (JSONException e) {
                    Log.d("Exception", e.toString());

                }

                //POINT X – Code to display List View

                ArrayAdapter<Carpark> adapter = new ArrayAdapter<Carpark>(MainActivity.this,android.R.layout.simple_list_item_1,alCarpark);
                lvCarpark.setAdapter((adapter));


            }//end onSuccess
        });
    }//end onResume
}