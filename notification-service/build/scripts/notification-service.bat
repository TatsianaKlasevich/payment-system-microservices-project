@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  notification-service startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and NOTIFICATION_SERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\notification-service-1.0-SNAPSHOT.jar;%APP_HOME%\lib\flyway-core-6.5.3.jar;%APP_HOME%\lib\h2-1.4.200.jar;%APP_HOME%\lib\spring-boot-starter-mail-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-web-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-amqp-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-actuator-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-cloud-starter-config-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-starter-netflix-eureka-client-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-starter-sleuth-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-starter-netflix-hystrix-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-json-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-cloud-starter-netflix-ribbon-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-starter-netflix-archaius-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-starter-loadbalancer-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-starter-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-netflix-eureka-client-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-netflix-hystrix-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-cache-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-context-support-5.2.8.RELEASE.jar;%APP_HOME%\lib\jakarta.mail-1.6.5.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-cloud-config-client-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-web-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-rabbit-2.2.9.RELEASE.jar;%APP_HOME%\lib\spring-messaging-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-autoconfigure-2.3.2.RELEASE.jar;%APP_HOME%\lib\micrometer-core-1.5.3.jar;%APP_HOME%\lib\eureka-core-1.9.13.jar;%APP_HOME%\lib\ribbon-eureka-2.3.0.jar;%APP_HOME%\lib\eureka-client-1.9.13.jar;%APP_HOME%\lib\hystrix-metrics-event-stream-1.5.18.jar;%APP_HOME%\lib\hystrix-serialization-1.5.18.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.11.1.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.11.1.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.11.1.jar;%APP_HOME%\lib\hystrix-javanica-1.5.18.jar;%APP_HOME%\lib\ribbon-2.3.0.jar;%APP_HOME%\lib\hystrix-core-1.5.18.jar;%APP_HOME%\lib\netflix-eventbus-0.3.0.jar;%APP_HOME%\lib\ribbon-httpclient-2.3.0.jar;%APP_HOME%\lib\ribbon-transport-2.3.0.jar;%APP_HOME%\lib\ribbon-loadbalancer-2.3.0.jar;%APP_HOME%\lib\ribbon-core-2.3.0.jar;%APP_HOME%\lib\archaius-core-0.7.6.jar;%APP_HOME%\lib\jackson-module-afterburner-2.11.1.jar;%APP_HOME%\lib\jackson-databind-2.11.1.jar;%APP_HOME%\lib\xstream-1.4.11.1.jar;%APP_HOME%\lib\spring-cloud-sleuth-core-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-netflix-ribbon-2.2.1.RELEASE.jar;%APP_HOME%\lib\rxjava-reactive-streams-1.2.1.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.3.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.3.2.RELEASE.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\spring-context-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-amqp-2.2.9.RELEASE.jar;%APP_HOME%\lib\spring-core-5.2.8.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.26.jar;%APP_HOME%\lib\jakarta.activation-1.2.2.jar;%APP_HOME%\lib\tomcat-embed-websocket-9.0.37.jar;%APP_HOME%\lib\tomcat-embed-core-9.0.37.jar;%APP_HOME%\lib\jakarta.el-3.0.3.jar;%APP_HOME%\lib\amqp-client-5.9.0.jar;%APP_HOME%\lib\HdrHistogram-2.1.12.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\spring-cloud-loadbalancer-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-context-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-cloud-commons-2.2.1.RELEASE.jar;%APP_HOME%\lib\spring-security-rsa-1.0.9.RELEASE.jar;%APP_HOME%\lib\jackson-annotations-2.11.1.jar;%APP_HOME%\lib\jackson-core-2.11.1.jar;%APP_HOME%\lib\jettison-1.3.7.jar;%APP_HOME%\lib\jersey-apache-client4-1.19.1.jar;%APP_HOME%\lib\jersey-client-1.19.1.jar;%APP_HOME%\lib\jersey-core-1.19.1.jar;%APP_HOME%\lib\jsr311-api-1.1.1.jar;%APP_HOME%\lib\rxnetty-servo-0.4.9.jar;%APP_HOME%\lib\servo-core-0.12.21.jar;%APP_HOME%\lib\httpclient-4.5.12.jar;%APP_HOME%\lib\guice-4.1.0.jar;%APP_HOME%\lib\woodstox-core-5.2.1.jar;%APP_HOME%\lib\spring-cloud-netflix-archaius-2.2.1.RELEASE.jar;%APP_HOME%\lib\commons-configuration-1.8.jar;%APP_HOME%\lib\rxnetty-contexts-0.4.9.jar;%APP_HOME%\lib\rxnetty-0.4.9.jar;%APP_HOME%\lib\rxjava-1.3.8.jar;%APP_HOME%\lib\evictor-1.0.0.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\log4j-to-slf4j-2.13.3.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.30.jar;%APP_HOME%\lib\netflix-infix-0.3.0.jar;%APP_HOME%\lib\netflix-commons-util-0.3.0.jar;%APP_HOME%\lib\netflix-statistics-0.1.1.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\xmlpull-1.1.3.1.jar;%APP_HOME%\lib\xpp3_min-1.1.4c.jar;%APP_HOME%\lib\aspectjweaver-1.9.6.jar;%APP_HOME%\lib\aspectjrt-1.9.6.jar;%APP_HOME%\lib\brave-context-log4j2-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-spring-rabbit-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-kafka-streams-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-kafka-clients-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-jms-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-messaging-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-rpc-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-spring-web-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-httpclient-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-httpasyncclient-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-spring-webmvc-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-servlet-5.9.0.jar;%APP_HOME%\lib\brave-instrumentation-http-5.9.0.jar;%APP_HOME%\lib\brave-5.9.0.jar;%APP_HOME%\lib\zipkin-reporter-metrics-micrometer-2.11.0.jar;%APP_HOME%\lib\commons-lang3-3.10.jar;%APP_HOME%\lib\asm-5.0.4.jar;%APP_HOME%\lib\guava-19.0.jar;%APP_HOME%\lib\reactor-extra-3.3.3.RELEASE.jar;%APP_HOME%\lib\reactor-core-3.3.8.RELEASE.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\spring-jcl-5.2.8.RELEASE.jar;%APP_HOME%\lib\spring-retry-1.2.5.RELEASE.jar;%APP_HOME%\lib\spring-security-crypto-5.3.3.RELEASE.jar;%APP_HOME%\lib\bcpkix-jdk15on-1.64.jar;%APP_HOME%\lib\stax-api-1.0.1.jar;%APP_HOME%\lib\commons-math-2.2.jar;%APP_HOME%\lib\httpcore-4.4.13.jar;%APP_HOME%\lib\commons-codec-1.14.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\stax2-api-4.2.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\zipkin-reporter-2.11.0.jar;%APP_HOME%\lib\zipkin-2.19.0.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\log4j-api-2.13.3.jar;%APP_HOME%\lib\bcprov-jdk15on-1.64.jar;%APP_HOME%\lib\commons-jxpath-1.3.jar;%APP_HOME%\lib\joda-time-2.3.jar;%APP_HOME%\lib\antlr-runtime-3.4.jar;%APP_HOME%\lib\gson-2.8.6.jar;%APP_HOME%\lib\stringtemplate-3.2.1.jar;%APP_HOME%\lib\antlr-2.7.7.jar


@rem Execute notification-service
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %NOTIFICATION_SERVICE_OPTS%  -classpath "%CLASSPATH%" com.klasevich.itrex.lab.Runner %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable NOTIFICATION_SERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%NOTIFICATION_SERVICE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
