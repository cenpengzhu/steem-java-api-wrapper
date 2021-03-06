package eu.bittrade.libs.steemj.base.models.operations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import org.joou.UInteger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import eu.bittrade.libs.steemj.BaseITForOperationParsing;
import eu.bittrade.libs.steemj.IntegrationTest;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import eu.bittrade.libs.steemj.plugins.apis.block.models.ExtendedSignedBlock;
import eu.bittrade.libs.steemj.protocol.operations.LimitOrderCancelOperation;
import eu.bittrade.libs.steemj.protocol.operations.Operation;

/**
 * This class tests if the {@link LimitOrderCancelOperation} can be parsed
 * successfully.
 * 
 * @author <a href="http://steemit.com/@dez1337">dez1337</a>
 */
public class LimitOrderCancelOperationParsingIT extends BaseITForOperationParsing {
    private static final long BLOCK_NUMBER_CONTAINING_OPERATION = 5681453;
    private static final int TRANSACTION_INDEX = 0;
    private static final int OPERATION_INDEX = 0;
    private static final String EXPECTED_AUTHOR = "fnait";
    private static final int EXPECTED_ORDER_ID = -1721858468;

    /**
     * Prepare all required fields used by this test class.
     * 
     * @throws Exception
     *             If something went wrong.
     */
    @BeforeClass()
    public static void prepareTestClass() throws Exception {
        setupIntegrationTestEnvironment();
    }

    @Override
    @Test
    @Category({ IntegrationTest.class })
    public void testOperationParsing() throws SteemCommunicationException, SteemResponseException {
        ExtendedSignedBlock blockContainingVoteOperation = steemJ.getBlock(BLOCK_NUMBER_CONTAINING_OPERATION).get();

        Operation voteOperation = blockContainingVoteOperation.getTransactions().get(TRANSACTION_INDEX).getOperations()
                .get(OPERATION_INDEX);

        assertThat(voteOperation, instanceOf(LimitOrderCancelOperation.class));
        assertThat(((LimitOrderCancelOperation) voteOperation).getOwner().getName(), equalTo(EXPECTED_AUTHOR));
        assertThat(((LimitOrderCancelOperation) voteOperation).getOrderId(),
                equalTo(UInteger.valueOf(EXPECTED_ORDER_ID)));
    }
}
