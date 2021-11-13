package Services;

import Objects.ObjectData;
import Objects.User;
import Repositories.DataReciveRepository;
import Repositories.DataSendRepository;

public class CommandManager {

    private static ObjectData objectDataRecive;
    private static RequestStrategy strategy;

    public static void manage(User user){

        objectDataRecive = DataReciveRepository.getInstance().getObjectData(user.getSesionNumber());
        if(objectDataRecive == null)
        {
            System.out.println("Pakiet przyszedł pusty");
        }else{

            switch(objectDataRecive.getCommand()){

                case "00001":
                   strategy = new LoginRequestService();
                    System.out.println("Logowanie");
                    break;

                case "00010":strategy = new RegisterRequestService();
                    break;

                case "00011":
                    // Zapomniane hasło
                    break;

                case "00100":
                 //   strategy = new MessageRequestService();
                    break;

                case "00101":
                    // Lista znajomych - sprawdzanie aktywnosci
                    break;

                case "00110":
                    // Wysyłanie wiadomości
                    break;

                case "00111":
                    // Wysyłanie pliku
                    break;

                case "01000":
                    // Tworzenie chatu
                    break;

                case "01001":
                    //Pobranie wiadomości dla czatu
                    break;

                case "01010":
                    //Pobranie pliku
                    break;

                case "01011":
                    //Pobranie starszy wiadomości
                    break;

                case "01100":
                    // Potwierdzenie online
                    break;



            }

            DataSendRepository.getInstance().addDataSend(strategy.processObjectData(user,objectDataRecive));
        }
    }
}
