plugins {
    java
    id("com.github.node-gradle.node") version "2.2.4"
}


node {
    version = "12.19.0"
    download = true
}

tasks {

    create<com.moowork.gradle.node.npm.NpmTask>("install"){
        setArgs(listOf("install"))
    }

    create<com.moowork.gradle.node.npm.NpmTask>("bundle"){
        dependsOn.add("install")
        setArgs(listOf("run", "build"))
    }

    create<com.moowork.gradle.node.npm.NpmTask>("run"){
        setArgs(listOf("start"))
    }

    create<Jar>("webJar"){
        from(fileTree("build")){
            into("META-INF/resources")
        }
    }

    jar {
        dependsOn("bundle")
        finalizedBy("webJar")
    }
}

