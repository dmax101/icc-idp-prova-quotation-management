package br.inatel.quotationmanagement.model.error;

public class ErrorModel {
    
    private String field;
    private String error;
    private String status;

    public ErrorModel() {
    }

    public ErrorModel(String field, String error, String status) {
        this.field = field;
        this.error = error;
        this.status = status;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
