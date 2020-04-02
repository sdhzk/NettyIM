package io.starsky.im.serialize;

import io.starsky.im.serialize.impl.JsonSerializer;

public interface Serializer {
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JsonSerializer();

    byte getSerializerAlogrithm();

    byte[] serialize(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
