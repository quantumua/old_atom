package com.betamedia.qe.af.core.fwdataaccess.repository.util;

import java.io.IOException;

/**
 * Created by mbelyaev on 3/10/17.
 */
public interface ApplicationVersionHolder {
    String getVersion() throws IOException;
}