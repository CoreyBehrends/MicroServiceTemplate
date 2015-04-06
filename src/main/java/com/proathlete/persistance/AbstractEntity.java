package com.proathlete.persistance;

import java.io.Serializable;

/**
 * Created by cbehrends on 10/7/14.
 */
public interface AbstractEntity extends Serializable {
    Long getId();

    void setId(Long id);
}
