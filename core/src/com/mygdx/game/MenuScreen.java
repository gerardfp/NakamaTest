package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.google.common.util.concurrent.FutureCallback;
import com.heroiclabs.nakama.Match;
import com.mygdx.game.mywidgets.GameScreen;
import com.mygdx.game.mywidgets.MyLabel;
import com.mygdx.game.mywidgets.MyScreen;
import com.mygdx.game.mywidgets.MyTextButton;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class MenuScreen extends MyScreen {
    Table table;
    boolean matchCreated;
    boolean joinGame;

    public MenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        MyTextButton create = new MyTextButton("CREATE GAME");
        MyTextButton join = new MyTextButton("JOIN GAME");
        MyLabel error = new MyLabel("", Color.RED);

        table.add(create);
        table.row();
        table.add(join);
        table.row();
        table.add(error);


        create.onClick(() ->  Nakama.createMatch(new FutureCallback<Match>() {
            @Override
            public void onSuccess(@NullableDecl Match result) {
                matchCreated = true;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(matchCreated){
            setScreen(new GameScreen(game));
        }
    }
}
