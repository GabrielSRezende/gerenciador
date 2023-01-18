package com.attornatus.gerenciador.mapper;

import java.text.ParseException;

public interface Mapper<T, U> {

    public U map(T t) throws ParseException;

}
