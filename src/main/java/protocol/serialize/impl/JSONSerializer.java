package protocol.serialize.impl;

import com.alibaba.fastjson.JSON;
import protocol.serialize.Serializer;
import protocol.serialize.SerializerAlgorithm;

/**
 * 具体的json实现序列化
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
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
