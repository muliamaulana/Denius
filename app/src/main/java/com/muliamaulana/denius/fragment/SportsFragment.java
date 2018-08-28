package com.muliamaulana.denius.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.muliamaulana.denius.BuildConfig;
import com.muliamaulana.denius.R;
import com.muliamaulana.denius.adapter.CategoryAdapter;
import com.muliamaulana.denius.api.ClientAPI;
import com.muliamaulana.denius.api.NewsInterface;
import com.muliamaulana.denius.model.Headlines;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SportsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SportsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private OnFragmentInteractionListener mListener;

    public SportsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SportsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SportsFragment newInstance(String param1, String param2) {
        SportsFragment fragment = new SportsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sports, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_sports);
        categoryAdapter = new CategoryAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoryAdapter);
        swipeRefreshLayout = view.findViewById(R.id.refresh_sports);

        swipeRefreshLayout.setRefreshing(true);
        loadNews();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews();
            }
        });

        return view;
    }

    private void loadNews() {
        NewsInterface newsInterface = ClientAPI.getNewsAPI().create(NewsInterface.class);
        Call<Headlines> headlinesCall = newsInterface.getCategory("id", "sports", BuildConfig.API_KEY);
        headlinesCall.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(@NonNull Call<Headlines> call, @NonNull Response<Headlines> response) {
                if (response.isSuccessful()){
                    swipeRefreshLayout.setRefreshing(false);
                    Log.d("Headlines Response", response.toString());
                    categoryAdapter.addAll(response.body().getArticles());
                }

            }

            @Override
            public void onFailure(@NonNull Call<Headlines> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.e("Headlines Failure",t.toString());
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            Toast.makeText(context, "Sport Fragment Attached", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
