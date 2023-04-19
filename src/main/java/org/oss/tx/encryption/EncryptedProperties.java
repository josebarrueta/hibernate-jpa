package org.oss.tx.encryption;

public interface EncryptedProperties<T> {

    T getProperties();

    void setProperties(T properties);

}
