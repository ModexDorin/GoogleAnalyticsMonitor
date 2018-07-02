package ro.iss.GoogleAnalyticsBase.classes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import rx.Observable;
import rx.functions.Func1;

/**
 * <p>
 * Auto generated code.
 * <p>
 * <strong>Do not modify!</strong>
 * <p>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>
 * Generated with web3j version 3.4.0.
 */
public class GaApi extends Contract
{
	private static final String BINARY = "6080604052600060085534801561001557600080fd5b50600054600160a060020a03161561002c57600080fd5b60008054600160a060020a0319163317808255600160a060020a03168152600260205260409020805460ff19166001179055610bd08061006d6000396000f3006080604052600436106100b95763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416631263791181146100be57806319b68d64146100e15780631a2973ed146101125780631efb647d1461019c5780632684ba4e1461020557806335d20dce1461023a5780634d628a2c146102e05780634f65671b14610387578063694e2b76146103e05780638da5cb5b14610401578063901690b114610416578063f0ab631a1461042b575b600080fd5b3480156100ca57600080fd5b506100df600160a060020a036004351661044c565b005b3480156100ed57600080fd5b506100f6610499565b60408051600160a060020a039092168252519081900360200190f35b34801561011e57600080fd5b506101276104cb565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610161578181015183820152602001610149565b50505050905090810190601f16801561018e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101a857600080fd5b50604080516020600460443581810135601f81018490048402850184019095528484526100df9482359460248035600160a060020a03169536959460649492019190819084018382808284375094975061058d9650505050505050565b34801561021157600080fd5b50610226600160a060020a03600435166105f4565b604080519115158252519081900360200190f35b34801561024657600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100df94369492936024939284019190819084018382808284375050604080516020601f818a01358b0180359182018390048302840183018552818452989b600160a060020a038b35169b909a90999401975091955091820193509150819084018382808284375094975061062b9650505050505050565b3480156102ec57600080fd5b50604080516020600460443581810135601f81018490048402850184019095528484526100df9482359460248035600160a060020a03169536959460649492019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975061080a9650505050505050565b34801561039357600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526100df9436949293602493928401919081908401838280828437509497506109fe9650505050505050565b3480156103ec57600080fd5b506100df600160a060020a0360043516610a43565b34801561040d57600080fd5b506100f6610a9e565b34801561042257600080fd5b506100f6610aad565b34801561043757600080fd5b506100df600160a060020a0360043516610abc565b600054600160a060020a0316331461046357600080fd5b600160a060020a038116151561047857600080fd5b600160a060020a03166000908152600260205260409020805460ff19169055565b3360009081526002602052604081205460ff1615156001146104ba57600080fd5b50600154600160a060020a03165b90565b3360009081526002602052604090205460609060ff1615156001146104ef57600080fd5b3360009081526006602090815260409182902080548351601f6002600019610100600186161502019093169290920491820184900484028101840190945280845290918301828280156105835780601f1061055857610100808354040283529160200191610583565b820191906000526020600020905b81548152906001019060200180831161056657829003601f168201915b5050505050905090565b3360009081526002602052604090205460ff1615156001146105ae57600080fd5b6105ef83836040805190810160405280600a81526020017f6d6574686f6443616c6c000000000000000000000000000000000000000000008152508461080a565b505050565b60008054600160a060020a0316331461060c57600080fd5b50600160a060020a031660009081526002602052604090205460ff1690565b3360009081526002602052604090205460ff16151560011461064c57600080fd5b7f6f4d19d36bf1f963ab2a59402830b1252a0c32d6611cc15fb85df587707f2215836006600033600160a060020a0316600160a060020a03168152602001908152602001600020848460405180806020018060200185600160a060020a0316600160a060020a0316815260200180602001848103845288818151815260200191508051906020019080838360005b838110156106f25781810151838201526020016106da565b50505050905090810190601f16801561071f5780820380516001836020036101000a031916815260200191505b508481038352875460026000196101006001841615020190911604808252602090910190889080156107925780601f1061076757610100808354040283529160200191610792565b820191906000526020600020905b81548152906001019060200180831161077557829003601f168201915b5050848103825285518152855160209182019187019080838360005b838110156107c65781810151838201526020016107ae565b50505050905090810190601f1680156107f35780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390a1505050565b3360009081526002602052604090205460ff16151560011461082b57600080fd5b83158015906108425750600160a060020a03831615155b151561084d57600080fd5b336000908152600660209081526040918290208251878152600160a060020a0387169381019390935260a09183018281528154600260001961010060018416150201909116049284018390527fc32ec0a6cba31d1e733893633a9c6fadd57306803e3e7c736a9135277839f34f9388938892889288929091906060830190608084019060c0850190899080156109245780601f106108f957610100808354040283529160200191610924565b820191906000526020600020905b81548152906001019060200180831161090757829003601f168201915b5050848103835286518152865160209182019188019080838360005b83811015610958578181015183820152602001610940565b50505050905090810190601f1680156109855780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b838110156109b85781810151838201526020016109a0565b50505050905090810190601f1680156109e55780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390a150505050565b3360009081526002602052604090205460ff161515600114610a1f57600080fd5b3360009081526006602090815260409091208251610a3f92840190610b0c565b5050565b600054600160a060020a03163314610a5a57600080fd5b600160a060020a0381161515610a6f57600080fd5b6001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600054600160a060020a031681565b600154600160a060020a031681565b600054600160a060020a03163314610ad357600080fd5b600160a060020a0381161515610ae857600080fd5b600160a060020a03166000908152600260205260409020805460ff19166001179055565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b4d57805160ff1916838001178555610b7a565b82800160010185558215610b7a579182015b82811115610b7a578251825591602001919060010190610b5f565b50610b86929150610b8a565b5090565b6104c891905b80821115610b865760008155600101610b905600a165627a7a7230582021e92d9950ec8bba6d90c389e9ee3630397711a64978ca943866a6ee34c712820029";

	public static final String FUNC_DISABLEOWNERMEMBER = "disableOwnerMember";

	public static final String FUNC_GETGASPAYADDRESS = "getGasPayAddress";

	public static final String FUNC_GETTRACKINGID = "getTrackingId";

	public static final String FUNC_METHODCALL = "methodCall";

	public static final String FUNC_GETALLOWEDOWNERMEMBERS = "getAllowedOwnerMembers";

	public static final String FUNC_GAPOST = "gaPost";

	public static final String FUNC_GACOLLECT = "gaCollect";

	public static final String FUNC_SETTRACKINGID = "setTrackingId";

	public static final String FUNC_SETGASPAYADDRESS = "setGasPayAddress";

	public static final String FUNC_OWNER = "owner";

	public static final String FUNC_GASADDRESS = "gasAddress";

	public static final String FUNC_ADDOWNERMEMBER = "addOwnerMember";

	public static final Event OWNERSHIPRENOUNCED_EVENT = new Event("OwnershipRenounced", Arrays.<TypeReference<?>>asList(new TypeReference<Address>()
	{
	}), Arrays.<TypeReference<?>>asList());;

	public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", Arrays.<TypeReference<?>>asList(new TypeReference<Address>()
	{
	}, new TypeReference<Address>()
	{
	}), Arrays.<TypeReference<?>>asList());;

	public static final Event GACOLLECTEVENT_EVENT = new Event("GaCollectEvent", Arrays.<TypeReference<?>>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>()
	{
	}, new TypeReference<Utf8String>()
	{
	}, new TypeReference<Address>()
	{
	}, new TypeReference<Utf8String>()
	{
	}, new TypeReference<Utf8String>()
	{
	}));;

	public static final Event GAEVENT_EVENT = new Event("GaEvent", Arrays.<TypeReference<?>>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>()
	{
	}, new TypeReference<Utf8String>()
	{
	}, new TypeReference<Address>()
	{
	}, new TypeReference<Utf8String>()
	{
	}));;

	protected GaApi(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	protected GaApi(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public RemoteCall<TransactionReceipt> disableOwnerMember(String _memberAddress) {

		final Function function = new Function(FUNC_DISABLEOWNERMEMBER, Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_memberAddress)), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<String> getGasPayAddress() {

		final Function function = new Function(FUNC_GETGASPAYADDRESS, Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Address>()
		{
		}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<String> getTrackingId() {

		final Function function = new Function(FUNC_GETTRACKINGID, Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>()
		{
		}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<TransactionReceipt> methodCall(BigInteger _version, String _clientId, String _methodName) {

		final Function function = new Function(FUNC_METHODCALL, Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_version), new org.web3j.abi.datatypes.Address(_clientId), new org.web3j.abi.datatypes.Utf8String(_methodName)), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<Boolean> getAllowedOwnerMembers(String _member) {

		final Function function = new Function(FUNC_GETALLOWEDOWNERMEMBERS, Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_member)), Arrays.<TypeReference<?>>asList(new TypeReference<Bool>()
		{
		}));
		return executeRemoteCallSingleValueReturn(function, Boolean.class);
	}

	public RemoteCall<TransactionReceipt> gaPost(String _version, String _clientId, String _hitPayload) {

		final Function function = new Function(FUNC_GAPOST, Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_version), new org.web3j.abi.datatypes.Address(_clientId), new org.web3j.abi.datatypes.Utf8String(_hitPayload)), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> gaCollect(BigInteger _version, String _clientId, String _eventCategory, String _eventLabel) {

		final Function function = new Function(FUNC_GACOLLECT, Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_version), new org.web3j.abi.datatypes.Address(_clientId), new org.web3j.abi.datatypes.Utf8String(_eventCategory), new org.web3j.abi.datatypes.Utf8String(_eventLabel)), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> setTrackingId(String _trackingId) {

		final Function function = new Function(FUNC_SETTRACKINGID, Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_trackingId)), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> setGasPayAddress(String _address) {

		final Function function = new Function(FUNC_SETGASPAYADDRESS, Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<String> owner() {

		final Function function = new Function(FUNC_OWNER, Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Address>()
		{
		}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<String> gasAddress() {

		final Function function = new Function(FUNC_GASADDRESS, Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Address>()
		{
		}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<TransactionReceipt> addOwnerMember(String _memberAddress) {

		final Function function = new Function(FUNC_ADDOWNERMEMBER, Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_memberAddress)), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public static RemoteCall<GaApi> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {

		return deployRemoteCall(GaApi.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
	}

	public static RemoteCall<GaApi> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {

		return deployRemoteCall(GaApi.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
	}

	public List<OwnershipRenouncedEventResponse> getOwnershipRenouncedEvents(TransactionReceipt transactionReceipt) {

		List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, transactionReceipt);
		ArrayList<OwnershipRenouncedEventResponse> responses = new ArrayList<OwnershipRenouncedEventResponse>(valueList.size());
		for (Contract.EventValuesWithLog eventValues : valueList) {
			OwnershipRenouncedEventResponse typedResponse = new OwnershipRenouncedEventResponse();
			typedResponse.log = eventValues.getLog();
			typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<OwnershipRenouncedEventResponse> ownershipRenouncedEventObservable(EthFilter filter) {

		return web3j.ethLogObservable(filter).map(new Func1<Log, OwnershipRenouncedEventResponse>()
		{
			@Override
			public OwnershipRenouncedEventResponse call(Log log) {

				Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPRENOUNCED_EVENT, log);
				OwnershipRenouncedEventResponse typedResponse = new OwnershipRenouncedEventResponse();
				typedResponse.log = log;
				typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
				return typedResponse;
			}
		});
	}

	public Observable<OwnershipRenouncedEventResponse> ownershipRenouncedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {

		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(OWNERSHIPRENOUNCED_EVENT));
		return ownershipRenouncedEventObservable(filter);
	}

	public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {

		List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
		ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
		for (Contract.EventValuesWithLog eventValues : valueList) {
			OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
			typedResponse.log = eventValues.getLog();
			typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
			typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<OwnershipTransferredEventResponse> ownershipTransferredEventObservable(EthFilter filter) {

		return web3j.ethLogObservable(filter).map(new Func1<Log, OwnershipTransferredEventResponse>()
		{
			@Override
			public OwnershipTransferredEventResponse call(Log log) {

				Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
				OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
				typedResponse.log = log;
				typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
				typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
				return typedResponse;
			}
		});
	}

	public Observable<OwnershipTransferredEventResponse> ownershipTransferredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {

		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
		return ownershipTransferredEventObservable(filter);
	}

	public List<GaCollectEventEventResponse> getGaCollectEventEvents(TransactionReceipt transactionReceipt) {

		List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(GACOLLECTEVENT_EVENT, transactionReceipt);
		ArrayList<GaCollectEventEventResponse> responses = new ArrayList<GaCollectEventEventResponse>(valueList.size());
		for (Contract.EventValuesWithLog eventValues : valueList) {
			GaCollectEventEventResponse typedResponse = new GaCollectEventEventResponse();
			typedResponse.log = eventValues.getLog();
			typedResponse._version = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
			typedResponse._tid = (String) eventValues.getNonIndexedValues().get(1).getValue();
			typedResponse._clientId = (String) eventValues.getNonIndexedValues().get(2).getValue();
			typedResponse._eventCategory = (String) eventValues.getNonIndexedValues().get(3).getValue();
			typedResponse._eventLabel = (String) eventValues.getNonIndexedValues().get(4).getValue();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<GaCollectEventEventResponse> gaCollectEventEventObservable(EthFilter filter) {

		return web3j.ethLogObservable(filter).map(new Func1<Log, GaCollectEventEventResponse>()
		{
			@Override
			public GaCollectEventEventResponse call(Log log) {

				Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(GACOLLECTEVENT_EVENT, log);
				GaCollectEventEventResponse typedResponse = new GaCollectEventEventResponse();
				typedResponse.log = log;
				typedResponse._version = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
				typedResponse._tid = (String) eventValues.getNonIndexedValues().get(1).getValue();
				typedResponse._clientId = (String) eventValues.getNonIndexedValues().get(2).getValue();
				typedResponse._eventCategory = (String) eventValues.getNonIndexedValues().get(3).getValue();
				typedResponse._eventLabel = (String) eventValues.getNonIndexedValues().get(4).getValue();
				return typedResponse;
			}
		});
	}

	public Observable<GaCollectEventEventResponse> gaCollectEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {

		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(GACOLLECTEVENT_EVENT));
		return gaCollectEventEventObservable(filter);
	}

	public List<GaEventEventResponse> getGaEventEvents(TransactionReceipt transactionReceipt) {

		List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(GAEVENT_EVENT, transactionReceipt);
		ArrayList<GaEventEventResponse> responses = new ArrayList<GaEventEventResponse>(valueList.size());
		for (Contract.EventValuesWithLog eventValues : valueList) {
			GaEventEventResponse typedResponse = new GaEventEventResponse();
			typedResponse.log = eventValues.getLog();
			typedResponse._version = (String) eventValues.getNonIndexedValues().get(0).getValue();
			typedResponse._tid = (String) eventValues.getNonIndexedValues().get(1).getValue();
			typedResponse._clientId = (String) eventValues.getNonIndexedValues().get(2).getValue();
			typedResponse._hitPayload = (String) eventValues.getNonIndexedValues().get(3).getValue();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<GaEventEventResponse> gaEventEventObservable(EthFilter filter) {

		return web3j.ethLogObservable(filter).map(new Func1<Log, GaEventEventResponse>()
		{
			@Override
			public GaEventEventResponse call(Log log) {

				Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(GAEVENT_EVENT, log);
				GaEventEventResponse typedResponse = new GaEventEventResponse();
				typedResponse.log = log;
				typedResponse._version = (String) eventValues.getNonIndexedValues().get(0).getValue();
				typedResponse._tid = (String) eventValues.getNonIndexedValues().get(1).getValue();
				typedResponse._clientId = (String) eventValues.getNonIndexedValues().get(2).getValue();
				typedResponse._hitPayload = (String) eventValues.getNonIndexedValues().get(3).getValue();
				return typedResponse;
			}
		});
	}

	public Observable<GaEventEventResponse> gaEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {

		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(GAEVENT_EVENT));
		return gaEventEventObservable(filter);
	}

	public static GaApi load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {

		return new GaApi(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public static GaApi load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {

		return new GaApi(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public static class OwnershipRenouncedEventResponse
	{
		public Log log;

		public String previousOwner;
	}

	public static class OwnershipTransferredEventResponse
	{
		public Log log;

		public String previousOwner;

		public String newOwner;
	}

	public static class GaCollectEventEventResponse
	{
		public Log log;

		public BigInteger _version;

		public String _tid;

		public String _clientId;

		public String _eventCategory;

		public String _eventLabel;
	}

	public static class GaEventEventResponse
	{
		public Log log;

		public String _version;

		public String _tid;

		public String _clientId;

		public String _hitPayload;
	}
}
