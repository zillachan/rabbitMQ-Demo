package com.unimedsci.mq.common;

/**
 * 接口反馈基本模型
 *
 * @param <T>
 */
public class ResponseModel<T> {
    /**
     * 状态码，采用HTTP的状态码返回
     */
    private int status;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 响应的具体业务内容
     */
    private T data;

    public ResponseModel() {
        this.status = 200;
        this.msg = "success";
    }

    public ResponseModel(String msg) {
        this.status = 200;
        this.msg = msg;
    }

    public ResponseModel(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseModel(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
