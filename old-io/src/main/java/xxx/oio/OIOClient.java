package xxx.oio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author xin
 * @date 2019-10-01 13:33
 */
public class OIOClient {
  public static void main(String[] args) {
    new Thread(() -> {
      try {
        Socket socket = new Socket("127.0.0.1", 8000);
        while (true) {
          try {
            socket.getOutputStream().write((new Date() + ": hello world!").getBytes());
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }
}
