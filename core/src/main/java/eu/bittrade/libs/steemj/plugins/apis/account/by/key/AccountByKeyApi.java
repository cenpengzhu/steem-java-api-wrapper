package eu.bittrade.libs.steemj.plugins.apis.account.by.key;

import eu.bittrade.libs.steemj.communication.CommunicationHandler;
import eu.bittrade.libs.steemj.communication.jrpc.JsonRPCRequest;
import eu.bittrade.libs.steemj.enums.RequestMethods;
import eu.bittrade.libs.steemj.enums.SteemApiType;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import eu.bittrade.libs.steemj.plugins.apis.account.by.key.models.GetKeyReferencesArgs;
import eu.bittrade.libs.steemj.plugins.apis.account.by.key.models.GetKeyReferencesReturn;

/**
 * This class implements the account by key api.
 * 
 * @author <a href="http://steemit.com/@dez1337">dez1337</a>
 */
public class AccountByKeyApi {
    /** Add a private constructor to hide the implicit public one. */
    private AccountByKeyApi() {
    }

    /**
     * Search for users under the use of their public key(s).
     * 
     * @param communicationHandler
     *            A
     *            {@link eu.bittrade.libs.steemj.communication.CommunicationHandler
     *            CommunicationHandler} instance that should be used to send the
     *            request.
     * @param publicKeys
     *            An array containing one or more public keys.
     * @return A list of arrays containing the matching account names.
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
    public static GetKeyReferencesReturn getKeyReferences(CommunicationHandler communicationHandler,
            GetKeyReferencesArgs publicKeys) throws SteemCommunicationException, SteemResponseException {
        JsonRPCRequest requestObject = new JsonRPCRequest();
        requestObject.setApiMethod(RequestMethods.GET_KEY_REFERENCES);
        requestObject.setSteemApi(SteemApiType.ACCOUNT_BY_KEY_API);
        Object[] parameters = { publicKeys };
        requestObject.setAdditionalParameters(parameters);

        return communicationHandler.performRequest(requestObject, GetKeyReferencesReturn.class).get(0);
    }
}
