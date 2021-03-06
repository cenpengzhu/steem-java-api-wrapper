package eu.bittrade.libs.steemj.plugins.apis.tags;

import java.util.List;

import eu.bittrade.libs.steemj.communication.CommunicationHandler;
import eu.bittrade.libs.steemj.communication.jrpc.JsonRPCRequest;
import eu.bittrade.libs.steemj.enums.RequestMethods;
import eu.bittrade.libs.steemj.enums.SteemApiType;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import eu.bittrade.libs.steemj.plugins.apis.tags.enums.DiscussionSortType;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.Discussion;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.DiscussionQuery;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.DiscussionQueryResult;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.GetActiveVotesArgs;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.GetActiveVotesReturn;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.GetDiscussionArgs;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.GetDiscussionsByAuthorBeforeDateArgs;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.GetRepliesByLastUpdateArgs;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.GetTagsUsedByAuthorArgs;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.GetTagsUsedByAuthorReturn;
import eu.bittrade.libs.steemj.plugins.apis.tags.models.Tag;

/**
 * This class implements the tags api.
 * 
 * @author <a href="http://steemit.com/@dez1337">dez1337</a>
 */
public class TagsApi {
    /** Add a private constructor to hide the implicit public one. */
    private TagsApi() {
    }

    /**
     * Use this method to get detailed values and metrics for tags. The methods
     * accepts a String as a search pattern and a number to limit the results.
     * 
     * <b>Example</b>
     * <p>
     * <code>getTrendingTags(communicationHandler, "steem", 2);</code> <br>
     * Will return two tags whose name has the biggest match with the String
     * "steem". An example response could contain the metrics and values for the
     * tag "steem" and "steemit", while "steem" would be the first entry in the
     * list as it has a bigger match than "steemit".
     * </p>
     * 
     * @param communicationHandler
     *            A
     *            {@link eu.bittrade.libs.steemj.communication.CommunicationHandler
     *            CommunicationHandler} instance that should be used to send the
     *            request.
     * @param firstTagPattern
     *            The search pattern used to build the resulting list of tags.
     * @param limit
     *            The maximum number of results.
     * @return A list of the tags. The first entry in the list is the tag that
     *         has the biggest match with the <code>firstTagPattern</code>.
     *         while the last tag in the last has the smallest match.
     * @throws SteemCommunicationException
     *             <ul>
     *             <li>If the server was not able to answer the request in the
     *             given time (see
     *             {@link eu.bittrade.libs.steemj.configuration.SteemJConfig#setResponseTimeout(int)
     *             setResponseTimeout}).</li>
     *             <li>If there is a connection problem.</li>
     *             </ul>
     * @throws SteemResponseException
     *             <ul>
     *             <li>If the SteemJ is unable to transform the JSON response
     *             into a Java object.</li>
     *             <li>If the Server returned an error object.</li>
     *             </ul>
     */
    public static List<Tag> getTrendingTags(CommunicationHandler communicationHandler, String firstTagPattern,
            int limit) throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_TRENDING_TAGS);
        requestObject.setSteemApi(SteemApiType.DATABASE_API);
        String[] parameters = { firstTagPattern, String.valueOf(limit) };
        requestObject.setAdditionalParameters(parameters);

        return communicationHandler.performRequest(requestObject, Tag.class);
    }

    /**
     * 
     * @param communicationHandler
     * @param getTagsUsedByAuthorArgs
     * @return
     * @throws SteemCommunicationException
     * @throws SteemResponseException
     */
    public static GetTagsUsedByAuthorReturn getTagsUsedByAuthor(CommunicationHandler communicationHandler,
            GetTagsUsedByAuthorArgs getTagsUsedByAuthorArgs)
            throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_CONTENT_REPLIES);
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(getTagsUsedByAuthorArgs);

        return communicationHandler.performRequest(requestObject, GetTagsUsedByAuthorReturn.class).get(0);
    }

    /**
     * 
     * @param communicationHandler
     * @param getDiscussionArgs
     * @return
     * @throws SteemCommunicationException
     * @throws SteemResponseException
     */
    public static Discussion getDiscussion(CommunicationHandler communicationHandler,
            GetDiscussionArgs getDiscussionArgs) throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_DISCUSSION);
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(getDiscussionArgs);

        return communicationHandler.performRequest(requestObject, Discussion.class).get(0);
    }

    /**
     * Get the replies of a specific post.
     * 
     * @param author
     *            The authors name.
     * @param permlink
     *            The permlink of the article.
     * @return A list of discussions or null if the post has no replies.
     * @throws SteemCommunicationException
     *             <ul>
     *             <li>If the server was not able to answer the request in the
     *             given time (see
     *             {@link eu.bittrade.libs.steemj.configuration.SteemJConfig#setResponseTimeout(int)
     *             setResponseTimeout}).</li>
     *             <li>If there is a connection problem.</li>
     *             </ul>
     * @throws SteemResponseException
     *             <ul>
     *             <li>If the SteemJ is unable to transform the JSON response
     *             into a Java object.</li>
     *             <li>If the Server returned an error object.</li>
     *             </ul>
     */
    public DiscussionQueryResult getContentReplies(CommunicationHandler communicationHandler,
            DiscussionQuery discussionQuery) throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_CONTENT_REPLIES);
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(discussionQuery);

        return communicationHandler.performRequest(requestObject, DiscussionQueryResult.class).get(0);
    }

    /**
     * 
     * @param communicationHandler
     * @param discussionQuery
     * @return
     * @throws SteemCommunicationException
     * @throws SteemResponseException
     */
    public static DiscussionQueryResult getPostDiscussionsByPayout(CommunicationHandler communicationHandler,
            DiscussionQuery discussionQuery) throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_POST_DISCUSSIONS_BY_PAYOUT);
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(discussionQuery);

        return communicationHandler.performRequest(requestObject, DiscussionQueryResult.class).get(0);
    }

    /**
     * 
     * @param communicationHandler
     * @param discussionQuery
     * @return
     * @throws SteemCommunicationException
     * @throws SteemResponseException
     */
    public static DiscussionQueryResult getCommentDiscussionsByPayout(CommunicationHandler communicationHandler,
            DiscussionQuery discussionQuery) throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_COMMENT_DISCUSSIONS_BY_PAYOUT);
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(discussionQuery);

        return communicationHandler.performRequest(requestObject, DiscussionQueryResult.class).get(0);
    }

    /**
     * Get active discussions for a specified tag.
     * 
     * @param discussionQuery
     *            A query defining specific search parameters.
     * @param sortBy
     *            Choose the method used for sorting the results.
     * @return A list of discussions matching the given conditions.
     * @throws SteemCommunicationException
     *             <ul>
     *             <li>If the server was not able to answer the request in the
     *             given time (see
     *             {@link eu.bittrade.libs.steemj.configuration.SteemJConfig#setResponseTimeout(int)
     *             setResponseTimeout}).</li>
     *             <li>If there is a connection problem.</li>
     *             </ul>
     * @throws SteemResponseException
     *             <ul>
     *             <li>If the SteemJ is unable to transform the JSON response
     *             into a Java object.</li>
     *             <li>If the Server returned an error object.</li>
     *             </ul>
     */
    public List<Discussion> getDiscussionsBy(CommunicationHandler communicationHandler, DiscussionQuery discussionQuery,
            DiscussionSortType sortBy) throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.valueOf(sortBy.name()));
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(discussionQuery);

        return communicationHandler.performRequest(requestObject, Discussion.class);
    }

    /**
     * /** Get a list of Content starting from the given post of the given user.
     * The list will be sorted by the Date of the last update.
     * 
     * @param username
     *            The name of the user.
     * @param permlink
     *            The permlink of an article.
     * @param limit
     *            Number of results.
     * @return A list of Content objects.
     * @throws SteemCommunicationException
     *             <ul>
     *             <li>If the server was not able to answer the request in the
     *             given time (see
     *             {@link eu.bittrade.libs.steemj.configuration.SteemJConfig#setResponseTimeout(int)
     *             setResponseTimeout}).</li>
     *             <li>If there is a connection problem.</li>
     *             </ul>
     * @throws SteemResponseException
     *             <ul>
     *             <li>If the SteemJ is unable to transform the JSON response
     *             into a Java object.</li>
     *             <li>If the Server returned an error object.</li>
     *             </ul>
     */
    public List<Discussion> getRepliesByLastUpdate(CommunicationHandler communicationHandler,
            GetRepliesByLastUpdateArgs getRepliesByLastUpdateArgs)
            throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_REPLIES_BY_LAST_UPDATE);
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(getRepliesByLastUpdateArgs);

        return communicationHandler.performRequest(requestObject, Discussion.class);
    }

    /**
     * Get a list of discussion for a given author.
     * 
     * @param author
     *            The authors name.
     * @param permlink
     *            The permlink of the article.
     * @param date
     *            Only return articles before this date. (This field seems to be
     *            ignored by the Steem api)
     * @param limit
     *            The number of results you want to receive.
     * @return A list of discussions.
     * @throws SteemCommunicationException
     *             <ul>
     *             <li>If the server was not able to answer the request in the
     *             given time (see
     *             {@link eu.bittrade.libs.steemj.configuration.SteemJConfig#setResponseTimeout(int)
     *             setResponseTimeout}).</li>
     *             <li>If there is a connection problem.</li>
     *             </ul>
     * @throws SteemResponseException
     *             <ul>
     *             <li>If the SteemJ is unable to transform the JSON response
     *             into a Java object.</li>
     *             <li>If the Server returned an error object.</li>
     *             </ul>
     */
    public DiscussionQueryResult getDiscussionsByAuthorBeforeDate(CommunicationHandler communicationHandler,
            GetDiscussionsByAuthorBeforeDateArgs getDiscussionsByAuthorBeforeDateArgs)
            throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_DISCUSSIONS_BY_AUTHOR_BEFORE_DATE);
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(getDiscussionsByAuthorBeforeDateArgs);

        return communicationHandler.performRequest(requestObject, DiscussionQueryResult.class).get(0);
    }

    /**
     * Get the active votes for a given post of a given author.
     * 
     * @param author
     *            The authors name.
     * @param permlink
     *            The permlink of the article.
     * @return A list of votes for a specific article.
     * @throws SteemCommunicationException
     *             <ul>
     *             <li>If the server was not able to answer the request in the
     *             given time (see
     *             {@link eu.bittrade.libs.steemj.configuration.SteemJConfig#setResponseTimeout(int)
     *             setResponseTimeout}).</li>
     *             <li>If there is a connection problem.</li>
     *             </ul>
     * @throws SteemResponseException
     *             <ul>
     *             <li>If the SteemJ is unable to transform the JSON response
     *             into a Java object.</li>
     *             <li>If the Server returned an error object.</li>
     *             </ul>
     */
    public static GetActiveVotesReturn getActiveVotes(CommunicationHandler communicationHandler,
            GetActiveVotesArgs getActiveVotesArgs) throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_ACTIVE_VOTES);
        requestObject.setSteemApi(SteemApiType.TAGS_API);
        requestObject.setAdditionalParameters(getActiveVotesArgs);

        return communicationHandler.performRequest(requestObject, GetActiveVotesReturn.class).get(0);
    }
}
