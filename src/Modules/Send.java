package Modules;

import CommandsManagement.Commands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Send {
    public static void send(Commands cmd, SocketAddress addr, DatagramChannel dc) throws IOException {
        byte[] commandBytes = Commands.serializeCommand(cmd);
        ByteBuffer buf1= ByteBuffer.wrap(commandBytes);
        dc.send(buf1, addr);
    }
}
