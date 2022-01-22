package org.nisvarthafoundation.nisvarthaportal.businessservices.NFStudentBusinessService.domain.model.valueobjects;

import java.io.IOException;
import org.bson.BsonBinarySubType;

import org.bson.types.Binary;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

public class BinaryDeserializer extends StdDeserializer<Binary> {

    public BinaryDeserializer() { 
        this(null); 
    } 

    public BinaryDeserializer(Class<?> vc) { 
        super(vc); 
    }

    @Override
    public Binary deserialize(JsonParser jp, DeserializationContext ctxt) 
      throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        byte type = ((byte) ((IntNode) node.get("type")).numberValue().byteValue());
        	
        byte[] data = node.get("data").binaryValue();


        return new Binary(type, data);
    }
}