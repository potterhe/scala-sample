# scala-sample

## archetype

https://github.com/davidB/scala-archetype-simple

### 关于scala-archetype-simple 1.6的bug

http://www.voidcn.com/article/p-enrpcfmw-ko.html

mvn test -Dtest=com.company.quickstart.HelloWorldTest

## maven插件

- maven-shade-plugin

```xml
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.4.3</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <minimizeJar>true</minimizeJar>
            </configuration>
          </execution>
        </executions>
      </plugin>
```
