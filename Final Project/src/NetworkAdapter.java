import java.io.IOException;

public interface NetworkAdapter {
    public String exchange(String msg, String host, int port) throws Exception;

    public Message exchange(Message msg, String host, int port) throws Exception;
}