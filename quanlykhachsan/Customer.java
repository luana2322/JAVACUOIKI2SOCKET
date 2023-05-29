package quanlykhachsan;
import java.io.IOException;
import java.net.Socket;
public class Customer{
    String name;
    Socket socket;    
  
    public Customer(){
    }
    public Customer(String name, Socket socket) throws IOException{
        this.name = name;
        this.socket = socket;

    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
  
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    } 
}