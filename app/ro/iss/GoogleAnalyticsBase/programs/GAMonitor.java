package ro.iss.GoogleAnalyticsBase.programs;

import java.util.Arrays;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import com.google.gson.JsonSyntaxException;

import play.Logger;
import ro.iss.GoogleAnalyticsBase.classes.GaApi;
import ro.iss.GoogleAnalyticsBase.classes.HttpHelper;
import rx.Observable;
import rx.Subscription;

public class GAMonitor
{

	public static void main(String[] args) {

		// contract address
		// final String contractAddress = args[0].toString();
		final String contractAddress = "0x4225afbf2846279d6bf04b57a03b498a520a5546";

		// define rpc connection and blockchain identifier
		Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
		// Logger.debug("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());

		// load main account based on private key
		// Credentials credentials = Credentials.create(args[1].toString());
		Credentials credentials = Credentials.create("dfd32c33493fc495724365a6eca66d37aa4d219bff594aecacecb9cbfd1552b9");
		Logger.debug("[ETH-INFO] Credentials: " + credentials.getAddress());

		// load existing smart contract
		GaApi contract = GaApi.load(contractAddress, web3j, credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT);
		Logger.debug("[ETH-INFO] Loading contract: " + contract.getContractAddress());

		Event GACOLLECTEVENT_EVENT = new Event("GaCollectEvent", Arrays.<TypeReference<?>>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>()
		{
		}, new TypeReference<Utf8String>()
		{
		}, new TypeReference<Address>()
		{
		}, new TypeReference<Utf8String>()
		{
		}, new TypeReference<Utf8String>()
		{
		}));
		;

		EthFilter filterCollect = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, contract.getContractAddress()); // .substring(2)
		filterCollect.addSingleTopic(EventEncoder.encode(GACOLLECTEVENT_EVENT));
		Observable<GaApi.GaCollectEventEventResponse> callbackCollectEvent = contract.gaCollectEventEventObservable(filterCollect);

		// make a subscription using the observable, monitoring the logs
		Subscription subscription = callbackCollectEvent.subscribe(log -> {
			Logger.debug("[ETH-EVENT-INFO] _version:: " + log._version);
			Logger.debug("[ETH-EVENT-INFO] _method:: " + log._tid);
			Logger.debug("[ETH-EVENT-INFO] _clientId:: " + log._clientId);
			Logger.debug("[ETH-EVENT-INFO] _hitType:: event");
			Logger.debug("[ETH-EVENT-INFO] _eventCategory::" + log._eventCategory);
			Logger.debug("[ETH-EVENT-INFO] _eventAction::" + log._eventLabel);

			// construct http parameters tracking
			String httpString = "";
			httpString += "v=" + log._version.toString();
			httpString += "&tid=" + log._tid;
			httpString += "&t=event";
			httpString += "&cid=" + log._clientId;
			httpString += "&ec=" + log._eventCategory;
			httpString += "&ea=" + log._eventLabel;

			// http request
			try {
				Logger.debug("https://www.google-analytics.com/collect?" + httpString);
				// make the request to google analytics api
				String httpResponse = HttpHelper.get("https://www.google-analytics.com/collect?" + httpString);
			}
			catch (JsonSyntaxException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}); // subscribe - close bracket

		// NOTE: Uncomment below comments in case you want a limited time service

		// set minutes for Observable to be active and then unsubscribe
		// try {
		// TimeUnit.MINUTES.sleep(10);
		// }
		// catch (InterruptedException e) {
		// Logger.error("TimeUnit error");
		// }
		// subscription.unsubscribe();

		Logger.debug("PROCESS FINISHED");
	}

}
