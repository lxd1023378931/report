package com.uzak.configure;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/30.
 */
@Data
@Component
public class AppConfigure {
    private GrpcConfigure grpcServices;
    private ZipKinConfigure zipKinConfigure;
}
