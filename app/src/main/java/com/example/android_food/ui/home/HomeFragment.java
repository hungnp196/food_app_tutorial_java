package com.example.android_food.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_food.databinding.FragmentHomeBinding;
import com.example.android_food.ui.entity.tblCategoryEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private GridView gridView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        gridView = binding.gridview;

        getData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData() {
        HomeFragment.AttemptGetCategory attem_getCategory = new HomeFragment().AttemptGetCategory();
        attem_getCategory.execute();
    }


    private class AttemptGetCategory extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {
            JSONObject json = jsonParser.makeHttpRequest("http://localhost:8000/api/get_categories", "GET");
            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted

            try {
                if ((result != null)&&(result.getInt("status_code")== 0)) {
                    JSONObject arr = result.getJSONObject("data");
                    ArrayList<tblCategoryEntity> list = new ArrayList<tblCategoryEntity>();

                    for (int i = 0 ; i < arr.length(); i++) {
                        tblCategoryEntity entity = new tblCategoryEntity();
                        entity.setId(arr.getInt("id"));
                        entity.setName(arr.getString("name"));
                        entity.setUrl(arr.getString("url"));
                        list.add(entity);
                    }


                    //SET DU LIEU CHO LISTVIEW
                    gridView.setAdapter(new HomeGridViewAdapter(getActivity().getApplicationContext(), list));

                } else {
                    Toast.makeText(getContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}