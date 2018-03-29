package ijarvis.intelliq.Fabric;

import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;

import java.net.MalformedURLException;
import java.util.HashMap;

/**
 * 加载未使用CA模块时用户的CA证书以及私钥信息，并按照要求加载对应的组织机构节点信息
 */

public class TestConfigure {
    public static String CHAINCODENAME="epointchaincodecommon";
    public static String CHAINCODEVERSION="0.2";
    public static String CHANNLNAME="epointchannel";
    public static HashMap<String,SampleOrg> getConfigure() throws MalformedURLException, InvalidArgumentException {
        HashMap<String,SampleOrg> orgHashMap=new HashMap<>();
        SampleOrg org1=new SampleOrg("org1","Org1MSP");
        org1.addPeerLocation("peer0org1","grpc://127.0.0.1:7051");
        org1.addPeerLocation("peer1org1","grpc://127.0.0.1:7051");
        org1.addOrdererLocation("orderer","grpc://127.0.0.1:7050");
        org1.setCALocation("http://127.0.0.1:7054");
        SampleUser Adminorg1=new SampleUser("peer","Admin","Org1MSP");
        SampleUser User1org1=new SampleUser("peer","User1","Org1MSP");
        org1.addUser(Adminorg1);
        org1.addUser(User1org1);
        org1.setAdmin(Adminorg1);
        

        SampleOrg org2=new SampleOrg("org2","Org2MSP");
        org2.addPeerLocation("peer0org2","grpc://127.0.0.1:7051");
        org2.addPeerLocation("peer1org2","grpc://127.0.0.1:7051");
        org2.addOrdererLocation("orderer","grpc://127.0.0.1:7050");
        SampleUser Adminorg2=new SampleUser("peer","Admin","Org2MSP");
        SampleUser User1org2=new SampleUser("peer","User1","Org2MSP");
        org1.addUser(Adminorg2);
        org1.addUser(User1org2);
        org1.setAdmin(Adminorg2);

        orgHashMap.put("org1",org1);
        orgHashMap.put("org2",org2);
        return orgHashMap;
    }



}
