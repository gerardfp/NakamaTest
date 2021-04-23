package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.mywidgets.MyScreen;

public class MenuScreen extends MyScreen {
    Table table;

    public MenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton create = new TextButton("CREATE GAME", Assets.skin);
        TextButton join = new TextButton("JOIN GAME", Assets.skin);

        Label error = new Label("", Assets.skin);
        error.setColor(Color.RED);

        table.add(create);
        table.row();
        table.add(join);
        table.row();
        table.add(error);

        create.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
    }
}
