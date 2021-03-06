package eu.bittrade.libs.steemj.base.models.operations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import eu.bittrade.crypto.core.CryptoUtils;
import eu.bittrade.crypto.core.Sha256Hash;
import eu.bittrade.libs.steemj.BaseTransactionalUT;
import eu.bittrade.libs.steemj.base.models.SignedTransaction;
import eu.bittrade.libs.steemj.exceptions.SteemInvalidTransactionException;
import eu.bittrade.libs.steemj.fc.TimePointSec;
import eu.bittrade.libs.steemj.protocol.AccountName;
import eu.bittrade.libs.steemj.protocol.operations.Operation;
import eu.bittrade.libs.steemj.protocol.operations.ProveAuthorityOperation;

/**
 * Test the transformation of the {@link ProveAuthorityOperation}.
 * 
 * @author <a href="http://steemit.com/@dez1337">dez1337</a>
 */
public class ProveAuthorityOperationTest extends BaseTransactionalUT {
    final String EXPECTED_BYTE_REPRESENTATION_WITH_OWNER = "170764657a3133333701";
    final String EXPECTED_BYTE_REPRESENTATION_WITH_ACTIVE = "170764657a3133333700";
    final String EXPECTED_TRANSACTION_HASH = "8eab8cc82a1c18605b048db55eaaef5ccf7e7e9a257b2ecc07e83b0a494b61b3";
    final String EXPECTED_TRANSACTION_SERIALIZATION = "0000000000000000000000000000000000000000000000000000000"
            + "000000000f68585abf4dce9c8045702170764657a3133333701170764657a313333370000";

    private static ProveAuthorityOperation proveAuthorityOperationWithOwnerKey;
    private static ProveAuthorityOperation proveAuthorityOperationWithActiveKey;

    /**
     * Prepare the environment for this specific test.
     * 
     * @throws Exception
     *             If something went wrong.
     */
    @BeforeClass()
    public static void prepareTestClass() throws Exception {
        setupUnitTestEnvironmentForTransactionalTests();

        AccountName challengedAccount = new AccountName("dez1337");

        proveAuthorityOperationWithOwnerKey = new ProveAuthorityOperation(challengedAccount, true);
        proveAuthorityOperationWithActiveKey = new ProveAuthorityOperation(challengedAccount);

        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(proveAuthorityOperationWithOwnerKey);
        operations.add(proveAuthorityOperationWithActiveKey);

        signedTransaction = new SignedTransaction(REF_BLOCK_NUM, REF_BLOCK_PREFIX, new TimePointSec(EXPIRATION_DATE),
                operations, null);
        signedTransaction.sign();
    }

    @Override
    @Test
    public void testOperationToByteArray() throws UnsupportedEncodingException, SteemInvalidTransactionException {
        assertThat("Expect that the operation has the given byte representation.",
                CryptoUtils.HEX.encode(proveAuthorityOperationWithOwnerKey.toByteArray()),
                equalTo(EXPECTED_BYTE_REPRESENTATION_WITH_OWNER));
        assertThat("Expect that the operation has the given byte representation.",
                CryptoUtils.HEX.encode(proveAuthorityOperationWithActiveKey.toByteArray()),
                equalTo(EXPECTED_BYTE_REPRESENTATION_WITH_ACTIVE));
    }

    @Override
    @Test
    public void testTransactionWithOperationToHex()
            throws UnsupportedEncodingException, SteemInvalidTransactionException {
        assertThat("The serialized transaction should look like expected.",
                CryptoUtils.HEX.encode(signedTransaction.toByteArray()), equalTo(EXPECTED_TRANSACTION_SERIALIZATION));
        assertThat("Expect that the serialized transaction results in the given hex.",
                CryptoUtils.HEX.encode(Sha256Hash.of(signedTransaction.toByteArray()).getBytes()),
                equalTo(EXPECTED_TRANSACTION_HASH));
    }
}
