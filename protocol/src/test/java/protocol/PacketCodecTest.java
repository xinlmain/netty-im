package protocol;

import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;
import protocol.serialize.JsonSerializer;
import protocol.serialize.Serializer;

/**
 * @author xin
 * @date 2019-10-02 21:28
 */
public class PacketCodecTest {

  @Test
  public void encode() {
    Serializer serializer = new JsonSerializer();
    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

    loginRequestPacket.setVersion(((byte) 1));
    loginRequestPacket.setUserId(123);
    loginRequestPacket.setUsername("zhangsan");
    loginRequestPacket.setPassword("password");

    PacketCodec packetCodec = new PacketCodec();
    ByteBuf byteBuf = packetCodec.encode(loginRequestPacket);
    Packet decodedPacket = packetCodec.decode(byteBuf);

    Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
  }
}