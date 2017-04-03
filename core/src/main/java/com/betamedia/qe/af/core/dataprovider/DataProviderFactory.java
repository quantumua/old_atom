package com.betamedia.qe.af.core.dataprovider;

import com.opencsv.CSVReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/**
 * Created by mbelyaev on 2/28/17.
 */
@Service
@Lazy
public class DataProviderFactory {

    public Iterator<Object[]> getData(String filename) throws IOException {
        try (Reader reader = new FileReader((String) RequestContextHolder.getRequestAttributes().getAttribute(filename, RequestAttributes.SCOPE_REQUEST))) {
            return new CSVReader(reader)
                    .readAll()
                    .stream()
                    .map(s -> (Object[]) s)
                    .iterator();
        }
    }

}
