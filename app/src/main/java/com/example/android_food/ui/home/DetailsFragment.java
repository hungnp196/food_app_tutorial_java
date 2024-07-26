package com.example.android_food.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_food.databinding.FragmentDetailsBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.phs.barcodescan.Constant;
import com.phs.barcodescan.JSONParser;
import com.phs.barcodescan.MainActivity;
import com.phs.barcodescan.database.DatabaseHandler;
import com.phs.barcodescan.databinding.FragmentProductBinding;
import com.phs.barcodescan.entity.SearchEntity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    private @NonNull FragmentDetailsBinding binding;
    private int category_id;
    JSONParser jsonParser=new JSONParser();

    TextView detail_name;


    public DetailsFragment() {
        // Required empty public constructor
    }

    public DetailsFragment Intantce() {
        return this;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isSave = true;
    }

    private void getData() {

        if (getArguments() != null) {
            category_id = getArguments().getInt("id");
        } else {
            category_id = 0;
        }

        DetailsFragment.AttemptCheckIn attemptCheckIn= new DetailsFragment.AttemptCheckIn();
        attemptCheckIn.execute(Integer.toString(category_id));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       detail_name = binding.detailName;

        //get data
        getData();

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private class AttemptCheckIn extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {
            String category_id = args[0];

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", category_id));

            JSONObject json = jsonParser.makeHttpRequest("http://localhost:8000/api/get_detail_by_category_id", "GET", params);

            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if ((result != null)&&(result.getInt("status_code")== 0)) {
                    JSONObject arr = result.getJSONObject("data");

                    detail_name.setText(arr.getString("name"));


                } else {
                    Toast.makeText(getContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}