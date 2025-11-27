/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Ognjen
 */
public class Sender {

    private Socket socket;

    public Sender() {
    }

    public Sender(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void send(Object obj) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(obj);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska pri slanju obj");
        }
    }

}
