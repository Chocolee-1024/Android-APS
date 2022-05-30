package com.example.aps.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aps.Activity.TableActivity;
import com.example.aps.Activity.TableRecyclerviewActivity;
import com.example.aps.Adapter.ScheduleOfTheDayRecyclerviewAdapter;
import com.example.aps.Contract.ScheduleOfTheDayContract;
import com.example.aps.databinding.FragmentScheduleOfTheDayBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleOfTheDayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleOfTheDayFragment extends Fragment implements ScheduleOfTheDayContract.View {
    private FragmentScheduleOfTheDayBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView scheduleRV;
    private RecyclerView.Adapter scheduleAdapt;
    private RecyclerView.LayoutManager scheduleManager;
    public ScheduleOfTheDayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Schedule_of_the_day.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleOfTheDayFragment newInstance(String param1, String param2) {
        ScheduleOfTheDayFragment fragment = new ScheduleOfTheDayFragment();
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
        binding =FragmentScheduleOfTheDayBinding.inflate(inflater, container, false);
        init(); //初始化
        return binding.getRoot();

    }

    private void init() {
        scheduleManager = new LinearLayoutManager(getContext());
        binding.scheduleRV.setLayoutManager(scheduleManager);
        scheduleAdapt = new ScheduleOfTheDayRecyclerviewAdapter(this);
        binding.scheduleRV.setAdapter(scheduleAdapt);
    }

    @Override
    //換頁至TableActivity，並告訴它是從ScheduleOfTheDay進入的
    public void selectTable(int num) {
        Intent intent = new Intent(getActivity(), TableActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("whichNumber","2");
        intent.putExtras(bundle);
        startActivity(intent);

    }
}