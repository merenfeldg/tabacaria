package br.com.tabacetabacaria.service.exceptions;

public class DataIntegratyViolationException extends RuntimeException {
    public DataIntegratyViolationException(String msg){
        super(msg);
    }
}
