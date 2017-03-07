package com.betamedia.qe.af.components;

import com.opencsv.CSVReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by mbelyaev on 2/28/17.
 */
@Service
@Lazy
public class DataProviderFactory {

    public Iterator<Object[]> getData(String filename) throws IOException {
        return new CSVReader(new FileReader((String) RequestContextHolder.getRequestAttributes().getAttribute(filename, RequestAttributes.SCOPE_REQUEST)))
                .readAll()
                .stream()
                .map(s -> (Object[]) s)
                .iterator();
    }

}
