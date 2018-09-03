package com.uzak.impl;

import com.uzak.inter.bean.test.TestRequest;
import com.uzak.inter.service.test.TestBeanServiceGrpc;
import com.uzak.util.StringUtil;
import io.grpc.stub.StreamObserver;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/8/26.
 */
@Service
public class TestServiceImpl extends TestBeanServiceGrpc.TestBeanServiceImplBase {

    Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);


    @Autowired
    Dao dao;

    @Override
    public void getTest(TestRequest.TestBeanRequest request, StreamObserver<TestRequest.TestBeanResponse> responseObserver) {
        System.out.println(request.getUrl() + ":" + request.getTime());
        TestRequest.TestBeanResponse.Builder response = TestRequest.TestBeanResponse.newBuilder();
        String s = "select * from uztoken";
        logger.info(StringUtil.LOGGERFORMAT_SQL,"TestServiceImpl.getTest",s);
        Sql sql = Sqls.create(s);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                while (rs.next()) {
                    logger.info(rs.getString("type") + rs.getString("token") + rs.getLong("expireTime"));
                }
                return null;
            }
        });
        dao.execute(sql);
        response.setRequest(request);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        return;
    }
}
