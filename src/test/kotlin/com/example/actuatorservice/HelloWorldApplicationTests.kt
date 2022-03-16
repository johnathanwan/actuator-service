package com.example.actuatorservice

import org.assertj.core.api.BDDAssertions.*
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.context.*
import org.springframework.boot.test.web.client.*
import org.springframework.boot.web.server.*
import org.springframework.http.*
import org.springframework.test.context.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = ["management.port=0"])
class HelloWorldApplicationTests {

    @LocalServerPort
    private val port = 0

    @Value("\${local.management.port}")
    private val mgt = 0

    @Autowired
    private val testRestTemplate: TestRestTemplate? = null

    @Test
    fun `should return 200 when sending request to controller`() {
        val entity = testRestTemplate!!.getForEntity("http://localhost:$port/hello-world", Map::class.java)
        then(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `should return 200 when sending request to management endpoint`() {
        val entity = testRestTemplate!!.getForEntity("http://localhost:$mgt/actuator", Map::class.java)
        then(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

}