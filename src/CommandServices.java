import java.io.ObjectInputStream;

public class CommandServices {

    private static ObjectData decision;

    public static void decision(int i) {
        ObjectData recive = new ObjectData();
        decision = DataReciveRepository.getInstance().getObjectData("zdanek");
        if (decision.getData().equals("exit")) {
            System.out.println("Wychodizmy nauuura");
            recive.setData("kończe");
            recive.setDataType("serwer");

        } else {

            recive.setData("czekam"+i);
            recive.setDataType("serwer");
        }
        //System.out.println(decision.getData());
        DataSendRepository.getInstance().addDataSend(recive);
    }
}
