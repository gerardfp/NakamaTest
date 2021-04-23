package com.mygdx.game;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.heroiclabs.nakama.Error;
import com.heroiclabs.nakama.*;
import com.heroiclabs.nakama.api.Account;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Nakama {
    static private final Client client = new DefaultClient("defaultkey", "127.0.0.1", 7349, false);;
    static ExecutorService executor = Executors.newSingleThreadExecutor();
    static Session session;
    static Account account;
    static SocketClient socket;

    static {
        socket = client.createSocket();
    }


    interface LoginCallback {
        void onSuccess(Account account);
        void onFailure(Throwable e);
    }

    static <T> void addCallback(ListenableFuture<T> lf, FutureCallback<T> fc){
        Futures.addCallback(lf, fc, executor);
    }

    static void login(String email, String password, LoginCallback callback){

        addCallback(client.authenticateEmail(email, password), new FutureCallback<Session>() {
            @Override
            public void onSuccess(@NullableDecl Session session) {

                Nakama.session = session;
                
               addCallback(client.getAccount(session), new FutureCallback<Account>() {
                   @Override
                   public void onSuccess(@NullableDecl Account account) {
                       Nakama.account = account;
                       callback.onSuccess(account);
                   }

                   @Override
                   public void onFailure(Throwable t) {
                        callback.onFailure(t);
                   }
               });

               socket.connect(session, new SocketListener() {
                   @Override
                   public void onDisconnect(Throwable t) {
                       System.out.println("SocketListener::onDisconnect: " + t.getMessage());
                   }

                   @Override
                   public void onError(Error error) {
                       System.out.println("SocketListener::onError: " + error.getMessage());

                   }

                   @Override
                   public void onChannelMessage(com.heroiclabs.nakama.api.ChannelMessage message) {
                       System.out.println("SocketListener::onChannelMessage: " + message.toString());
                   }

                   @Override
                   public void onChannelPresence(ChannelPresenceEvent presence) {
                       System.out.println("SocketListener::onChannelPresence: " + presence.toString());

                   }

                   @Override
                   public void onMatchmakerMatched(MatchmakerMatched matched) {
                       System.out.println("SocketListener::onMatchmakerMatched: " + matched.toString());

                   }

                   @Override
                   public void onMatchData(MatchData matchData) {
                       System.out.println("SocketListener::onMatchData: "  + matchData.toString());
                   }

                   @Override
                   public void onMatchPresence(MatchPresenceEvent matchPresence) {
                       System.out.println("SocketListener::onMatchPresence: "  + matchPresence.toString());
                   }

                   @Override
                   public void onNotifications(com.heroiclabs.nakama.api.NotificationList notifications) {
                       System.out.println("SocketListener::onNotifications: "  + notifications.toString());

                   }

                   @Override
                   public void onStatusPresence(StatusPresenceEvent presence) {
                       System.out.println("SocketListener::onStatusPresence: "  + presence.toString());

                   }

                   @Override
                   public void onStreamPresence(StreamPresenceEvent presence) {
                       System.out.println("SocketListener::onStreamPresence: "  + presence.toString());

                   }

                   @Override
                   public void onStreamData(StreamData data) {
                       System.out.println("SocketListener::onStreamData: "  + data.toString());
                   }
               });
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    static void createMatch(FutureCallback<Match> callback){
        addCallback(socket.createMatch(), callback);
    }

    public static void writeAlgo(){
        client.writeStorageObjects(session, new StorageObjectWrite("objetos", "monedas", "{\"oro\": 24}", PermissionRead.OWNER_READ, PermissionWrite.OWNER_WRITE));

//        addCallback(client.writeStorageObjects(session, new StorageObjectWrite("objetos", "monedas", "{\"oro\": 24}", PermissionRead.OWNER_READ, PermissionWrite.OWNER_WRITE)), new FutureCallback<StorageObjectAcks>() {
//            @Override
//            public void onSuccess(@NullableDecl StorageObjectAcks result) {
//                System.out.println("WRITTEN OBJECT " + result.toString());
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.println("ERROR WRITING STORAGE OBJECT " + t.getMessage());
//            }
//        });
    }


    public static void addPresenceListener() {

    }
}