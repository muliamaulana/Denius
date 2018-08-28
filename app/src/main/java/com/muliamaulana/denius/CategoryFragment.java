package com.muliamaulana.denius;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muliamaulana.denius.adapter.TabPagerAdapter;
import com.muliamaulana.denius.fragment.BusinessFragment;
import com.muliamaulana.denius.fragment.EntertainmentFragment;
import com.muliamaulana.denius.fragment.HealthFragment;
import com.muliamaulana.denius.fragment.ScienceFragment;
import com.muliamaulana.denius.fragment.SportsFragment;
import com.muliamaulana.denius.fragment.TechnologyFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private Context context;


    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeadlinesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        context = view.getContext();

        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.title_category));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        viewPager = view.findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {

        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new BusinessFragment(), getResources().getString(R.string.business));
        adapter.addFragment(new TechnologyFragment(), getResources().getString(R.string.technology));
        adapter.addFragment(new ScienceFragment(), getResources().getString(R.string.science));
        adapter.addFragment(new HealthFragment(), getResources().getString(R.string.health));
        adapter.addFragment(new SportsFragment(), getResources().getString(R.string.sports));
        adapter.addFragment(new EntertainmentFragment(), getResources().getString(R.string.entertainment));
        viewPager.setAdapter(adapter);

    }

    // TODO: Rename method, updatargumeurinte  and hook method into UI event
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
//            Toast.makeText(context, "Category Fragment Attached", Toast.LENGTH_SHORT).show();
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
