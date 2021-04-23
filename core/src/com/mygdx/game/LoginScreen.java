package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.heroiclabs.nakama.api.Account;
import com.mygdx.game.mywidgets.MyScreen;
import com.mygdx.game.mywidgets.MyTextField;

public class LoginScreen extends MyScreen {
    private boolean loggedIn;

    public LoginScreen(MyGdxGame game) {
        super(game);
    }

    Table table;
    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label label = new Label("Login to your account", Assets.skin);
        TextField email = new MyTextField("Email");
        TextField password = new MyTextField("Password", true);
        TextButton login = new TextButton("LOG-IN", Assets.skin);
        Label error = new Label("", Assets.skin);
        error.setColor(Color.RED);

        table.add(label);
        table.row();
        table.add(email).width(300);
        table.row();
        table.add(password).width(300);
        table.row();
        table.add(login);
        table.row();
        table.add(error);

        login.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Nakama.login(email.getText(), password.getText(), new Nakama.LoginCallback() {
                    @Override
                    public void onSuccess(Account account) {
                        loggedIn = true;
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        error.setText(e.getMessage().split(":")[1]);
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(loggedIn){
            setScreen(new MenuScreen(game));
        }
    }
}
