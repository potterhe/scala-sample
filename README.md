# scala-sample

## archetype

https://github.com/davidB/scala-archetype-simple

### 关于scala-archetype-simple 1.6的bug

http://www.voidcn.com/article/p-enrpcfmw-ko.html

mvn test -Dtest=com.company.quickstart.HelloWorldTest

### invalid-signature-file-digest-for-manifest
https://stackoverflow.com/questions/34855649/invalid-signature-file-digest-for-manifest-main-attributes-exception-while-tryin
```
$ java -cp target/scala-sample-0.1-SNAPSHOT.jar com.company.App
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.SecurityException: Invalid signature file digest for Manifest main attributes
	at sun.security.util.SignatureFileVerifier.processImpl(SignatureFileVerifier.java:317)
	at sun.security.util.SignatureFileVerifier.process(SignatureFileVerifier.java:259)
	at java.util.jar.JarVerifier.processEntry(JarVerifier.java:323)
	at java.util.jar.JarVerifier.update(JarVerifier.java:234)
	at java.util.jar.JarFile.initializeVerifier(JarFile.java:394)
	at java.util.jar.JarFile.ensureInitialization(JarFile.java:632)
	at java.util.jar.JavaUtilJarAccessImpl.ensureInitialization(JavaUtilJarAccessImpl.java:69)
	at sun.misc.URLClassPath$JarLoader$2.getManifest(URLClassPath.java:993)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:456)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
	at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:601)
```

