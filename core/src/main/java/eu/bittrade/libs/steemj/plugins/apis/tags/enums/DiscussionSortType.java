package eu.bittrade.libs.steemj.plugins.apis.tags.enums;

/**
 * An enumeration representing all sort types for discussions.
 * 
 * @author <a href="http://steemit.com/@dez1337">dez1337</a>
 */
public enum DiscussionSortType {
    /** Get trending discussions first. */
    GET_DISCUSSIONS_BY_TRENDING, 
    /** Get discussions based on their creation date. */
    GET_DISCUSSIONS_BY_CREATED, 
    GET_DISCUSSIONS_BY_ACTIVE, 
    GET_DISCUSSIONS_BY_CASHOUT, 
    GET_DISCUSSIONS_BY_VOTES, 
    GET_DISCUSSIONS_BY_CHILDREN, 
    GET_DISCUSSIONS_BY_HOT, 
    GET_DISCUSSIONS_BY_FEED, 
    GET_DISCUSSIONS_BY_BLOG, 
    GET_DISCUSSIONS_BY_COMMENTS, 
    GET_DISCUSSIONS_BY_PROMOTED;
}
