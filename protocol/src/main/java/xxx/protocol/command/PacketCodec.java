package xxx.protocol.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import xxx.protocol.command.impl.LoginRequestPacket;
import xxx.protocol.command.impl.LoginResponsePacket;
import xxx.protocol.command.impl.MessageRequestPacket;
import xxx.protocol.command.impl.MessageResponsePacket;
import xxx.protocol.serialize.JsonSerializer;
import xxx.protocol.serialize.Serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xin
 * @date 2019-10-02 21:07
 * IM应用数据包的编解码类，和Packet搭配使用。
 */
public class PacketCodec {
  public static final PacketCodec INSTANCE = new PacketCodec();
  private static final int MAGIC_NUMBER = 0x7654321;
  private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
  private static final Map<Byte, Serializer> serializeMap;

  static {
    packetTypeMap = new HashMap<>();
    packetTypeMap.put(Commands.LOGIN_REQUEST, LoginRequestPacket.class);
    packetTypeMap.put(Commands.LOGIN_RESPONSE, LoginResponsePacket.class);
    packetTypeMap.put(Commands.MESSAGE_REQUEST, MessageRequestPacket.class);
    packetTypeMap.put(Commands.MESSAGE_RESPONSE, MessageResponsePacket.class);

    serializeMap = new HashMap<>();
    Serializer serializer = new JsonSerializer();
    serializeMap.put(serializer.getSerializerAlgorithm(), serializer);
  }

  /**
   * 命令序列化
   */
  public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
    ByteBuf byteBuf = byteBufAllocator.ioBuffer();
    // 将消息内容直接用Json序列化成字节
    byte[] bytes = Serializer.DEFAULT.serialize(packet);

    // 按魔数，版本号，序列化方式，指令，命令消息长度，消息内容来组织字节数据。
    byteBuf.writeInt(MAGIC_NUMBER);
    byteBuf.writeByte(packet.getVersion());
    byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
    byteBuf.writeByte(packet.getCommand());
    byteBuf.writeInt(bytes.length);
    byteBuf.writeBytes(bytes);

    return byteBuf;
  }

  /**
   * 命令反序列化
   */
  public Packet decode(ByteBuf byteBuf) {
    // 按魔数，版本号，序列化方式，指令，命令消息长度，消息内容来解析字节数据。
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

  /**
   * 从消息的序列化方式获取具体的Serializer，在这里只有JsonSerializer一种
   */
  private Serializer getSerializer(byte serializeAlgorithm) {
    return serializeMap.get(serializeAlgorithm);
  }

  /**
   * 从消息的命令字段获取具体的packet类
   */
  private Class<? extends Packet> getRequestType(byte command) {
    return packetTypeMap.get(command);
  }
}
