package protocol.serialize;

/**
 * @author xin
 * @date 2019-10-02 20:47
 */
public interface Serializer {
  JsonSerializer DEFAULT = new JsonSerializer();

  /**
   * 序列化算法
   */
  byte getSerializerAlgorithm();

  /**
   * 序列化
   */
  byte[] serialize(Object object);

  /**
   * 反序列化
   */
  <T> T deserialize(Class<T> clazz, byte[] bytes);
}
