package br.com.habeis.api.socket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/update")
public class Socket {

    private static final Set<Session> SESSION = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public Session onOpen(Session session) {
        SESSION.add(session);
        //sendId(session.getId());

        System.out.println("Conectou");

        return session;
    }

    @OnClose
    public void onClose(Session session) {
        SESSION.remove(session);
        System.out.println("Saiu");
    }

    @OnMessage
    public void onMessage(String json) throws Exception {
        System.out.println("Enviou msg");
    }

}
