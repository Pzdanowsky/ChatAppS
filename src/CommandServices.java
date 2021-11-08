import java.io.ObjectInputStream;

public class CommandServices {

    private static ObjectData decision;

    public static void decision(User user) {
        ObjectData send = new ObjectData();
        decision = DataReciveRepository.getInstance().getObjectData(user.getUsername());
        if (decision.getData().equals("exit")) {
            System.out.println("Wychodizmy nauuura");
            send.setData("kończe");
            send.setDataType("serwer");

        }
        send.setData(decision.getData());
        //System.out.println(decision.getData());
        DataSendRepository.getInstance().addDataSend(send);
    }
}
