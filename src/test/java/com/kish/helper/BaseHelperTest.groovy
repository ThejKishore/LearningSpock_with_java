package com.kish.helper

import spock.lang.Specification

class BaseHelperTest extends Specification {

    def helper

    def setup(){
        helper =  BaseHelper.BaseHelperSingleton.INSTANCE.getBaseHelper()
    }


    def "GetPath_test_relative"() {
        when:
        def result = helper.getPath("relative:/META-INF/something1.properties")

        then:
        result == "/home/thej/IdeaProjects/utlities/out/production/classes/META-INF/something1.properties"
    }

    def "GetPath_test_classpath"() {
        when:
        def result = helper.getPath("classpath:/something.properties")

        then:
        result == "/home/thej/IdeaProjects/utlities/out/production/classes/something.properties"
    }

    def "GetPath_test_absolute"() {
        when:
        def result = helper.getPath("absolute:/home/thej/IdeaProjects/utlities/src/main/resources/META-INF/something1.properties")

        then:
        result == "/home/thej/IdeaProjects/utlities/src/main/resources/META-INF/something1.properties"
    }

    def "GetPath_test_file"() {
        when:
        def result = helper.getPath("file:/home/thej/IdeaProjects/utlities/src/main/resources/something.properties")

        then:
        result == "/home/thej/IdeaProjects/utlities/src/main/resources/something.properties"
    }

    def "GetPath_nullPointer"(){
        when:
        helper.getPath("classpath:something2.txt")

        then:
        thrown(NullPointerException)

    }

    def "IsValidString_test_emptystr"() {
        when:
        def result = helper.isValidString("")

        then:
        result == false

    }

    def "IsValidString_test_validstr"() {
        when:
        def result = helper.isValidString("asd")

        then:
        result == true

    }

    def "IsValidString_test_null"() {
        setup:
        def helper  =  BaseHelper.BaseHelperSingleton.INSTANCE.getBaseHelper()

        when:
        def result = helper.isValidString(null)

        then:
        result == false

    }
}
