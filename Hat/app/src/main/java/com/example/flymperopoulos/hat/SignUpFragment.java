package com.example.flymperopoulos.hat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by flymperopoulos on 10/18/2014.
 */
public class SignUpFragment extends Fragment {

    private Context context;
    public SignUpFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.signup, container, false);

        final EditText userName = (EditText)rootView.findViewById(R.id.user);
        final EditText userPhone = (EditText)rootView.findViewById(R.id.phone);
        final RelativeLayout relativeLayout = (RelativeLayout)rootView.findViewById(R.id.rellayout);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(v==relativeLayout){
                    InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(userName.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(userPhone.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        Button loginButton = (Button)rootView.findViewById(R.id.signup_button);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().isEmpty() || userPhone.getText().toString().isEmpty()){
                    Toast.makeText(context, "Enter a valid username or phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userPhone.getText().toString().length()<10){
                    Toast.makeText(context, "Enter a 10-digit number with no spaces or dashes", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, "Welcome, " + userName.getText().toString() + "!", Toast.LENGTH_SHORT).show();

                final MyActivity activity = (MyActivity)getActivity();
                final String username = userName.getText().toString();
                final String phonenumber = userPhone.getText().toString();
                Log.d("Debug", "change to mainfrag");
                activity.changeToMainPage();
            }

        });
        return rootView;
    }

}
