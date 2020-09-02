package com.joezeo.steamcollector.common.config;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * mongoDB配置
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/28 16:55
 */
@Configuration
public class MongoConfig {
    @Value("${server.data.mongo.uri}")
    private String uri;
    @Value("${server.data.mongo.address}")
    private String address;
    @Value("${server.data.mongo.verify}")
    private boolean verify;
    @Value("${server.data.mongo.keepalive}")
    private boolean keepalive;
    @Value("${server.data.mongo.useuri}")
    private boolean useuri;
    @Value("${server.data.mongo.dbname}")
    private String dbname;
    @Value("${server.data.mongo.username}")
    private String username;
    @Value("${server.data.mongo.password}")
    private String password;
    @Value("${server.data.mongo.host.connections.count}")
    private Integer connectionCount;
    @Value("${server.data.mongo.max.wait.time}")
    private Integer maxWaitTime;

    @Bean("mongoClientOptions")
    public MongoClientOptions mongoClientOptions() {
        final MongoClientOptions.Builder optionBuilder = new MongoClientOptions.Builder();
        optionBuilder.socketKeepAlive(keepalive);
        optionBuilder.connectionsPerHost(connectionCount);
        optionBuilder.maxWaitTime(maxWaitTime);
        optionBuilder.readPreference(ReadPreference.primary());// 最近优先策略
        optionBuilder.threadsAllowedToBlockForConnectionMultiplier(50);
        optionBuilder.writeConcern(WriteConcern.UNACKNOWLEDGED);
        optionBuilder.readPreference(ReadPreference.primary());
        return optionBuilder.build();
    }

    @Bean("mongoClient")
    public MongoClient mongoClient(@Autowired MongoClientOptions clientOptions) {
        if (verify) {
            if (!useuri) {
                final List<ServerAddress> addressList = analysisAddress(address);
                MongoCredential credentials = MongoCredential.createCredential(username, dbname, password.toCharArray());
                List<MongoCredential> credentialsList = new ArrayList<>();
                credentialsList.add(credentials);
                return new MongoClient(addressList, credentialsList, clientOptions);
            } else {
                MongoClientURI connStr = new MongoClientURI(uri);
                return new MongoClient(connStr);
            }
        } else {
            final List<ServerAddress> addressList = analysisAddress(address);
            return new MongoClient(addressList, clientOptions);
        }
    }

    @Bean("datastore")
    public Datastore datastore(@Autowired MongoClient mongoClient) {
        Morphia morphia = new Morphia();
        Datastore ads = morphia.createDatastore(mongoClient, dbname);
        ads.ensureIndexes(); // 建立索引
        return ads;
    }

    /* 解析字符串生成mongo address */
    private List<ServerAddress> analysisAddress(String addressStr) {
        String[] allStr = addressStr.split(",") ;
        List<ServerAddress> allAddress = new ArrayList<>() ;
        for(String _address : allStr){
            String[] _hp = _address.trim().split(":") ;
            String host = _hp[0] ;
            int port = Integer.parseInt(_hp[1]) ;
            final ServerAddress address = new ServerAddress(host , port);
            allAddress.add(address) ;
        }
        return allAddress ;
    }
}
