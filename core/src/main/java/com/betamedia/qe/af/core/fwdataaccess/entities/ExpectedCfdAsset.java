package com.betamedia.qe.af.core.fwdataaccess.entities;

import com.betamedia.qe.af.core.fwdataaccess.annotations.ClasspathLocation;
import com.opencsv.bean.CsvBindByName;

/**
 * Created by mbelyaev on 4/27/17.
 */
@ClasspathLocation("/data/expectedCfdAssets.csv")
public class ExpectedCfdAsset {

    @CsvBindByName
    private String symbol;

    @CsvBindByName
    private String tooltipName;

    @CsvBindByName
    private String listBidderName;

    public String getSymbol() {
        return symbol;
    }

    public String getTooltipName() {
        return tooltipName;
    }

    public String getListBidderName() {
        return listBidderName;
    }
}
