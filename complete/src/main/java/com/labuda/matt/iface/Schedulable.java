package com.labuda.matt.iface;

import java.util.Map;

/**
 * Created by mateu on 08/04/2018.
 */
public interface Schedulable extends ICanLog{

    public Schedulable run(Map<String,String> params);


}
