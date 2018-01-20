package com.example.android.navdrawer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.navdrawer.Adapters.ItemAdapter;
import com.example.android.navdrawer.Model.PhotoModel;
import com.example.android.navdrawer.R;
import com.example.android.navdrawer.api.RestApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pc on 1/11/2018.
 */

public class PhotoFragment extends Fragment {

    ItemAdapter itemAdapter;
    PhotoModel photoModel;
    RestApi api;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.search)
    EditText searchBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_photos, container, false);
        ButterKnife.bind(this, rootView);



        progressBar.setVisibility(View.INVISIBLE);
        api = new RestApi(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                api.checkInternet(new Runnable() {
                    @Override
                    public void run() {
                        Call<PhotoModel> call = api.getSearchPhotos(searchBar.getText().toString());
                        call.enqueue(new Callback<PhotoModel>() {
                            @Override
                            public void onResponse(Call<PhotoModel> call, Response<PhotoModel> response) {
                                if (response.isSuccessful()) {
                                    photoModel = response.body();
                                    itemAdapter = new ItemAdapter(getActivity(), photoModel);
                                    if (searchBar.getText().length()>= 3) {
                                        recyclerView.setAdapter(itemAdapter);
                                        progressBar.setVisibility(View.GONE);
                                    }


                                } else {
                                    Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<PhotoModel> call, Throwable t) {
                                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

            }
        });


        api.checkInternet(new Runnable() {
            @Override
            public void run() {
                Call<PhotoModel> call = api.getPhotos("fresh_today");
                call.enqueue(new Callback<PhotoModel>() {
                    @Override
                    public void onResponse(Call<PhotoModel> call, Response<PhotoModel> response) {
                        if (response.code()==200) {
                            photoModel = response.body();
                            itemAdapter = new ItemAdapter(getActivity(), photoModel);
                            recyclerView.setAdapter(itemAdapter);
                            progressBar.setVisibility(View.GONE);


                        } else if (response.code()==401){
                            Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<PhotoModel> call, Throwable t) {
                        Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.VISIBLE);
                refreshRecycleView();

            }
        });

        return  rootView;

    }
    public void refreshRecycleView (){
        api.checkInternet(new Runnable() {
            @Override
            public void run() {
                Call<PhotoModel> call = api.getPhotos("fresh_today");
                call.enqueue(new Callback<PhotoModel>() {
                    @Override
                    public void onResponse(Call<PhotoModel> call, Response<PhotoModel> response) {
                        if (response.code()==200) {
                            photoModel = response.body();
                            itemAdapter = new ItemAdapter(getActivity(), photoModel);
                            recyclerView.setAdapter(itemAdapter);
                            progressBar.setVisibility(View.GONE);

                        } else if (response.code()==401){
                            Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<PhotoModel> call, Throwable t) {
                        Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
