package protocol.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author xin
 * @date 2019-10-02 20:52
 */
public class JsonSerializer implements Serializer{

  @Override
  public byte getSerializerAlgorithm() {
    return SerializerAlgorithms.JSON;
  }

  @Override
  public byte[] serialize(Object object) {
    return JSON.toJSONBytes(object);
  }

  @Override
  public <T> T deserialize(Class<T> clazz, byte[] bytes) {
    return JSON.parseObject(bytes, clazz);
  }
}
