package com.harrry.comment_it.config

import org.flywaydb.core.Flyway
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
@EnableConfigurationProperties(value = [DatabaseConfig::class])
class FlywayConfig (
        val databaseConfig: DatabaseConfig
) {

    @PostConstruct
    fun migrateWithFlyway() {
        val flyway = Flyway.configure().dataSource(
                databaseConfig.url,
                databaseConfig.username,
                databaseConfig.password
        ).load()

        flyway.migrate()
    }
}