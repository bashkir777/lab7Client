package CommandsManagement;

import java.io.*;


public class Commands implements Serializable {
    private static final long serialVersionUID = -3982430373387432443L;
    private CommandsEnum type;
    private Object[] attr;
    public Commands(CommandsEnum type){
        this.type = type;
    }
    public Commands(CommandsEnum type, Object[] attr){
        this.type = type;
        this.attr = attr;
    }
    public Object[] getAttr() {
        return attr;
    }

    public void setAttr(Object[] attr) {
        this.attr = attr;
    }
    public static byte[] serializeCommand(Commands cmd) throws IOException {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)){
            oos.writeObject(cmd);
            return bos.toByteArray();
        }
    }
    public static Commands deSerializeCommand(byte[] commandArr){
        try(ByteArrayInputStream bis = new ByteArrayInputStream(commandArr);
            ObjectInputStream ois = new ObjectInputStream(bis)
        ) {
            return (Commands) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public CommandsEnum getType() {
        return type;
    }
}
