#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.test;

import java.nio.file.Paths;

import org.joda.time.Duration;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.configuration.DockerComposeFiles;
import com.palantir.docker.compose.connection.DockerMachine;
import com.palantir.docker.compose.connection.DockerPort;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import com.palantir.docker.compose.configuration.ShutdownStrategy;
import com.palantir.docker.compose.execution.SkipShutdownStrategy;

import ${package}.test.group.HelloworldEmptyInstallationIntegrationTest;


@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Category({HelloworldEmptyInstallationIntegrationTest.class})
public class InstallHelloworldIntegrationTest {

  private static final String SERVICES_NAME = "helloworld";
  private static final String DB_SERVICE_NAME = "helloworlddb";
  private static final String SERVICE_URL = String.format("http://%s-services:8080/%s", "helloworld", "helloworld");
  private static final String jsonMimeType = "application/json";

  static String ROOT_FOLDER = Paths.get("").toAbsolutePath().normalize().toString();
  static String VOLUME_H2_SPRING_CONFIG_LOCATION = ROOT_FOLDER + "/src/integration-test/resources/configh2/server";
  static String VOLUME_H2_SPRING_CONFIG_MANAGER_LOCATION = ROOT_FOLDER + "/src/integration-test/resources/configh2/manager";

  private static final DockerMachine dockerMachine = DockerMachine.localMachine()
      .withAdditionalEnvironmentVariable("DB_USER", "sa")
      .withAdditionalEnvironmentVariable("DB_PASSWORD", "")
      .withAdditionalEnvironmentVariable("BACKEND_REST_URL", SERVICE_URL)
      .withAdditionalEnvironmentVariable("SPRING_CONFIG_SERVER_LOCATION", VOLUME_H2_SPRING_CONFIG_LOCATION)
      .withAdditionalEnvironmentVariable("SPRING_CONFIG_MANAGER_LOCATION", VOLUME_H2_SPRING_CONFIG_MANAGER_LOCATION)
      .build();

  @ClassRule
  public static DockerComposeRule docker = DockerComposeRule
      .builder()
      .machine(dockerMachine)
      .files(DockerComposeFiles.from("target/integration-test-resources/test-compose-helloworld-services-H2.yaml"))
      .waitingForService(DB_SERVICE_NAME, HealthChecks.toHaveAllPortsOpen())
      .waitingForService(SERVICES_NAME, HealthChecks
          .toRespond2xxOverHttp(8080, service -> "http://" + service.getIp() + ":" + service.getExternalPort() + "/helloworld/v1/anon/ping"), Duration.standardMinutes(4))
      .saveLogsTo("target/dockerLogs/h2")
      .removeConflictingContainersOnStartup(true)
      .shutdownStrategy(new SkipShutdownStrategy())
      .build();

  @Test
  public void validateAllServicesStart() throws Exception {
    DockerPort h2Service = docker.containers()
        .container(DB_SERVICE_NAME)
        .port(81);

    org.junit.Assert.assertNotNull(h2Service);
    String h2ServiceUrl = String.format("http://%s:%s", h2Service.getIp(), h2Service.getExternalPort());
    org.junit.Assert.assertNotNull(h2ServiceUrl);

    DockerPort jsoaggerService = docker.containers()
        .container(SERVICES_NAME)
        .port(8080);

    org.junit.Assert.assertNotNull(jsoaggerService);
    String dynamoEndpoint = String.format("http://%s:%s", jsoaggerService.getIp(), jsoaggerService.getExternalPort());
    org.junit.Assert.assertNotNull(dynamoEndpoint);
  }
}
