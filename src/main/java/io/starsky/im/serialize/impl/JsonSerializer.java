package io.starsky.im.serialize.impl;

import com.alibaba.fastjson.JSON;
import io.starsky.im.serialize.Serializer;
import io.starsky.im.serialize.SerializerAlogrithm;

public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
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
