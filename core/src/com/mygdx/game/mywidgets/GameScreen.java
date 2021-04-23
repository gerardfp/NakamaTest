package com.mygdx.game.mywidgets;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Nakama;

public class GameScreen extends MyScreen{

    public GameScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        System.out.println("GAME SCREEN");
        Nakama.writeAlgo();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
