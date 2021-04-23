package com.mygdx.game.mywidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Assets;


public class MyLabel extends Label {
    public MyLabel(String text){
        super(text , Assets.skin);
    }

    public MyLabel(String text, Color color){
        this(text);
        setColor(color);
    }
}
