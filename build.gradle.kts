
subprojects {
    tasks {
        create("stage") {
            dependsOn(listOf("clean", "build"))
        }
    }
}