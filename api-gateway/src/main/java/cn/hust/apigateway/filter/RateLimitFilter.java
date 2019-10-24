package cn.hust.apigateway.filter;

import cn.hust.apigateway.exception.RatelimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 使用google令牌桶实现限流
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-24 14:18
 **/
@Component
public class RateLimitFilter extends ZuulFilter {

    //创建100个令牌
    private static  final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if(!RATE_LIMITER.tryAcquire()){
            throw  new RatelimitException();
        }
        return null;
    }
}
