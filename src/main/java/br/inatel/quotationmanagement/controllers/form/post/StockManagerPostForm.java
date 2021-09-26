package br.inatel.quotationmanagement.controllers.form.post;

public class StockManagerPostForm {
    
    private String host;
    private int port;

    public StockManagerPostForm() {
    }

    public StockManagerPostForm(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "{" +
            " host='" + getHost() + "'" +
            ", port='" + getPort() + "'" +
            "}";
    }

}
