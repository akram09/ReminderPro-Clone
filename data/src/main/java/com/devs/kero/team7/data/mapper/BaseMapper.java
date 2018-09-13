package com.devs.kero.team7.data.mapper;

import java.text.ParseException;

public interface BaseMapper<F , T> {
       F to (T t );
       T from (F f) throws ParseException;
}
