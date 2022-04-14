package my.store.assignment.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Getter
@Setter
public class RedisConfig {

    private boolean clusterMode;

    @Autowired
    RedisProperties redisProperties;

    @Bean
    JedisConnectionFactory jedisClusterConnectionFactory() {
        return clusterMode ? new JedisConnectionFactory(getRedisClusterConfiguration())
                : new JedisConnectionFactory(getRedisStandaloneConfiguration());
    }

    private RedisClusterConfiguration getRedisClusterConfiguration() {
        return new RedisClusterConfiguration(List.of(redisProperties.getHost() + ":" + redisProperties.getPort()));
    }

    private RedisStandaloneConfiguration getRedisStandaloneConfiguration() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        return configuration;
    }

    @Primary
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
