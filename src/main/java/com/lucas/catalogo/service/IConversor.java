package com.lucas.catalogo.service;

public interface IConversor {
    
    <T> T conversor(String json, Class<T> classe);
}
