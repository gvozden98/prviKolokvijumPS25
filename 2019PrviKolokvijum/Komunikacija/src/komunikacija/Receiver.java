/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Ognjen
 */
public class Receiver {

    Socket socket;

    public Receiver() {
    }

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public Object receive() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska pri primanju obj!");
        }
    }
}
