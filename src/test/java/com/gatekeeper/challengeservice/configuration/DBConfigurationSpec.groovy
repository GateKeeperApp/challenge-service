package com.gatekeeper.challengeservice.configuration

import org.springframework.jdbc.datasource.DriverManagerDataSource
import spock.lang.Specification

class DBConfigurationSpec extends Specification {

    DBConfiguration dbConfiguration = new DBConfiguration()

    def "Test dataSource"() {
        when:
        DriverManagerDataSource dataSource = dbConfiguration.dataSource()

        then:
        0 * _

        and:
        dataSource
    }
}
