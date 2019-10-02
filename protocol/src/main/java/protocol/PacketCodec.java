package protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import protocol.serialize.JsonSerializer;
import protocol.serialize.Serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xin
 * @date 2019-10-02 21:07
 */
public class PacketCodec {
  private static final int MAGIC_NUMBER = 0x7654321;
  private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
  private static final Map<Byte, Serializer> serializeMap;

  static {
    packetTypeMap = new HashMap<>();
    packetTypeMap.put(Commands.LOGIN_REQUEST, LoginRequestPacket.class);

    serializeMap = new HashMap<>();
    Serializer serializer = new JsonSerializer();
    serializeMap.put(serializer.getSerializerAlgorithm(), serializer);
  }

  public ByteBuf encode(Packet packet) {
    ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
    byte[] bytes = Serializer.DEFAULT.serialize(packet);

    byteBuf.writeInt(MAGIC_NUMBER);
    byteBuf.writeByte(packet.getVersion());
    byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
    byteBuf.writeByte(packet.getCommand());
    byteBuf.writeInt(bytes.length);
    byteBuf.writeBytes(bytes);

    return byteBuf;
  }

  public Packet decode(ByteBuf byteBuf) {
    byteBuf.skipBytes(4);
    byteBuf.skipBytes(1);

    byte serializeAlgorithm = byteBuf.readByte();

    byte command = byteBuf.readByte();

    int length = byteBuf.readInt();

    byte[] bytes = new byte[length];
    byteBuf.readBytes(bytes);

    Class<? extends Packet> requestType = getRequestType(command);
    Serializer serializer = getSerializer(serializeAlgorithm);

    if (requestType != null && serializer != null) {
      return serializer.deserialize(requestType, bytes);
    }

    return null;
  }

  private Serializer getSerializer(byte serializeAlgorithm) {
    return serializeMap.get(serializeAlgorithm);
  }

  private Class<? extends Packet> getRequestType(byte command) {
    return packetTypeMap.get(command);
  }
}
