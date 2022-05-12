package com.study.service;

import java.io.IOException;

public class Excecoes {

    public static void main(String[] args) {
        System.out.println("INICIO");
        try {
            excecao();
        } catch (IOException e) {
            System.out.println("TRATAMENTO EXCECAO IO");
        } catch (RuntimeException e) {
            System.out.println("TRATAMENTO RUNTIME EXCEPTION");
        }
        System.out.println("FIM");
    }

    public static void excecao() throws IOException{
        throw new IOException("Alguma excecao");
    }
}
