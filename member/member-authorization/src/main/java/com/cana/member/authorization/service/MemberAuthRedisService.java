package com.cana.member.authorization.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.cana.member.authorization.common.MemberAuthUtils;
import com.cana.member.authorization.common.MemberCommonConfig;
import com.cana.member.authorization.common.PlatformUtils;

@Component("memberAuthRedisService")
public class MemberAuthRedisService {

	private static final Logger LGR = LoggerFactory.getLogger(MemberAuthRedisService.class);

	@Resource
	private RedisTemplate<String, Object> rdsTemplate;

	public void putSessionAttrs(final String key, final Object obj) {
		if(!MemberAuthUtils.isValidRediskey(key)){
			LGR.error("invalid redis key: {}", key);
			return ;
		}
		rdsTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = serialize(obj);
				if (ArrayUtils.isEmpty(value)) {
					LGR.info("obj is empty!");
					return null;
				}
				connection.setEx(serializeKey(key), MemberCommonConfig.getInt("http.session.timeout") * 60, value);
				return null;
			}
		});
	}

	public Object get(final String key) {
		if(!MemberAuthUtils.isValidRediskey(key)){
			LGR.info("the redis key : {} is not  valid", key);
			return null;
		}
		return rdsTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] b = connection.get(serializeKey(key));
				return deserialize(b);
			}
		});
	}


	public void remove(final String key) {
		rdsTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.del(serializeKey(key));
				return null;
			}
		});
	}

	public Long getLoginFailureCount(HttpServletRequest request) {
		Long usernameLoginFailureCount = getUsernameLoginFailureCount(request);
		Long ipLoginFailureCount = getIpLoginFailureCount(request);
		return ipLoginFailureCount > usernameLoginFailureCount ? ipLoginFailureCount : usernameLoginFailureCount;
	}

	public Long getUsernameLoginFailureCount(HttpServletRequest request) {
		final String key = getUsernameLoginFailureRedisKey(request);
		if (StringUtils.isEmpty(key)) {
			return new Long(0);
		}
		return rdsTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = serializeKey(key);
				byte[] v = connection.get(k);
				String result = rdsTemplate.getStringSerializer().deserialize(v);
				if (StringUtils.isBlank(result) || !result.matches("\\d+")) {
					return Long.valueOf(0);
				} else {
					return Long.parseLong(result);
				}
			}
		});
	}

	public Long getIpLoginFailureCount(HttpServletRequest request) {
		final String key = getIpLoginFailureRedisKey(request);
		if (StringUtils.isEmpty(key)) {
			return new Long(0);
		}
		return rdsTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = serializeKey(key);
				byte[] v = connection.get(k);
				String result = rdsTemplate.getStringSerializer().deserialize(v);
				if (StringUtils.isBlank(result) || !result.matches("\\d+")) {
					return Long.valueOf(0);
				} else {
					return Long.parseLong(result);
				}
			}
		});
	}

	public void removeUsernameLoginFailureCount(HttpServletRequest request) {
		final String key = getUsernameLoginFailureRedisKey(request);
		rdsTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = serializeKey(key);
				return connection.del(k);
			}
		});
	}

	public void removeIPLoginFailureCount(HttpServletRequest request) {
		final String key = getIpLoginFailureRedisKey(request);
		rdsTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = serializeKey(key);
				return connection.del(k);
			}
		});
	}

	public Long increaseUsernameLoginFailureCount(HttpServletRequest request) {
		final String key = getUsernameLoginFailureRedisKey(request);
		if (StringUtils.isEmpty(key)) {
			return new Long(0);
		}
		return rdsTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] k = serializeKey(key);
				Long result = connection.incr(k);
				connection.expire(k, MemberCommonConfig.getInt("http.session.timeout") * 60);
				return result;
			}
		});
	}

	public Long increaseIpLoginFailureCount(HttpServletRequest request) {
		final String key = getIpLoginFailureRedisKey(request);
		if (StringUtils.isEmpty(key)) {
			return new Long(0);
		}
		return rdsTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] k = serializeKey(key);
				Long result = connection.incr(k);
				connection.expire(k, MemberCommonConfig.getInt("http.session.timeout") * 60);
				return result;
			}
		});
	}

	private String getUsernameLoginFailureRedisKey(HttpServletRequest request) {
		String username = request.getParameter("username");
		if (StringUtils.isEmpty(username)) {
			Cookie cookie = MemberAuthUtils.getCookie(request, MemberAuthUtils.getUsername());
			if (cookie != null)
				username = cookie.getValue();
		}

		if (StringUtils.isNotEmpty(username)) {
			return PlatformUtils.getPlatform().name() + ":login_failure_count:" + username;
		}else {
			return "";
		}
	}

	private String getIpLoginFailureRedisKey(HttpServletRequest request) {
		String ipAddress = MemberAuthUtils.getIpAddr(request);
		if (!StringUtils.isEmpty(ipAddress)) {
			return PlatformUtils.getPlatform().name() + ":ip_login_failure_count:" + ipAddress;
		} else {
			return "";
		}
	}

	private byte[] serializeKey(String key) {
		return rdsTemplate.getStringSerializer().serialize(key);
	}

	private byte[] serialize(Object obj) {
		if (obj == null) {
			return null;
		}
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(obj);
			return baos.toByteArray();
		} catch (Exception e) {
			LGR.error("serialize object failed.", e);
		}
		return null;
	}

	private Object deserialize(byte[] b) {
		if (ArrayUtils.isEmpty(b)) {
			return null;
		}
		try (ByteArrayInputStream bais = new ByteArrayInputStream(b);
				ObjectInputStream ois = new ObjectInputStream(bais)) {
			return ois.readObject();
		} catch (Exception e) {
			LGR.error("deserialize object failed.", e);
		}
		return null;
	}

}
