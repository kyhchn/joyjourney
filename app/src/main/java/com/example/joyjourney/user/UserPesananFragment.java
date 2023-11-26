package com.example.joyjourney.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joyjourney.R;
import com.example.joyjourney.admin.PesananAdapter;
import com.example.joyjourney.databinding.FragmentUserPesananBinding;
import com.example.joyjourney.model.Pesanan;
import com.example.joyjourney.utils.PesananDialog;

import java.util.LinkedList;
import java.util.List;

public class UserPesananFragment extends Fragment {
    FragmentUserPesananBinding binding;
    UserSharedViewModel vm;
    List<Pesanan> pesananList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserPesananBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(requireActivity()).get(UserSharedViewModel.class);
        pesananList = new LinkedList<>();
        vm.fetchPesanan();

        PesananAdapter adapter = new PesananAdapter(pesananList,getContext(), pesanan -> {
            PesananDialog pesananDialog = new PesananDialog(requireActivity(), pesanan);
            pesananDialog.showDialog();
        } );
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.historyRecycler.setLayoutManager(layoutManager);
        binding.historyRecycler.setAdapter(adapter);

        vm.getListPesanan().observe( getViewLifecycleOwner(), pesanans -> {
            pesananList.clear();
            pesananList.addAll(pesanans);
            adapter.notifyDataSetChanged();
        });

    }
}