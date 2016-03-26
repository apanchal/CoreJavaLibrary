package com.inclever.library.daoblend.core.domain;

import java.io.Serializable;

public interface IPersistable<ID extends Serializable> extends Serializable {

    /**
     * 
     * @return
     */
    ID getId();
}
