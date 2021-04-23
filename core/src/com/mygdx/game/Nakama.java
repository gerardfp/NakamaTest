package com.mygdx.game;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.heroiclabs.nakama.Client;
import com.heroiclabs.nakama.DefaultClient;
import com.heroiclabs.nakama.SocketClient;
import com.heroiclabs.nakama.api.Account;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Nakama {
    static private final Client client = new DefaultClient("defaultkey", "127.0.0.1", 7349, false);;
    static ExecutorService executor = Executors.newSingleThreadExecutor();
    static SocketClient socket;
    static Account account;

    static {
        socket = client.createSocket();
    }

    interface LoginCallback {
        void onSuccess(Account account);
        void onFailure(Throwable e);
    }

    static void login(String email, String password, LoginCallback callback){
        Futures.addCallback(
                Futures.transformAsync(client.authenticateEmail(email, password), session -> client.getAccount(session), executor),
                new FutureCallback<Account>() {
                    @Override
                    public void onSuccess(Account account) {
                        Nakama.account = account;
                        callback.onSuccess(account);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        callback.onFailure(e);
                    }
                },
                executor
        );
    }
}