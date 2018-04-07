package com.labuda.matt.iface;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

/**
 * Created by mateu on 04/04/2018.
 */

public interface ICanLog {

    default Logger _log() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
