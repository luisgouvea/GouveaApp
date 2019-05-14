package com.gouvealtda.gouvea.model;

public class ResponseAPIModel<T> {
    private T responseDetail;
    private boolean hasError;
    private String error;
    private String errorCode;

    public static final String errorBase = "Não foi possível comunicar-se com o servidor";
    public static final String codeBase = "404";

    public ResponseAPIModel() {
        this.setError(ResponseAPIModel.errorBase);
        this.setErrorCode(ResponseAPIModel.codeBase);
        this.setHasError(true);
        this.setResponseDetail(null);
    }

    public void setResponseDetail(T responseDetail) {
        this.responseDetail = responseDetail;
    }

    public T getResponseDetail() {
        return responseDetail;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public boolean isHasError() {
        return hasError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
