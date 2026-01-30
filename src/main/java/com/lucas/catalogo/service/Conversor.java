package com.lucas.catalogo.service;

import tools.jackson.databind.ObjectMapper;

public class Conversor implements IConversor {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T conversor(String json, Class<T> classe){
        try{
            T convertidos = mapper.readValue(json, classe);
            return convertidos;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
