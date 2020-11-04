package com.smart.transaction.common;

import lombok.Builder;
import lombok.Data;

/**
 * @Author Emilia
 * @Since 2020.11.04 20:11
 */
@Data
@Builder
public class RequestEntity<T> {
    private int status;
    private String msg;
    private T data;

    public static <T> RequestEntity<T> success(T data) {
        return RequestEntity
                .<T>builder()
                .status(200)
                .msg("success")
                .data(data)
                .build();
    }

    public static <T> RequestEntity<T> error() {
        return RequestEntity
                .<T>builder()
                .status(404)
                .msg("error")
                .build();
    }
}
