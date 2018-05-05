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
        result == "/home/thej/IdeaProjects/utlities/out/test/classes/META-INF/something1.properties"
    }

    def "GetPath_test_classpath"() {
        when:
        def result = helper.getPath("classpath:/something.properties")

        then:
        result == "/home/thej/IdeaProjects/utlities/out/test/classes/something.properties"
    }

    def "GetPath_test_absolute"() {
        when:
        def result = helper.getPath("absolute:/home/thej/IdeaProjects/utlities/src/test/resources/META-INF/something1.properties")

        then:
        result == "/home/thej/IdeaProjects/utlities/src/test/resources/META-INF/something1.properties"
    }

    def "GetPath_test_file"() {
        when:
        def result = helper.getPath("file:/home/thej/IdeaProjects/utlities/src/test/resources/something.properties")

        then:
        result == "/home/thej/IdeaProjects/utlities/src/test/resources/something.properties"
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


    def "ReadFileToList_test1"(){
        when:
        def fileContentList = helper.readFileToList("classpath:/META-INF/something1.properties")

        then:
        println "intentionally kept to fail"
        fileContentList.get(1) == "db.name1=mysqldriver"
    }

    //intentionally kept a failure scenario....
    def "ReadFileToList_test2"(){
        when:
        def fileContentList = helper.readFileToList("classpath:/something.properties")

        then:
        fileContentList.get(0) == "db.name=mysqldriver"
    }



    def "ReadFileToString_test1"(){
        when:
        def fileContentList = helper.readFileToString("classpath:/META-INF/something1.properties")
        def expectedResult ="""db.name=mysqldriver
db.name1=mysqldriver1
"""

        then:
        fileContentList == expectedResult
    }

    //intentionally kept a failure scenario....
    def "ReadFileToString_test2"(){
        when:
        def fileContentList = helper.readFileToString("classpath:/something.properties")

        then:
        fileContentList == "db.name=mysqldriver\n"
    }


    def "IsValidCollection_test1"(){
        when:
        def bool = helper.isValidCollection(null)

        then:
        bool == false
    }

    def "IsValidMap_test1"(){
        when:
        def bool = helper.isValidMap(null)

        then:
        bool == false
    }


    def "IsValidCollection_test2"(){
        when:
        def list = new ArrayList()
        def bool = helper.isValidCollection(list)

        then:
        bool == false
    }

    def "IsValidMap_test2"(){
        when:
        def map = new HashMap()
        def bool = helper.isValidMap(map)

        then:
        bool == false
    }

    def "IsValidCollection_test3"(){
        when:
        def list = new ArrayList()
        list.add("Something")
        def bool = helper.isValidCollection(list)

        then:
        bool == true
    }

    def "IsValidMap_test3"(){
        when:
        def map = new HashMap()
        map.put("something","something")
        def bool = helper.isValidMap(map)

        then:
        bool == true
    }
}
