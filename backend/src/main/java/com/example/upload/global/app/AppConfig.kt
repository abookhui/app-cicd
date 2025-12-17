package com.example.upload.global.app

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.tika.Tika
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class AppConfig {

    companion object {
        private lateinit var objectMapper: ObjectMapper
        private lateinit var genFileDirPath: String
        private lateinit var environment: Environment
        private lateinit var siteBackUrl: String
        private lateinit var siteFrontUrl: String
        private lateinit var springServletMultipartMaxFileSize: String
        private lateinit var springServletMultipartMaxRequestSize: String
        private lateinit var tika: Tika

        fun getObjectMapper(): ObjectMapper = objectMapper

        fun getGenFileDirPath(): String = genFileDirPath

        fun getSiteBackUrl(): String = siteBackUrl

        fun getSiteFrontUrl(): String = siteFrontUrl

        fun getSpringServletMultipartMaxFileSize(): String = springServletMultipartMaxFileSize

        fun getSpringServletMultipartMaxRequestSize(): String = springServletMultipartMaxRequestSize

        // 임시 디렉토리 경로 반환
        fun getTempDirPath(): String = System.getProperty("java.io.tmpdir")

        fun getTika(): Tika = tika

        /**
         * JAR 실행 환경에서는 리소스의 '물리적 파일 경로'를 추출할 수 없습니다.
         * 대신 리소스 파일들이 들어있는 루트 디렉토리 명칭(classpath 기준)만 반환합니다.
         */
        fun getResourcesSampleDirPath(): String = "sample"

        val isNotProd: Boolean
            get() = !isProd

        val isProd: Boolean
            get() = environment.matchesProfiles("prod")

        val isDev: Boolean
            get() = environment.matchesProfiles("dev")

        val isTest: Boolean
            get() = environment.matchesProfiles("test")
    }

    @Value("\${custom.site.backUrl}")
    fun setSiteBackUrl(siteBackUrl: String) {
        Companion.siteBackUrl = siteBackUrl
    }

    @Value("\${custom.site.frontUrl}")
    fun setSiteFrontUrl(siteFrontUrl: String) {
        Companion.siteFrontUrl = siteFrontUrl
    }

    @Autowired
    fun setEnvironment(environment: Environment) {
        Companion.environment = environment
    }

    @Value("\${custom.genFile.dirPath}")
    fun setGenFileDirPath(genFileDirPath: String) {
        Companion.genFileDirPath = genFileDirPath
    }

    @Autowired
    fun setObjectMapper(objectMapper: ObjectMapper) {
        Companion.objectMapper = objectMapper
    }

    @Autowired
    fun setTika(tika: Tika) {
        Companion.tika = tika
    }

    @Value("\${spring.servlet.multipart.max-file-size}")
    fun setSpringServletMultipartMaxFileSize(springServletMultipartMaxFileSize: String) {
        Companion.springServletMultipartMaxFileSize = springServletMultipartMaxFileSize
    }

    @Value("\${spring.servlet.multipart.max-request-size}")
    fun setSpringServletMultipartMaxRequestSize(springServletMultipartMaxRequestSize: String) {
        Companion.springServletMultipartMaxRequestSize = springServletMultipartMaxRequestSize
    }
}