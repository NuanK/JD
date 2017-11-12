package com.bwie.view;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.fragment.Fragment_cart;
import com.bwie.fragment.Fragment_class;
import com.bwie.fragment.Fragment_find;
import com.bwie.fragment.Fragment_home;
import com.bwie.fragment.Fragment_my;
import com.bwie.test.a09aproject.R;

public class MainActivity extends AppCompatActivity {

    RadioGroup radio;

    Fragment_home fhome;

    Fragment_class fclass;

    Fragment_find find;

    Fragment_cart fcart;

    Fragment_my fmy;

    RadioButton nine_home,nine_class,nine_find,nine_cart,nine_my;

    FragmentManager fm;

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        InData();
    }

    private void init() {

        nine_home=(RadioButton)findViewById(R.id.nine_home);

        nine_class=(RadioButton)findViewById(R.id.nine_class);

        nine_find=(RadioButton)findViewById(R.id.nine_find);

        nine_cart=(RadioButton)findViewById(R.id.nine_cart);

        nine_my=(RadioButton)findViewById(R.id.nine_my);

        radio=(RadioGroup)findViewById(R.id.group);

        fhome=new Fragment_home();

        fclass=new Fragment_class();

        find=new Fragment_find();

        fcart=new Fragment_cart();

        fmy=new Fragment_my();

    }

    private void InData() {
        nine_home.setChecked(true);
        final FragmentManager fm1=getSupportFragmentManager();
        FragmentTransaction ftransaction=fm1.beginTransaction();
        ftransaction.replace(R.id.fra_layout,fhome);
        ftransaction.commit();

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                fm=getSupportFragmentManager();
                transaction=fm.beginTransaction();

                switch (i){
                    case R.id.nine_home:
                        transaction.replace(R.id.fra_layout,fhome);
                        break;
                    case R.id.nine_class:
                        transaction.replace(R.id.fra_layout,fclass);
                        break;
                    case R.id.nine_find:
                        transaction.replace(R.id.fra_layout,find);
                        break;
                    case R.id.nine_cart:
                        transaction.replace(R.id.fra_layout,fcart);
                        break;
                    case R.id.nine_my:
                        transaction.replace(R.id.fra_layout,fmy);
                        break;
                }
                transaction.commit();
            }
        });
    }
}
