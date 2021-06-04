package com.cryptocli;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

class _1d {
    public String volume;
    public String price_change;
    public String price_change_pct;
    public String volume_change;
    public String volume_change_pct;
    public String market_cap_change;
    public String market_cap_change_pct;
}

public class TickerResponse {
    public String id;
    public String currency;
    public String symbol;
    public String name;
    public String logo_url;
    public String status;
    public String price;
    public Date price_date;
    public Date price_timestamp;
    public String circulating_supply;
    public String max_supply;
    public String market_cap;
    public String market_cap_dominance;
    public String num_exchanges;
    public String num_pairs;
    public String num_pairs_unmapped;
    public Date first_candle;
    public Date first_trade;
    public Date first_order_book;
    public String rank;
    public String rank_delta;
    public String high;
    public Date high_timestamp;
    @JsonProperty("1d")
    public _1d _1d;
}
