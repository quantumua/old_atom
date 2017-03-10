package com.betamedia.qe.af.common.holder;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mbelyaev on 3/10/17.
 */
@Service
@RequestScope
public class ApplicationVersionHolderImpl implements ApplicationVersionHolder {

    private String version = null;

    @Override
    public String getVersion() throws IOException {
        if (version == null){
            //TODO get address in runtime
            version = this.getVersion("https://eu-mit.24option.com/resources/lib/betamedia-min.js");
        }
        return version;
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
