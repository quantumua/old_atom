package com.betamedia.qe.af.core.fwdataaccess.repository.util.tp;

import com.betamedia.qe.af.core.fwdataaccess.repository.util.ApplicationVersionHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * Created by mbelyaev on 3/10/17.
 */
@Service
@Scope(value = SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
public class TPApplicationVersionHolderImpl implements ApplicationVersionHolder {

    public static final String VERSION_SOURCE = "http://192.168.5.47:8080/24option-eu/resources/lib/betamedia-min.js";

    @Override
    public String getVersion() throws IOException {
        return getVersion(VERSION_SOURCE);
    }


    private String getVersion(String address) throws IOException {
        //language=RegExp
        //String jsAddress = getMatch(address, "\"(http[^<>]+?betamedia-min.js[^<>]+?)\"");
        return getMatch(address, "Build (\\d+)");
    }

    private String getMatch(String address, String regex) throws IOException {
        URL url = new URL(address);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            Pattern pattern = Pattern.compile(regex);
            while ((inputLine = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(inputLine);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        }
        return null;
    }

}
