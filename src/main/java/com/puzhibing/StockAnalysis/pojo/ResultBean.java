package com.puzhibing.StockAnalysis.pojo;

/**
 * 定义统一的返回对象
 */
public class ResultBean<T> {

    private boolean b;

    private T result;

    public void setB(boolean b) {
        this.b = b;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean getB() {
        return b;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "NotesResponse{" +
                "b=" + b +
                ", result=" + result +
                '}';
    }
}
