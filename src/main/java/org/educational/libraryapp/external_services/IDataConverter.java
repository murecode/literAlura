package org.educational.libraryapp.external_services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IDataConverter {
  <T> T jsonToClass(String json, Class<T> tClass) throws JsonProcessingException;
}
