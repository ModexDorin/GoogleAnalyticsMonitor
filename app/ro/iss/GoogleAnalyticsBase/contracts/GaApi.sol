pragma solidity ^0.4.23;


contract GaApi {

    event OwnershipRenounced(address indexed previousOwner);
    event OwnershipTransferred( address indexed previousOwner, address indexed newOwner);
    event GaCollectEvent(uint256 _version, string _tid, address _clientId, string _eventCategory, string _eventLabel);
    event GaEvent(string _version, string _tid, address _clientId, string _hitPayload);

    address public owner;

    // the address to receive the payment for response gas
    address public gasAddress;

    // list of allowed members
    mapping(address => bool) private allowedOwnerMembers;
    // the requests are correlated to their owner; only the request owner can see the result
    mapping(uint256 => address) private requestAllowedOwnerMembers;
    // the requests
    mapping(uint256 => address) private requestCallingContractAddress;
    // java monitor will process only if false
    mapping(uint256 => bool) private requestIsProcessed;

    // Google analytics mapping **********
    mapping (address => string) private memberTrackingIdMap;

    // checks if the operated by the owner
    modifier onlyOwner() {
        require(msg.sender == owner);
        _;
    }

    modifier onlyMember() {
        require(allowedOwnerMembers[msg.sender] == true);
        _;
    }

    // check if the request is the from its owner
    modifier allowOnlyMember(uint256 _requestId) {
        require(requestAllowedOwnerMembers[_requestId] == msg.sender);
        _;
    }

    constructor() public {
        require(owner == address(0));
        owner = msg.sender;
        allowedOwnerMembers[owner] = true;
    }

    function getAllowedOwnerMembers(address _member) public view onlyOwner returns (bool) {
        return allowedOwnerMembers[_member];
    }

    // add member address
    function addOwnerMember(address _memberAddress) public onlyOwner {
        require(_memberAddress != address(0));
        allowedOwnerMembers[_memberAddress] = true;
    }

    // disable member address
    function disableOwnerMember(address _memberAddress) public onlyOwner {
        require(_memberAddress != address(0));
        allowedOwnerMembers[_memberAddress] = false;
    }

    function setGasPayAddress(address _address) public onlyOwner {
        require(_address != address(0));
        gasAddress = _address;
    }

    function getGasPayAddress() public onlyMember view returns (address) {
        return gasAddress;
    }

    function setTrackingId(string _trackingId) public onlyMember {
        // no check if the string is empty since the user pays to set it
        memberTrackingIdMap[msg.sender] = _trackingId;
    }

    function getTrackingId() public view onlyMember returns(string) {
        return memberTrackingIdMap[msg.sender];
    }

    // raise event to send google analytics collect event specific request
    function gaCollect(uint256 _version, address _clientId, string _eventCategory, string _eventLabel) public onlyMember
    {
        require(_version != uint256(0) && _clientId != address(0));
        emit GaCollectEvent(_version, memberTrackingIdMap[msg.sender],  _clientId, _eventCategory, _eventLabel);
    }

    // raise event to send google analytics custom request
    function gaPost(string _version, address _clientId, string _hitPayload) public onlyMember {
        emit GaEvent(_version, memberTrackingIdMap[msg.sender],  _clientId, _hitPayload);
    }

    // raised by the custom contract when a get method was called
    function methodCall(uint256 _version, address _clientId, string _methodName) public onlyMember {
        gaCollect(_version, _clientId, "methodCall", _methodName);
    }

}
