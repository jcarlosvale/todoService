package com.study.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDtoTest {

    @Test
    void testConstructor (){
        //given
         int codigoErro = 123;
         String mensagem = "test";

         //when
        var dto = new ErrorDto(codigoErro, mensagem);

        //then
        assertEquals(codigoErro, dto.getCodigoErro());
        assertEquals(mensagem, dto.getMensagem());
    }
}