
  ${pom.organization.name} - ${pom.name} - README.txt
  Version ${pom.version} Build ${buildNumber}
  ${pom.url}

  ${pom.description}

  Environment

    The environment variable CLASSPATH_PREFIX needs to contain a classpath entry
    pointing to the JOMC Logging implementation artifact to test.

  Tests

    org.jomc.logging.it.LoggerTest
