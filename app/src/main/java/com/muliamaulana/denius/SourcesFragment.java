package com.muliamaulana.denius;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muliamaulana.denius.adapter.SourcesAdapter;
import com.muliamaulana.denius.api.ClientAPI;
import com.muliamaulana.denius.api.NewsInterface;
import com.muliamaulana.denius.model.Sources;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SourcesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SourcesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SourcesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private SourcesAdapter adapter;
    private Toolbar toolbar;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;

    private OnFragmentInteractionListener mListener;

    public SourcesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SourcesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SourcesFragment newInstance(String param1, String param2) {
        SourcesFragment fragment = new SourcesFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);
        toolbar = view.findViewById(R.id.toolbar_sources);
        toolbar.setTitle(getResources().getString(R.string.title_channel));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        swipeRefreshLayout = view.findViewById(R.id.refresh_sources);

        recyclerView = view.findViewById(R.id.recycler_sources);
        adapter = new SourcesAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
        final Call<Sources> sourcesCall = newsInterface.getSources(BuildConfig.API_KEY);
        sourcesCall.enqueue(new Callback<Sources>() {
            @Override
            public void onResponse(Call<Sources> call, Response<Sources> response) {
                if (response.isSuccessful()){
                    swipeRefreshLayout.setRefreshing(false);
                    Log.d(TAG, "onResponse: "+ response.toString());
                    adapter.addAll(response.body().getSources());
                }
            }

            @Override
            public void onFailure(Call<Sources> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
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
