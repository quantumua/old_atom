package com.betamedia.atom.core.api.tp.parsers.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

/**
 * This class is provides base template functionality to deserialize CRMResponse results.
 * Its concrete subclasses should be used via the {@link JsonDeserialize} annotation.
 *
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 *         <p>
 *         Need to deserialize "result" field in CRMResponse classes
 *         as it can contain as Object as empty array "[]" inside.
 *         In last case the attempt to deserialize in object is leading to exception.
 */
public abstract class CRMDataDeserializer<T> extends JsonDeserializer<T> {

    /**
     * Deserialization template method entry point.
     * It should be called by Jackson during object deserialization.
     */
    @Override
    public T deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        if (node.isArray()) {
            return null;
        }
        return deserialize(node);
    }

    protected abstract T deserialize(JsonNode node) throws IOException;
}
