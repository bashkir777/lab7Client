package Modules;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;

public class Receive {
    public static String receive(DatagramChannel dc) throws IOException {
        byte[] serverAnswer = new byte[4096];
        ByteBuffer buffer= ByteBuffer.wrap(serverAnswer);
        dc.socket().setSoTimeout(300);
        dc.read(buffer);
        buffer.flip();
        ArrayList<Byte> ab = new ArrayList <>();
        while (buffer.hasRemaining())
        {
            byte b =buffer.get();
            ab.add(b);
        }

        byte[] outDataForEncoder = new byte[ab.size()];
        for(int i=0; i<ab.size();i++){
            outDataForEncoder [i]=ab.get(i);
        }
        buffer.clear();
        return new String(outDataForEncoder);
    }
}
