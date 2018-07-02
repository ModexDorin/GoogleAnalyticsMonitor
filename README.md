# Google Analytics Monitor

Google Analytics monitor is a solidity smart-contract written with the exact scope to help other custom contracts to monitor user interaction by connecting events to Google Analytics account.   
It has methods to raise logged events with parameters and the java application, on top of Web3j package, to monitor and operate API requests with the response written back to the Google Analytics account.

## Installation

+ Install Solidity Compiler

    ```
    sudo add-apt-repository ppa:ethereum/ethereum
    sudo apt-get update
    sudo apt-get install solc
    ```

+ Download [web3j(v3.4.0)](https://github.com/web3j/web3j/releases)

## Usage

+ Compile the contract to generate *abi* and *bin* files
    ```
    solc GaApi.sol --bin --abi --optimize -o build/ --overwrite
    ```
+ Generate the java class coresponding to the contract by using the web3j downloaded tool:

    ```
    ~/web3j-3.4.0/bin/web3j solidity generate '~/pathToBinFile/GaApi.bin' '~/pathToAbiFile/GaApi.abi' -p packageName -o '~/pathToJavaClassesFolder'
    ```
+ Deploy GaApi contact to the ethereum blockchain using [MyEtherWallet](https://www.myetherwallet.com/#contracts)

## GaApi contract in details:

+ __Events__

    - __GaCollectEvent__  logs the queryApiBase function call with the below parameters:  
        1. uint256 _version, logs the version specific to the call; currently has value 1  
        2. string _tid, logs the Google Analytics tracking id  
        3. address _clientId, logs the client id that was sent by the calling contract; it is thought to be the client calling eth account  
        4. string _eventCategory, logs the event category  
        5. string _eventLabel, logs the event name or label  

    - __GaEvent__  logs the updateApiResponse function call with the below parameters:  
        1. uint256 _version, logs the version specific to the call; currently has value 1  
        2. string _tid, logs the Google Analytics tracking id  
        3. address _clientId, logs the client id that was sent by the calling contract; it is thought to be the client calling eth account  
        4. string _hitPayload, logs the hit payload; it is the complex hit payload requested by the user; it expands the possibilities to use Google Analytics  

    - __OwnershipRenounced__  logs the updateApiResponse function call with the below parameters:  
        1. address indexed previousOwner;  

    - __OwnershipTransferred__   
        1. address indexed previousOwner, logs the previous owner of the contract  
        2. address indexed newOwner, logs the new owner of the contract  

+ __Available Variables__  
    - __owner__ is the owner of the contract; only the owner can execute the main methods of the contract  
    - __gasAddress__ is the address to receive the payment for response gas  
    - __allowedOwnerMembers__ is a solidity map to store the allowed member addresses to connect, access and operate the GaBase contract   
    - __requestAllowedOwnerMembers__ is a solidity map to store the requests that are correlated to their owner; only the request owner can see the result  
    - __requestCallingContractAddress__ is a solidity map to store the contract addresses that are correlated to each request id  
    - __requestIsProcessed__ is a solidity map to store the requests that are processed by java monitor  
    - __memberTrackingIdMap__ stores the trackingId for each member  


+ __Methods__    

    - __getAllowedOwnerMembers__ returns if an address is a member and can call the Apibase contract  
        1. address _member      

            __Note:__ can be executed only by contract owner  
    - __addOwnerMember__ enables the member address to call the Apibase contract  
        1. address _memberAddress  

            __Note:__ can be executed only by contract owner         
    - __disableOwnerMember__ disables the member address from calling the Apibase contract  
        1. address _memberAddress  

            __Note:__ can be executed only by contract owner  

    - __setGasPayAddress__ sets the account address that records the payments for response calls from Apibase contract to the calling contract  
        1. address _address   

           __Note:__ can be executed only by contract owner  
    - __getGasPayAddress__ returns the account address that records the payments for response calls from Apibase contract to the calling contract  

        __Note:__ can be executed only by approved member  

    - __setTrackingId__ sets the tracking id for the member address

        __Note:__ can be executed only by approved member  

    - __getTrackingId__ returns the tracking id for the member address

        __Note:__ can be executed only by approved member  

    - __gaCollect__ emits log to be executed to the Google Analytics measurement protocol by the GaApi java monitor application
        1. uint256 _version, logs the version specific to the call; currently has value 1  
        2. string _tid, logs the Google Analytics tracking id  
        3. address _clientId, logs the client id that was sent by the calling contract; it is thought to be the client calling eth account  
        4. string _eventCategory, logs the event category  
        5. string _eventLabel, logs the event name or label  

        __Note:__ can be executed only by approved member

    - __gaCollect__ emits log specific to events to be executed to the Google Analytics measurement protocol by the GaApi java monitor application
        1. uint256 _version, logs the version specific to the call; currently has value 1  
        2. string _tid, logs the Google Analytics tracking id  
        3. address _clientId, logs the client id that was sent by the calling contract; it is thought to be the client calling eth account  
        4. string _eventCategory, logs the event category  
        5. string _eventLabel, logs the event name or label  

        __Note:__ can be executed only by approved member

    - __gaPost__ emits log custom (complex and not specific only to events) to be executed to the Google Analytics measurement protocol by the GaApi java monitor application
        1. uint256 _version, logs the version specific to the call; currently has value 1  
        2. string _tid, logs the Google Analytics tracking id  
        3. address _clientId, logs the client id that was sent by the calling contract; it is thought to be the client calling eth account  
        4. string _hitPayload, logs the hit payload; it is the complex hit payload requested by the user; it expands the possibilities to use Google Analytics   

        __Note:__ can be executed only by approved member

    - __methodCall__ emits log cutomized and specific to method events to be executed to the Google Analytics measurement protocol by the GaApi java monitor application
        1. uint256 _version, logs the version specific to the call; currently has value 1  
        2. string _tid, logs the Google Analytics tracking id  
        3. address _clientId, logs the client id that was sent by the calling contract; it is thought to be the client calling eth account  
        4. string _eventCategory, logs the event category  
        5. string _eventLabel, logs the event name or label

        __Note:__ can be executed only by approved member

## Custom contract:  
The custom contract is the contract that connects and creates a communication to the GaBase contract.   
The request will be sent to Google Analytics and complex reports will be visible in Google account presenting the custom smart contract method hits    

+ ___Mandatory methods:___               

There are no mandatory methods since the result will be recorded to Google analytics platform

+ __Note:__
A full list of measurement parameters can be found to [parameters list](https://developers.google.com/analytics/devguides/collection/protocol/v1/parameters)
The tracking hits can be tested and validated to the [hit builder](https://ga-dev-tools.appspot.com/hit-builder/) page.

+ __The custom contract must contain the below GaBase interface:__   

```
contract GaBase {  
  function gaCollect(uint256 _version, address _clientId, string _eventCategory, string _eventLabel) public;

  // raise event to send google analytics custom request
  function gaPost(string _version, address _clientId, string _hitPayload) public;

  // raised by the custom contract when a get method was called
  function methodCall(uint256 _version, address _clientId, string _methodName) public;
}  
```

To send tracking analytics to Google account in the method must be instantiated the interface for GaBase contract and execute the proper method.

```
function callApiLocationInformation() public {  
    require(msg.sender != address(0));  
    GaBase apiBase = GaBase(gaBaseContractAddress);  
    apiBase.gaCollect("1",address(this), "methodCall", "callApiLocationInformation");  
}
```


## Java Application   

The java application has the purpose to monitor the GaBase smart contract and send the parameters received through the logs to the Google Analytics account

### In details:
#### Program to monitor the GaBase smart contract is called GaMonitor  

Requirements:  
1. Install Java

    ```
    sudo apt-get update
    sudo apt-get install default-jre
    ```

3. Open three terminal windows and go to project location:

   + terminal 1
       ```
       geth
       ```
   + terminal 2
       ```
        java -jar GaMonitor.jar param1 param2
       ```   
       **where**:  
        _param1_ is the contract address and  
        _param2_ is the account[0] private key

       Both params are in terminal 2 window at run time.
