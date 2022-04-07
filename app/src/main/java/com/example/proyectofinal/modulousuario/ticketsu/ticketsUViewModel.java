package com.example.proyectofinal.modulousuario.ticketsu;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyectofinal.databinding.FragmentTicketsuBinding;

public class ticketsUViewModel extends ViewModel
{

    private final MutableLiveData<String> mText;


    public ticketsUViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}