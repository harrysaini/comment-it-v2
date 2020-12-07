rootProject.name = "comment-it-v2"


buildCache {
    local {
        isEnabled = !System.getenv().containsKey("CI")
    }
}