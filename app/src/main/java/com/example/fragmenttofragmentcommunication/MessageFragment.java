package com.example.fragmenttofragmentcommunication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MessageFragment extends Fragment {

    private Button button;
    private EditText editText;

//  Create interface to communicate between fragments
    OnMessageSendListener messageSendListener;
    public interface OnMessageSendListener
    {
        public void onMessageSend(String message);
    }

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_message, container, false);
        button = view.findViewById(R.id.bn);
        editText = view.findViewById(R.id.txt_message);

//        Handle button click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onClick get the message from the editText
                String message = editText.getText().toString();
                messageSendListener.onMessageSend(message);
            }
        });

        return view;
    }
//    initialize the interface with onAttach method
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try{
            messageSendListener = (OnMessageSendListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+"must implement onMessageSend...");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        reload the editText
        editText.setText("");
    }
}