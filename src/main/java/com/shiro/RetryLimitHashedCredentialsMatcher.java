package com.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.shiro.cache.VCache;
import com.shiro.manager.JedisShiroContant;

/**
 * 验证登录
 * @author HOY
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	@SuppressWarnings( "unchecked" )
	@Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        AtomicInteger retryCount=new AtomicInteger(0);
       
        if(VCache.exists(JedisShiroContant.REDIS_SHIRO_CACHE_PASSWORDRETRY+username)) {
            retryCount = VCache.get(JedisShiroContant.REDIS_SHIRO_CACHE_PASSWORDRETRY+username);
        }
        
        if(retryCount.incrementAndGet() > 5) {
        	throw new ExcessiveAttemptsException("密码连续出错，已被锁定，请10分钟后再登录！");
        }
        
        VCache.setex(JedisShiroContant.REDIS_SHIRO_CACHE_PASSWORDRETRY+username, retryCount, 10*60);
        
        String tokenP = String.valueOf(((UsernamePasswordToken)token).getPassword());
        String infoP = getCredentials(info).toString();
        boolean matches = tokenP.equals(infoP);//super.doCredentialsMatch(token, info);
        //System.out.println(username);
        //System.out.println(info.getPrincipals());
        if(matches) {
            //clear retry count
        	VCache.delByKey(JedisShiroContant.REDIS_SHIRO_CACHE_PASSWORDRETRY+username);
        }
        return matches;
    }
	
}
