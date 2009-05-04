
  ${pom.organization.name} - ${pom.name} - README.txt
  Version ${pom.version} Build ${buildNumber}
  ${pom.url}

  ${pom.description}

  Environment

    Prior to testing, the JOMC Sequences EAR artifact needs to be deployed to
    the container and the environment variable CLASSPATH_PREFIX needs to hold
    the client classpath corresponding to the container to test.

  JBoss

    For testing against a local JBoss application server copy the file
    etc/jboss-jndi.properties to etc/jndi.properties and update the
    JNDI names in file etc/META-INF/jomc.xml accordingly.

  Glassfish

    For testing against a local Glassfish application server copy the
    file etc/glassfish-jndi.properties to etc/jndi.properties and update
    the JNDI names in file etc/META-INF/jomc.xml accordingly.

    for i in glassfish-v2.1-b60e-linux-ml/lib/*.jar;do
      CLASSPATH_PREFIX="$i:$CLASSPATH_PREFIX"
    done
    export CLASSPATH_PREFIX

    <property name="objectName" type="java.lang.String"
              value="#org.jomc.sequences.SequenceOperations"/>

    <property name="objectName" type="java.lang.String"
              value="#org.jomc.sequences.SequenceDirectory"/>

  Other

    Setup of various application servers should be similar to the examples shown
    above. For sharing test setups please use one of the project mailing lists.

  Tests

    org.jomc.sequences.it.SequenceOperationsTest
    org.jomc.sequences.it.SequenceDirectoryTest
