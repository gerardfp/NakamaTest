package com.mygdx.game.mywidgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.mygdx.game.Assets;



public class MyTextField extends TextField {
    String hint;

    public MyTextField(String hint){
        super(hint, Assets.skin);
        this.hint = hint;

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(MyTextField.super.getText().equals(hint)){
                    setText("");;
                }
                return true;
            }
        });

        addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused){
                    if(MyTextField.super.getText().isEmpty()){
                        setText(hint);
                    }
                }
            }
        });
    }

    public MyTextField(String hint, boolean isPassword){
        this(hint);
        setPasswordMode(isPassword);
    }

    @Override
    public String getText() {
        if(super.getText().equals(hint)){
            return "";
        }
        return super.getText();
    }
}
