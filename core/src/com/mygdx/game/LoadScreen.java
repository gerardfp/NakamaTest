package com.mygdx.game;


import com.mygdx.game.mywidgets.MyScreen;

public class LoadScreen extends MyScreen {

    public LoadScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        (S.assets = new Assets()).load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(!S.assets.update()) return;

        setScreen(new LoginScreen(game));
    }
}