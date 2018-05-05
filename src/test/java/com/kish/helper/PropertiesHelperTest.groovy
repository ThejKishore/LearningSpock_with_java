package com.kish.helper

import spock.lang.Specification

class PropertiesHelperTest extends Specification {

    PropertiesHelper helper

    void setup() {
        helper = PropertiesHelper.PropertiesSingletonHelper.INSTANCE.getPropertiesHelper()
    }

    def "LoadProperties_test_positive"() {
        when:
        def prop= helper.loadProperties("classpath:/META-INF/something1.properties")

        then:
        prop.getProperty("db.name") == "mysqldriver"
    }

    def "LoadProperties_test_positive_negative"() {
        when:
        def prop= helper.loadProperties("classpath:/META-INF/something1.properties")

        then:
        prop.getProperty("db.name") != "mysqldriver1"
    }


    //intentionally kept the failure scenario to see how spock works..
    def "LoadProperties_test_negative"() {
        when:
        def prop= helper.loadProperties("classpath:/META-INF/something1.properties")

        then:
        prop.getProperty("db.name1") != "mysqldriver1"
    }
}
