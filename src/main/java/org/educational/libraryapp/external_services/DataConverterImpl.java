package org.educational.libraryapp.external_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverterImpl implements IDataConverter{

  ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public <T> T jsonToClass(String json, Class<T> tClass) throws JsonProcessingException {
    return objectMapper.readValue(json, tClass);
  }
}
