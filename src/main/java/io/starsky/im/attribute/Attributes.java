package io.starsky.im.attribute;

import io.netty.util.AttributeKey;
import io.starsky.im.session.Session;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
