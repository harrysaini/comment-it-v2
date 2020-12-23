package com.harrry.comment_it.config

import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class FlywayConfig (
        val hikariDataSource: HikariDataSource
) {

    @PostConstruct
    fun migrateWithFlyway() {
        val flyway = Flyway.configure().dataSource(hikariDataSource).load()

        flyway.migrate()
    }
}