package org.oss.tx.utils;

import jakarta.persistence.AttributeConverter;
import org.oss.tx.dao.Credentials;

public class JsonConverter implements AttributeConverter<Credentials, String> {

    @Override
    public String convertToDatabaseColumn(Credentials credentials) {
        return null;
    }

    @Override
    public Credentials convertToEntityAttribute(String s) {
        return null;
    }
}
