package com.betamedia.qe.af.core.api.tp.parsers.json.deserialiser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 *         <p>
 *         Need to deserialize "result" field in CRMResponse classes
 *         as it can contain as Object as empty array "[]" inside.
 *         In last case the attempt to deserialize in object is leading to exception.
 */
public abstract class CRMDataDeserializer<T> extends JsonDeserializer<T> {

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