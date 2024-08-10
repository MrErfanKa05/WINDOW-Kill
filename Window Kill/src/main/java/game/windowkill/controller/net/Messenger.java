package game.windowkill.controller.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Messenger {
    private Scanner scanner;
    private PrintWriter writer;
    private Socket socket;

    public Messenger(Socket socket) throws IOException {
        this.socket = socket;
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream());
    }

    public Messenger(InputStream inputStream, OutputStream outputStream){
        scanner = new Scanner(inputStream);
        writer = new PrintWriter(outputStream);
    }

    public String read(){
        return scanner.next();
    }

    public boolean hasNext(){
        return scanner.hasNext();
    }

    public void write(String s){
        writer.println(s);
        writer.flush();
    }

    public void close() {
        writer.close();
        scanner.close();
    }
}
