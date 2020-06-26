package com.jungyonge.kakaopay.codemap;

import lombok.Data;

@Data
public class CodeValue {
    private String key;
    private String value;

    public CodeValue(CodeModel codeModel) {
        key = codeModel.getKey();
        value = codeModel.getValue();
    }

}
