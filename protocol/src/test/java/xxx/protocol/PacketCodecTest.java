package xxx.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Assert;
import org.junit.Test;
import xxx.protocol.command.impl.LoginRequestPacket;
import xxx.protocol.command.Packet;
import xxx.protocol.command.PacketCodec;
import xxx.protocol.serialize.JsonSerializer;
import xxx.protocol.serialize.Serializer;

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
    loginRequestPacket.setUserId("123");
    loginRequestPacket.setUsername("zhangsan");
    loginRequestPacket.setPassword("password");

    PacketCodec packetCodec = new PacketCodec();
    ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
    packetCodec.encode(byteBuf, loginRequestPacket);
    Packet decodedPacket = packetCodec.decode(byteBuf);

    Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
  }
}