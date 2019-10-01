package xxx.oio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xin
 * @date 2019-10-01 13:26
 * 国庆快乐！
 */
public class OIOServer {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8000);

    new Thread(() -> {
      while (true) {
        try {
          // (1) 阻塞方法获取新的连接
          Socket socket = serverSocket.accept();

          // (2) 每个新的连接都创建一个新线程，负责读取数据
          new Thread(() -> {
            try {
              int len;
              byte[] data = new byte[1024];
              InputStream inputStream = socket.getInputStream();

              // (3) 按字节流方式读取数据
              while ((len = inputStream.read(data)) != -1) {
                System.out.println(new String(data, 0, len));
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }).start();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
