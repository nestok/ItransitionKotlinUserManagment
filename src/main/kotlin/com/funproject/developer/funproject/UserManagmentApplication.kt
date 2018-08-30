package com.funproject.developer.funproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent
import org.springframework.context.ApplicationListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent
import org.springframework.context.event.EventListener
import org.springframework.cloud.client.ServiceInstance
import com.netflix.appinfo.InstanceInfo
import com.netflix.config.DynamicPropertyFactory
import com.netflix.appinfo.ApplicationInfoManager
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider
import com.netflix.appinfo.EurekaInstanceConfig
import com.netflix.appinfo.MyDataCenterInstanceConfig
import com.netflix.discovery.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertiesPropertySource
import java.util.*
import org.springframework.context.ApplicationEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import javax.inject.Inject
import kotlin.collections.ArrayList


@SpringBootApplication
@EnableDiscoveryClient
//@EnableResourceServer
class UserManagmentApplication
//class UserManagmentApplication : ApplicationListener<InstanceRegisteredEvent<EurekaEvent>> {
//
//    @Autowired
//    lateinit var discoveryClient: DiscoveryClient
//
//    @Inject
//    lateinit var environment: Environment
//
//    private var applicationInfoManager: ApplicationInfoManager? = null
//    private var eurekaClient: EurekaClient? = null
//    lateinit var configInstance: DynamicPropertyFactory
//
//    override fun onApplicationEvent(event: InstanceRegisteredEvent<EurekaEvent>) {
//        configInstance = com.netflix.config.DynamicPropertyFactory.getInstance()
//        applicationInfoManager = initializeApplicationInfoManager(MyDataCenterInstanceConfig())
//        eurekaClient = initializeEurekaClient(applicationInfoManager!!, DefaultEurekaClientConfig())
//
//        var nextServerInfo: List<ServiceInstance> = ArrayList()
//        while (nextServerInfo.size == 0) {
//            try {
//                nextServerInfo = discoveryClient.getInstances("USER-MANAGEMENT-SERVICE")
//                Thread.sleep(31000)
//            } catch (e: Throwable) {
//                println("Waiting ... verifying service registration with eureka ...")
//            }
//        }
//        val service = nextServerInfo[0]
//    }
//
//    @Synchronized
//    private fun initializeEurekaClient(applicationInfoManager: ApplicationInfoManager, clientConfig: EurekaClientConfig): EurekaClient {
//        if (eurekaClient == null) {
//            eurekaClient = DiscoveryClient(applicationInfoManager, clientConfig)
//        }
//        return eurekaClient as EurekaClient
//    }
//
//    @Synchronized
//    private fun initializeApplicationInfoManager(instanceConfig: EurekaInstanceConfig): ApplicationInfoManager {
//        if (applicationInfoManager == null) {
//            val instanceInfo = EurekaConfigBasedInstanceInfoProvider(instanceConfig).get()
//            applicationInfoManager = ApplicationInfoManager(instanceConfig, instanceInfo)
//        }
//        return applicationInfoManager as ApplicationInfoManager
//    }
//
//}

fun main(args: Array<String>) {
    runApplication<UserManagmentApplication>(*args)
}

