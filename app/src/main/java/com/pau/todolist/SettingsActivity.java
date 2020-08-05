package com.pau.todolist;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;

public class SettingsActivity extends MainActivity{

    private SharedPreferences mPrefs; //Para leer datos guardados en disco
    private SharedPreferences.Editor mEditor; //Para escribir datos en las Shared Prefs

    private boolean mSound; //Para activar / desactivar el sonido de la app

    public static final int FAST = 0; //Animaciones rápidas
    public static final int SLOW = 1; //Animaciones lentas
    public static final int NONE = 2; //Sin animaciones

    private int mAnimationOption; //Para cambiar el tipo de animación en la app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPrefs = getSharedPreferences("To Do List JB", MODE_PRIVATE);
        mEditor = mPrefs.edit();


        //Lógica de activar y desactivar sonido
        mSound = mPrefs.getBoolean("sound", true);  //True, in case that's the first time we enter the app.

        CheckBox checkBoxSound = (CheckBox) findViewById(R.id.sound_checkbox);


        //This if/else is used when entering the activity. We must get what's in memory.
        if (mSound){
            checkBoxSound.setChecked(true); //Sound will be activated by default.
        }else {
            checkBoxSound.setChecked(false);
        }

        checkBoxSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                //Si el sonido estaba en marcha, lo apagamos
                //Si el sonido estaba apagado, lo ponemos en marcha
                mSound = !mSound;

                mEditor.putBoolean("sound", mSound);    //Saving the selected sound option.
            }
        });


        //Lógica de cambiar de tipo de animación
        mAnimationOption = mPrefs.getInt("anim option", FAST);  //FAST  by default.

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_animation);
        radioGroup.clearCheck();//Deselecciono cualquier radio button


        //Getting what's in memory and checking the button that was selected the last time we used the notes app.
        //En función, de la preferencia del jugador, selecciono uno de los 3 modos de animación
        switch (mAnimationOption){
            case FAST:
                radioGroup.check(R.id.rbFast);
                break;
            case SLOW:
                radioGroup.check(R.id.rbSlow);
                break;
            case NONE:
                radioGroup.check(R.id.rbNone);
                break;
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                //Recuperamos el radio button del radio group que ha sido seleccionado por el usuario a travs del checked ID
                RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);

                if (null != rb && checkedId > -1){

                    switch (rb.getId()) {
                        case R.id.rbFast:
                            mAnimationOption = FAST;
                            break;
                        case R.id.rbSlow:
                            mAnimationOption = SLOW;
                            break;
                        case R.id.rbNone:
                            mAnimationOption = NONE;
                            break;
                    }

                    mEditor.putInt("anim option", mAnimationOption);
                }

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        mEditor.commit();   //Necessary to store everything.
    }




}
