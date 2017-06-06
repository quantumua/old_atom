package com.betamedia.atom.core.fwdataaccess.entities;

import com.betamedia.atom.core.fwdataaccess.annotations.ClasspathLocation;
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

    @Override
    public String toString() {
        return "ExpectedCfdAsset{" +
                "symbol='" + symbol + '\'' +
                ", tooltipName='" + tooltipName + '\'' +
                ", listBidderName='" + listBidderName + '\'' +
                '}';
    }
}
