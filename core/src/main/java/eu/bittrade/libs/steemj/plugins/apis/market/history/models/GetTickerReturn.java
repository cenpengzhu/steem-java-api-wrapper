package eu.bittrade.libs.steemj.plugins.apis.market.history.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.bittrade.libs.steemj.base.models.Asset;

/**
 * This class represents a Steem "get_ticker_return" object of the
 * "market_history_plugin".
 * 
 * @author <a href="http://steemit.com/@dez1337">dez1337</a>
 */
public class GetTickerReturn {
    @JsonProperty("latest")
    private double latest;
    @JsonProperty("lowest_ask")
    private double lowestAsk;
    @JsonProperty("highest_bid")
    private double highestBid;
    @JsonProperty("percent_change")
    private double percentChange;
    @JsonProperty("steem_volume")
    private Asset steemVolume;
    @JsonProperty("sbd_volume")
    private Asset sbdVolume;

    /**
     * This object is only used to wrap the JSON response in a POJO, so
     * therefore this class should not be instantiated.
     */
    protected GetTickerReturn() {
    }

    /**
     * @return the latest
     */
    public double getLatest() {
        return latest;
    }

    /**
     * @return the lowestAsk
     */
    public double getLowestAsk() {
        return lowestAsk;
    }

    /**
     * @return the highestBid
     */
    public double getHighestBid() {
        return highestBid;
    }

    /**
     * @return the percentChange
     */
    public double getPercentChange() {
        return percentChange;
    }

    /**
     * @return the steemVolume
     */
    public Asset getSteemVolume() {
        return steemVolume;
    }

    /**
     * @return the sbdVolume
     */
    public Asset getSbdVolume() {
        return sbdVolume;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
