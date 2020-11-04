package com.smart.transaction.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Cars {
    /**
     *
     */
    private Integer carsId;

    /**
     *
     */
    private Integer productId;

    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private Boolean status;

    /**
     *
     */
    private Date createDate;
}

