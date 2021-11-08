import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {


    private static final int PORT = 1909;

    private static ArrayList<ClientHandler> clientsList = new ArrayList<>();



    public static void main(String[] args){

        System.out.println("Server started");


        try{
            ServerSocket listener =  new ServerSocket(PORT);

            while(true){
                Socket client = listener.accept();
                System.out.println("Akcept clienta");
                ClientHandler clinetThread = new ClientHandler(client);
                clinetThread.start();
            }



        }catch(Exception ex){
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
        }





    }
}
