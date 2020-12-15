package com.example.ivan.ogu.cabinet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.ivan.ogu.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class Login extends Fragment {

    Button btnLogin;

    public EditText receipt;
    public EditText phone;

    private QueryLogin queryLogin;

    private String receiptQ;
    private String phoneQ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Личный кабинет");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fr_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogin = view.findViewById(R.id.btnLogin);
        receipt = view.findViewById(R.id.editReceipt);
        phone = view.findViewById(R.id.editPhone);

        receipt.setText("150337");
        phone.setText("89997058274");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewKeyboard();//скрыть клавиатуру
                receiptQ = receipt.getText().toString();
                phoneQ = phone.getText().toString();
                    queryLogin = new QueryLogin();
                    queryLogin.start(receiptQ,phoneQ);

                try {
                    queryLogin.join();
                } catch (InterruptedException ie) {
                    Log.e("pass 0 ", ie.getMessage());
                }

                // если пришел ответ с БД, то переходим в личный кабинет
                if (queryLogin.state == true) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_main, new Cabinet());
                    ft.commit();
                } else {
                    Toast.makeText(getActivity(),"Пользователь не найден",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    // скрыть клавиатуру
    public void  viewKeyboard(){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnLogin.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
