package ijarvis.intelliq.Fabric;


import ijarvis.intelliq.FabricCA.TestConfigure;
import ijarvis.intelliq.LedgerRecord;
import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.Channel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static Logger logger=Logger.getLogger(AppTest.class);
    private static String CONNFIG_Orderer="grpc://127.0.0.1:7050";
    private static String CONNFIG_Peer0Org1="grpc://127.0.0.1:7051";
    private static String CONNFIG_Peer1Org1="grpc://127.0.0.1:7051";
    private static String CONNFIG_Peer0Org2="grpc://127.0.0.1:7051";
    private static String CONNFIG_Peer1Org2="grpc://127.0.0.1:7051";
    private static String CHANNELID="epointchannel";
    private static LedgerRecord PERSONINFO=new LedgerRecord("liuwenru","{name:\"liuwenhua\",cname:\"刘文华\"}");
    @Before
    public void Setup() throws Exception{
        logger.debug("Fabric Test Init........");
        FabricApp fabricApp=new FabricApp();
        FabricApp.init();
    }

    /**
     *
     * 测试链码插入操作
     */
    @Ignore
    @Test
    public void TestEpointChainCodeInstert() throws Exception {
        logger.debug("测试Fabric 插入功能");
        Channel channel = FabricApp.client.newChannel(CHANNELID);
        channel.addPeer(FabricApp.client.newPeer("peer", CONNFIG_Peer0Org1));
        channel.addOrderer(FabricApp.client.newOrderer("orderer", CONNFIG_Orderer));
        channel.initialize();
        FabricApp.instertFabcar(channel,PERSONINFO);
    }

    /**
     * 测试链码查询操作
     */
    @Test
    public void TestEpointChainCodeQuery() throws Exception {
        logger.debug("测试Fabric 查询功能");
        Channel channel = FabricApp.client.newChannel(TestConfigure.CHANNLNAME);
        channel.addPeer(FabricApp.client.newPeer("peer", FabricApp.orgHashMap.get("org1").getPeerLocation("peer0org1")));
        channel.addOrderer(FabricApp.client.newOrderer("orderer", FabricApp.orgHashMap.get("org1").getOrdererLocation("orderer")));
        channel.initialize();
        FabricApp.queryFabcar(channel, PERSONINFO.getKey());
    }

    /**
     *
     */
    @Ignore
    @Test
    public void TestEpointChainCodeMutilInstert() throws  Exception{
        logger.debug("测试Fabric 循环插入1000个值测试监控值是否包含变化");
        Channel channel = FabricApp.client.newChannel(CHANNELID);
        channel.addPeer(FabricApp.client.newPeer("peer", CONNFIG_Peer0Org1));
        channel.addOrderer(FabricApp.client.newOrderer("orderer", CONNFIG_Orderer));
        channel.initialize();
        for (int i =0 ;i<10000;i++){
            String perid=UUID.randomUUID().toString();
            LedgerRecord tmp=new LedgerRecord(perid,"测试");
            FabricApp.instertFabcar(channel, tmp);
        }
        logger.debug("测试完成");
    }


}
