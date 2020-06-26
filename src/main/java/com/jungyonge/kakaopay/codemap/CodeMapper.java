package com.jungyonge.kakaopay.codemap;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CodeMapper {
    public Map<String, List<CodeValue>> factory = new HashMap<>();

    public List<CodeValue> toCodeValues(Class<? extends CodeModel> e){
        return Arrays
                .stream(e.getEnumConstants())
                .map(CodeValue::new)
                .collect(Collectors.toList());
    }


    public void put(String key, Class<? extends CodeModel> e){
        factory.put(key, toCodeValues(e));
    }


    public Map<String, List<CodeValue>> get(String keys){
        return Arrays
                .stream(keys.split(","))
                .collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));
    }


    public Map<String, List<CodeValue>> getAll(){
        return factory;
    }


}
